package com.majkusi.booking_api.instastructure.repository;

import com.majkusi.booking_api.domain.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository< LoanEntity, Long > {
    List< LoanEntity > findByMember_Id( Long memberId );

    @Query( "SELECT COUNT(l) FROM LoanEntity l WHERE l.returnDate IS NULL AND l.member.id = :memberId " )
    long checkUserBooks( @Param( "memberId" ) Long memberId );

    @Query( "SELECT COUNT(l) FROM LoanEntity l WHERE l.dueDate < :today AND l.returnDate IS NULL AND l.member.id = :memberId " )
    long checkUserOverdue( @Param( "memberId" ) Long memberId, @Param( "today" ) LocalDate localDate );
}
