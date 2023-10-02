package com.fastcampuspay.remittance.adapter.out.service.banking;

import com.fastcampuspay.common.CommonHttpClient;
import com.fastcampuspay.common.ExternalSystemAdapter;
import com.fastcampuspay.remittance.application.port.out.banking.BankingInfo;
import com.fastcampuspay.remittance.application.port.out.banking.BankingPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.net.http.HttpResponse;


@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdapter implements BankingPort {

    private final CommonHttpClient bankingServiceHttpClient;

    @Value("${service.banking.url}")
    private String bankingServiceEndpoint;

    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber, String membershipId) {
        String buildUrl = String.join("/", this.bankingServiceEndpoint,
                "banking", "account", "register");
        try {
            ObjectMapper mapper = new ObjectMapper();

            String response = bankingServiceHttpClient.sendPostRequest(buildUrl, mapper.writeValueAsString(new
                    Banking(membershipId, bankName, bankAccountNumber, true))).join().body();

            AccountResult result = mapper.readValue(response, AccountResult.class);
            if (result.isLinkedStatusIsValid()){
                return new BankingInfo(result.getBankName(), result.getBankAccountNumber(), true);
            } else{
                return new BankingInfo(result.getBankName(), result.getBankAccountNumber(), false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean requestFirmBanking(String bankName, String bankAccountNumber, String fromMembershipId, int amount) {
        String buildUrl = String.join("/", this.bankingServiceEndpoint,
                "banking", "firmbanking", "register");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonRequest = mapper.writeValueAsString(new RequestBanking(bankName, bankAccountNumber, fromMembershipId, amount));

            String response = bankingServiceHttpClient.sendPostRequest(buildUrl, jsonRequest).join().body();

            FirmBankingResult result = mapper.readValue(response, FirmBankingResult.class);
            return result.getFirmbankingStatus() == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}