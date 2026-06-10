package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.BookCopyResponse;
import com.majkusi.booking_api.application.dto.LoanResponse;
import com.majkusi.booking_api.application.dto.MemberResponse;
import com.majkusi.booking_api.application.exception.BookCopyAlreadyLoanedException;
import com.majkusi.booking_api.application.exception.LoanLimitExceededException;
import com.majkusi.booking_api.application.exception.MemberHasOverdueException;
import com.majkusi.booking_api.application.exception.MemberSuspendedException;
import com.majkusi.booking_api.domain.BookStatus;
import com.majkusi.booking_api.domain.MemberStatus;
import com.majkusi.booking_api.domain.entity.LoanEntity;
import com.majkusi.booking_api.instastructure.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoanService {
    private final MemberService memberService;
    private final BookCopyService bookCopyService;
    private final LoanRepository loanRepository;

    public LoanService( MemberService memberService, BookCopyService bookCopyService, LoanRepository loanRepository ) {
        this.memberService = memberService;
        this.bookCopyService = bookCopyService;
        this.loanRepository = loanRepository;
    }

    public LoanResponse create( Long bookCopyId, Long memberId ) {
        BookCopyResponse bookCopy = bookCopyService.getById( bookCopyId ).orElseThrow( );
        MemberResponse member = memberService.getById( memberId ).orElseThrow( );
        if ( bookCopy.status( ) != BookStatus.AVAILABLE ) {
            throw new BookCopyAlreadyLoanedException( "Book is not available at the moment" );
        } else if ( member.status( ) == MemberStatus.SUSPENDED ) {
            throw new MemberSuspendedException( "Member is incorrect" );
        } else if ( checkUserOverdue( memberId ) ) {
            throw new MemberHasOverdueException( "Member has overdue books!" );
        } else if ( checkUserBooks( memberId ) ) {
            throw new LoanLimitExceededException( "Member has too many book loaned" );
        }
        LoanEntity loan = save( bookCopyId, memberId );
        return toResponse( loan );
    }

    public LoanResponse returnLoan( Long loanId ) {
        LoanEntity loan = loanRepository.findById( loanId ).orElseThrow( );
        loan.setReturnDate( LocalDate.now( ) );
        loanRepository.save( loan );
        return toResponse( loan );
    }

    private boolean checkUserBooks( Long memberId ) {
        long loanedBooks = loanRepository.findLoanEntitiesByMemberId( memberId ).stream( ).filter( l -> l.getReturnDate( ) == null ).count( );
        return loanedBooks >= 5;
    }

    private boolean checkUserOverdue( Long memberId ) {
        long overdue = loanRepository.findLoanEntitiesByMemberId( memberId ).stream( ).filter( l -> LocalDate.now( ).isAfter( l.getDueDate( ) ) && l.getReturnDate( ) == null ).count( );
        return overdue >= 1;
    }

    private LoanEntity save( Long bookCopyId, Long memberId ) {
        return loanRepository.save( new LoanEntity( bookCopyId, memberId, LocalDate.now( ), LocalDate.now( ).plusWeeks( 2 ), null ) );
    }

    private LoanResponse toResponse( LoanEntity loan ) {
        return new LoanResponse( loan.getId( ), loan.getBookCopyId( ), loan.getMemberId( ), loan.getStartDate( ), loan.getDueDate( ), Optional.ofNullable( loan.getReturnDate( ) ) );
    }
}
