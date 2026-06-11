package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.LoanResponse;
import com.majkusi.booking_api.application.exception.*;
import com.majkusi.booking_api.domain.BookStatus;
import com.majkusi.booking_api.domain.MemberStatus;
import com.majkusi.booking_api.domain.entity.BookCopyEntity;
import com.majkusi.booking_api.domain.entity.LoanEntity;
import com.majkusi.booking_api.domain.entity.MemberEntity;
import com.majkusi.booking_api.instastructure.repository.BookCopyRepository;
import com.majkusi.booking_api.instastructure.repository.LoanRepository;
import com.majkusi.booking_api.instastructure.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoanService {
    private final MemberRepository memberRepository;
    private final BookCopyRepository bookCopyRepository;
    private final LoanRepository loanRepository;

    public LoanService( MemberRepository memberRepository, BookCopyRepository bookCopyRepository, LoanRepository loanRepository ) {
        this.memberRepository = memberRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public LoanResponse create( Long bookCopyId, Long memberId ) {
        BookCopyEntity bookCopy = bookCopyRepository.findByIdWithLock( bookCopyId ).orElseThrow( ( ) -> new BookNotFoundException( "Book copy not found, check ID" ) );
        MemberEntity member = memberRepository.findById( memberId ).orElseThrow( ( ) -> new MemberNotFoundException( "Member not found" ) );
        if ( bookCopy.getStatus( ) != BookStatus.AVAILABLE ) {
            throw new BookCopyAlreadyLoanedException( "Book is not available at the moment" );
        } else if ( member.getStatus( ) == MemberStatus.SUSPENDED ) {
            throw new MemberSuspendedException( "Member is incorrect" );
        } else if ( checkUserOverdue( memberId ) ) {
            throw new MemberHasOverdueException( "Member has overdue books!" );
        } else if ( checkUserBooks( memberId ) ) {
            throw new LoanLimitExceededException( "Member has too many book loaned" );
        }
        bookCopy.setStatus( BookStatus.LOANED );
        LoanEntity loan = save( bookCopy, member );
        return toResponse( loan );
    }

    @Transactional
    public LoanResponse returnLoan( Long loanId ) {
        LoanEntity loan = loanRepository.findById( loanId ).orElseThrow( );
        loan.setReturnDate( LocalDate.now( ) );
        BookCopyEntity bookCopy = loan.getBookCopy( );
        bookCopy.setStatus( BookStatus.RETURNED );
        return toResponse( loan );
    }

    private boolean checkUserBooks( Long memberId ) {
        return loanRepository.checkUserBooks( memberId ) >= 5;
    }

    private boolean checkUserOverdue( Long memberId ) {
        return loanRepository.checkUserOverdue( memberId, LocalDate.now( ) ) >= 1;
    }

    private LoanEntity save( BookCopyEntity bookCopy, MemberEntity member ) {
        return loanRepository.save( new LoanEntity( bookCopy, member, LocalDate.now( ), LocalDate.now( ).plusWeeks( 2 ), null ) );
    }

    private LoanResponse toResponse( LoanEntity loan ) {
        return new LoanResponse( loan.getId( ), loan.getBookCopy( ).getId( ), loan.getMember( ).getId( ), loan.getStartDate( ), loan.getDueDate( ), Optional.ofNullable( loan.getReturnDate( ) ) );
    }
}
