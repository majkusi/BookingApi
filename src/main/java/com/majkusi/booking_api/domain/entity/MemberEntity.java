package com.majkusi.booking_api.domain.entity;

import com.majkusi.booking_api.domain.MemberStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table( name = "members" )
public class MemberEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    Long id;
    String name;
    String email;
    LocalDate registerDate;
    @Enumerated( EnumType.STRING )
    MemberStatus status;
    String password;

    protected MemberEntity( ) {
    }

    public MemberEntity( String name, String email, String password, LocalDate registerDate, MemberStatus status ) {
        this.name = name;
        this.email = email;
        this.registerDate = registerDate;
        this.status = status;
        this.password = password;
    }

    public String getPassword( ) {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Long getId( ) {
        return id;
    }

    public String getName( ) {
        return name;
    }

    public String getEmail( ) {
        return email;
    }

    public LocalDate getRegisterDate( ) {
        return registerDate;
    }

    public MemberStatus getStatus( ) {
        return status;
    }
}

