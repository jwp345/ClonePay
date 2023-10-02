package com.fastcampuspay.remittance.adapter.out.service.banking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Banking {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private boolean isValid;
}