package com.fastcampuspay.money.application.port.out;

import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyJpaEntity;

public interface FindMemberMoneyPort {
    MemberMoneyJpaEntity findMemberMoney(String membershipId);
}
