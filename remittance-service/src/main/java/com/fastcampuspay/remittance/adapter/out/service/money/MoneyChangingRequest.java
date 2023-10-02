package com.fastcampuspay.remittance.adapter.out.service.money;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoneyChangingRequest {

    private String targetMembershipId;

    private int amount;
}