package com.majkusi.booking_api.instastructure.repository;

import com.majkusi.booking_api.domain.entity.BookCopyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCopyRepository extends JpaRepository< BookCopyEntity, Long > {
    List< BookCopyEntity > findByBook_Id( Long bookId );
}
