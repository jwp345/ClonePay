package com.fastcampuspay.remittance.application.port.out.banking;

public interface BankingPort {
    BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber, String membershipId);

    boolean requestFirmBanking(String bankName, String bankAccountNumber, String fromMembershipId, int amount);
}
