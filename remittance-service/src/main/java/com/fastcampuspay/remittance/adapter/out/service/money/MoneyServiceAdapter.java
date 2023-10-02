package com.fastcampuspay.remittance.adapter.out.service.money;

import com.fastcampuspay.common.CommonHttpClient;
import com.fastcampuspay.common.ExternalSystemAdapter;
import com.fastcampuspay.remittance.application.port.out.money.MoneyInfo;
import com.fastcampuspay.remittance.application.port.out.money.MoneyPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyServiceAdapter implements MoneyPort {

    private final CommonHttpClient moneyServiceHttpClient;

    @Value("${service.money.url}")
    private String moneyServiceEndpoint;

    @Override
    public MoneyInfo getMoneyInfo(String membershipId) {
        String buildUrl = String.join("/", this.moneyServiceEndpoint, "money", membershipId);

        try {
            String jsonResponse = moneyServiceHttpClient.sendGetRequest(buildUrl).body();
            ObjectMapper mapper = new ObjectMapper();

            if(jsonResponse.isEmpty()) {
                return new MoneyInfo(membershipId, 0);
            }
            MemberMoney mem = mapper.readValue(jsonResponse, MemberMoney.class);
            if (mem != null){
                return new MoneyInfo(mem.getMembershipId(), mem.getBalance());
            } else {
                return new MoneyInfo(membershipId, 0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean requestMoneyRecharging(String membershipId, int amount) {
        String buildUrl = String.join("/", this.moneyServiceEndpoint, "money", "increase-async");

        return sendMoneyIncreaseRequest(membershipId, amount, buildUrl);
    }

    @Override
    public boolean requestMoneyIncrease(String membershipId, int amount) {
        String buildUrl = String.join("/", this.moneyServiceEndpoint, "money", "increase");

        return sendMoneyIncreaseRequest(membershipId, amount, buildUrl);
    }

    private boolean sendMoneyIncreaseRequest(String membershipId, int amount, String buildUrl) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String request = mapper.writeValueAsString(new MoneyChangingRequest(membershipId, amount));
            String jsonResponse = moneyServiceHttpClient.sendPostRequest(buildUrl, request).join().body();

            MoneyChangingResultDetail mem = mapper.readValue(jsonResponse, MoneyChangingResultDetail.class);
            if (mem.getMoneyChangingResultStatus() == 0 && mem.getMoneyChangingType() == 0
                    && mem.getAmount() == amount){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean requestMoneyDecrease(String membershipId, int amount) {
        String buildUrl = String.join("/", this.moneyServiceEndpoint, "money", "decrease");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String request = mapper.writeValueAsString(new MoneyChangingRequest(membershipId, amount));
            String jsonResponse = moneyServiceHttpClient.sendPostRequest(buildUrl, request).join().body();

            MoneyChangingResultDetail mem = mapper.readValue(jsonResponse, MoneyChangingResultDetail.class);
            if (mem.getMoneyChangingResultStatus() == 0 && mem.getMoneyChangingType() == 1
                    && mem.getAmount() == amount){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
