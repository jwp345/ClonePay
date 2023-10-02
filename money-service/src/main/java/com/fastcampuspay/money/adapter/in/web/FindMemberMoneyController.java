package com.fastcampuspay.money.adapter.in.web;

import com.fastcampuspay.common.WebAdapter;
import com.fastcampuspay.money.application.port.in.FindMemberMoneyCommand;
import com.fastcampuspay.money.application.port.in.FindMemberMoneyUseCase;
import com.fastcampuspay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMemberMoneyController {
    private final FindMemberMoneyUseCase findMoneyUseCase;

    @GetMapping(path = "/money/{membershipId}")
    ResponseEntity<MemberMoney> findMembershipByMemberId(@PathVariable String membershipId) {

        FindMemberMoneyCommand command = FindMemberMoneyCommand.builder()
                .membershipId(membershipId)
                .build();


        return ResponseEntity.ok(findMoneyUseCase.findMemberMoney(command));
    }
}
