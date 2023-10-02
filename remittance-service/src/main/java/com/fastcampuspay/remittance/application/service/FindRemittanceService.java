package com.fastcampuspay.remittance.application.service;

import com.fastcampuspay.common.UseCase;
import com.fastcampuspay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.fastcampuspay.remittance.adapter.out.persistence.RemittanceRequestMapper;
import com.fastcampuspay.remittance.application.port.in.FindRemittanceCommand;
import com.fastcampuspay.remittance.application.port.in.FindRemittanceUseCase;
import com.fastcampuspay.remittance.application.port.out.FindRemittancePort;
import com.fastcampuspay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {

    private final FindRemittancePort findRemittancePort;
    private final RemittanceRequestMapper mapper;

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
        List<RemittanceRequestJpaEntity> resultList = findRemittancePort.findRemittanceHistory(command);

        List<RemittanceRequest> remittanceRequests = new ArrayList<>();
        for(RemittanceRequestJpaEntity entity : resultList) {
            remittanceRequests.add(mapper.mapToDomainEntity(entity));
        }

        return remittanceRequests;
    }
}
