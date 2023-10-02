package com.fastcampuspay.remittance.adapter.out.service.banking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResult {
    private String registeredBankAccountId;

    private String membershipId;

    private String bankName; // enum

    private String bankAccountNumber;

    private boolean linkedStatusIsValid;
}
