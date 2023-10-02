package com.fastcampuspay.money.application.service;

import com.fastcampuspay.common.UseCase;
import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyMapper;
import com.fastcampuspay.money.application.port.in.FindMemberMoneyCommand;
import com.fastcampuspay.money.application.port.in.FindMemberMoneyUseCase;
import com.fastcampuspay.money.application.port.out.FindMemberMoneyPort;
import com.fastcampuspay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindMemberMoneyService implements FindMemberMoneyUseCase {

    private final FindMemberMoneyPort findMemberMoneyPort;
    private final MemberMoneyMapper mapper;

    @Override
    public MemberMoney findMemberMoney(FindMemberMoneyCommand command) {
        MemberMoneyJpaEntity memberMoneyJpaEntity = findMemberMoneyPort.findMemberMoney(command.getMembershipId());

        if(memberMoneyJpaEntity != null) {
            return mapper.mapToDomainEntity(memberMoneyJpaEntity);
        } else {
            return null;
        }
    }
}
