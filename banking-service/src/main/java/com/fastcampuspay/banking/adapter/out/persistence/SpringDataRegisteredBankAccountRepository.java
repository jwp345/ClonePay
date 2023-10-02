package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.domain.RegisteredBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRegisteredBankAccountRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
    RegisteredBankAccountJpaEntity findByMembershipId(String membershipId);
}
