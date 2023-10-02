package com.fastcampuspay.banking.application.port.in;


import com.fastcampuspay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestFirmbankingCommand extends SelfValidating<RequestFirmbankingCommand> {

    @NotNull
    public final String fromMembershipId;

    @NotNull
    private final String toBankName;

    @NotNull
    private final String toBankAccountNumber;

    @NotNull
    private final int moneyAmount;


    public RequestFirmbankingCommand(String fromMembershipId, String toBankName, String toBankAccountNumber, int moneyAmount) {
        this.fromMembershipId = fromMembershipId;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
    }
}
