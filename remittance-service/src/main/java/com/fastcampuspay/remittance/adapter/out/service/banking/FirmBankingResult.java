package com.fastcampuspay.remittance.adapter.out.service.banking;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FirmBankingResult {
    private String firmbankingRequestId;

    private String fromBankName;

    private String fromBankAccountNumber; // enum

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAmount; // only won

    private int firmbankingStatus; // 0: 요청, 1: 완료, 2: 실패

    private UUID uuid;
}