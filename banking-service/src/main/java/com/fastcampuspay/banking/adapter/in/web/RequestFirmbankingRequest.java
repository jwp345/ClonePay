package com.fastcampuspay.banking.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingRequest {
    // a -> b 실물계좌로 요청을 하기 위한 Request

    private String fromMembershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount; // only won
}
