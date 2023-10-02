package com.fastcampuspay.remittance.adapter.out.service.money;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoney {

    private String memberMoneyId;


    private String membershipId;


    private int balance;
}
