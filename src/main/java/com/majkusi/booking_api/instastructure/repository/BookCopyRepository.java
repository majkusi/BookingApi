package com.majkusi.booking_api.instastructure.repository;

import com.majkusi.booking_api.domain.entity.BookCopyEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository extends JpaRepository< BookCopyEntity, Long > {
    List< BookCopyEntity > findByBook_Id( Long bookId );

    @Lock( LockModeType.PESSIMISTIC_WRITE )
    @Query( "SELECT b FROM BookCopyEntity b WHERE b.id = :id" )
    Optional< BookCopyEntity > findByIdWithLock( @Param( "id" ) Long id );
}
