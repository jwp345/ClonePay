package com.fastcampuspay.remittance.adapter.out.service.banking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestBanking {
    private String bankName;
    private String bankAccountNumber;
    private String fromMembershipId;
    private int amount;
}
