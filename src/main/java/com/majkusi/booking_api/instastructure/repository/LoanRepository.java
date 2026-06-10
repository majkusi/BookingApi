package com.majkusi.booking_api.instastructure.repository;

import com.majkusi.booking_api.domain.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository< LoanEntity, Long > {
    List< LoanEntity > findLoanEntitiesByMemberId( Long memberId );
}
