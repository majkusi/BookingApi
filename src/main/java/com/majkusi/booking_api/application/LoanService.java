package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.BookCopyResponse;
import com.majkusi.booking_api.application.dto.LoanResponse;
import com.majkusi.booking_api.application.dto.MemberResponse;
import com.majkusi.booking_api.application.exception.BookCopyAlreadyLoanedException;
import com.majkusi.booking_api.application.exception.LoanLimitExceededException;
import com.majkusi.booking_api.application.exception.MemberHasOverdueException;
import com.majkusi.booking_api.application.exception.MemberSuspendedException;
import com.majkusi.booking_api.domain.BookStatus;
import com.majkusi.booking_api.domain.Loan;
import com.majkusi.booking_api.domain.MemberStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LoanService {
    private final MemberService memberService;
    private final BookCopyService bookCopyService;
    private final AtomicLong idGenerator = new AtomicLong( );
    private final Map< Long, Loan > loans = new ConcurrentHashMap<>( );

    public LoanService( MemberService memberService, BookCopyService bookCopyService ) {
        this.memberService = memberService;
        this.bookCopyService = bookCopyService;
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
        Loan loan = add( bookCopyId, memberId );
        return toResponse( loan );
    }

    public LoanResponse returnLoan( Long loanId ) {
        Loan loan = Optional.ofNullable( loans.get( loanId ) ).orElseThrow( );
        Loan newLoan = new Loan( loan.id( ), loan.bookCopyId( ), loan.memberId( ), loan.startDate( ), loan.dueDate( ), Optional.of( LocalDate.now( ) ) );
        loans.put( loanId, newLoan );
        return toResponse( newLoan );
    }

    private boolean checkUserBooks( Long memberId ) {
        long loanedBooks = loans.values( ).stream( ).filter( l -> l.memberId( ).equals( memberId ) && l.returnDate( ).isEmpty( ) ).count( );
        return loanedBooks >= 5;
    }

    private boolean checkUserOverdue( Long memberId ) {
        long overdue = loans.values( ).stream( ).filter( l -> l.memberId( ).equals( memberId ) && LocalDate.now( ).isAfter( l.dueDate( ) ) && l.returnDate( ).isEmpty( ) ).count( );
        return overdue >= 1;
    }

    private Loan add( Long bookCopyId, Long memberId ) {
        long id = idGenerator.getAndIncrement( );
        Loan loanWithId = new Loan( id, bookCopyId, memberId, LocalDate.now( ), LocalDate.now( ).plusWeeks( 2 ), Optional.empty( ) );
        loans.put( id, loanWithId );
        return loanWithId;
    }

    private LoanResponse toResponse( Loan loan ) {
        return new LoanResponse( loan.id( ), loan.bookCopyId( ), loan.memberId( ), loan.startDate( ), loan.dueDate( ), loan.returnDate( ) );
    }
}
