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

    public LoanResponse create( Long bookCopyId, Long memberId ) {
        BookCopyEntity bookCopy = bookCopyRepository.findById( bookCopyId ).orElseThrow( ( ) -> new BookNotFoundException( "Book copy not found, check ID" ) );
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
        bookCopyRepository.save( bookCopy );
        LoanEntity loan = save( bookCopy, member );
        return toResponse( loan );
    }

    public LoanResponse returnLoan( Long loanId ) {
        LoanEntity loan = loanRepository.findById( loanId ).orElseThrow( );
        loan.setReturnDate( LocalDate.now( ) );
        BookCopyEntity bookCopy = loan.getBookCopy( );
        bookCopy.setStatus( BookStatus.RETURNED );
        bookCopyRepository.save( bookCopy );
        loanRepository.save( loan );
        return toResponse( loan );
    }

    private boolean checkUserBooks( Long memberId ) {
        long loanedBooks = loanRepository.findByMember_Id( memberId ).stream( ).filter( l -> l.getReturnDate( ) == null ).count( );
        return loanedBooks >= 5;
    }

    private boolean checkUserOverdue( Long memberId ) {
        long overdue = loanRepository.findByMember_Id( memberId ).stream( ).filter( l -> LocalDate.now( ).isAfter( l.getDueDate( ) ) && l.getReturnDate( ) == null ).count( );
        return overdue >= 1;
    }

    private LoanEntity save( BookCopyEntity bookCopy, MemberEntity member ) {
        return loanRepository.save( new LoanEntity( bookCopy, member, LocalDate.now( ), LocalDate.now( ).plusWeeks( 2 ), null ) );
    }

    private LoanResponse toResponse( LoanEntity loan ) {
        return new LoanResponse( loan.getId( ), loan.getBookCopy( ), loan.getMember( ), loan.getStartDate( ), loan.getDueDate( ), Optional.ofNullable( loan.getReturnDate( ) ) );
    }
}
