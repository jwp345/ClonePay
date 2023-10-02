package com.fastcampuspay.money.adapter.out.persistence;

import com.fastcampuspay.common.PersistenceAdapter;
import com.fastcampuspay.money.application.port.out.FindMemberMoneyPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FindMemberMoneyPersistenceAdapter implements FindMemberMoneyPort {

    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MemberMoneyJpaEntity findMemberMoney(String membershipId) {
        try {
            return memberMoneyRepository.findALlByMembershipId(membershipId).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
