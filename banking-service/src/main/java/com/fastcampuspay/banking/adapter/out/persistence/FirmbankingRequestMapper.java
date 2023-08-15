package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.domain.FirmBankingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FirmbankingRequestMapper {
    public FirmBankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity requestFirmbankingJpaEntity, UUID uuid) {
        return FirmBankingRequest.generateFirmbankingRequest(
                new FirmBankingRequest.FirmbankingReuqestId(requestFirmbankingJpaEntity.getRequestFirmbankingId() + ""),
                new FirmBankingRequest.FromBankName(requestFirmbankingJpaEntity.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(requestFirmbankingJpaEntity.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(requestFirmbankingJpaEntity.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(requestFirmbankingJpaEntity.getToBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(requestFirmbankingJpaEntity.getMoneyAmount()),
                new FirmBankingRequest.FirmbankingStatus(requestFirmbankingJpaEntity.getFirmbankingStatus()), uuid
        );
    }
}
