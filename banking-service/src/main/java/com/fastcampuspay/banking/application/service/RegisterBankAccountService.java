package com.fastcampuspay.banking.application.service;

import com.fastcampuspay.banking.adapter.out.external.bank.BankAccount;
import com.fastcampuspay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.fastcampuspay.banking.application.port.out.GetMembershipPort;
import com.fastcampuspay.banking.application.port.out.MembershipStatus;
import com.fastcampuspay.banking.application.port.out.RegisterBankAccountPort;
import com.fastcampuspay.banking.application.port.out.RequestBankAccountInfoPort;
import com.fastcampuspay.banking.domain.RegisteredBankAccount;
import com.fastcampuspay.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final GetMembershipPort getMembershipPort;
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper mapper;

    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        // 은행 계좌를 등록해야하는 서비스 (비즈니스 로직)

        // call membership svc, 정상인지 확인
        MembershipStatus membershipStatus = getMembershipPort.getMembership(command.getMembershipId());
        if(!membershipStatus.isValid()) {
            return null;
        }

        // 1. 외부 실제 은행에 등록이 가능한 계좌인지(정상인지) 확인한다.
        // 외부의 은행에 이 계좌 정상인지? 확인
        // Biz Logic -> External System
        // Hexagoal : Port -> Adapter -> External System
        // Port
        // 실제 외부의 은행계좌 정보를 Get

        // 실제 외부의 은행 계좌 정보
        BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean accountIsValid = accountInfo.isValid();

        // 2. 등록가능한 계좌라면, 등록한다. 성공하면, 등록에 성공한 등록 정보를 리턴
        // 2-1. 등록가능하지않은 계좌라면. 에러를 리턴

        if (accountIsValid) {
            RegisteredBankAccountJpaEntity savedAccountInfo = registerBankAccountPort.
                    findRegisteredBankAccount(new RegisteredBankAccount.MembershipId(command.getMembershipId()));

            if(savedAccountInfo == null) {
                savedAccountInfo = registerBankAccountPort.createRegisteredBankAccount(
                        new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                        new RegisteredBankAccount.BankName(command.getBankName()),
                        new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                        new RegisteredBankAccount.LinkedStatusIsValid(command.isValid())
                );
            }

            return mapper.mapToDomainEntity(savedAccountInfo);
        } else {
            return null;
        }

    }
}
