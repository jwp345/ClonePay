package com.fastcampuspay.money.application.port.in;

import com.fastcampuspay.money.domain.MemberMoney;

public interface FindMemberMoneyUseCase {
    MemberMoney findMemberMoney(FindMemberMoneyCommand command);
}
