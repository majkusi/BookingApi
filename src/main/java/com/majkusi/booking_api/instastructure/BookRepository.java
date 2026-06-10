package com.majkusi.booking_api.instastructure;

import com.majkusi.booking_api.domain.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository< BookEntity, Long > {
}
