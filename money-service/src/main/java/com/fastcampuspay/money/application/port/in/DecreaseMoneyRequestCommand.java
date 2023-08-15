package com.fastcampuspay.money.application.port.in;


import com.fastcampuspay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class DecreaseMoneyRequestCommand extends SelfValidating<DecreaseMoneyRequestCommand> {

    @NotNull
    public final String targetMembershipId;

    @NotNull
    private final int amount;


    public DecreaseMoneyRequestCommand(@NotNull String targetMembershipId, int amount) {
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;
        this.validateSelf();
    }
}
