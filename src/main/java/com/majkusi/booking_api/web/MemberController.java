package com.majkusi.booking_api.web;

import com.majkusi.booking_api.application.MemberService;
import com.majkusi.booking_api.application.dto.MemberResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/members" )
public class MemberController {
    private final MemberService memberService;

    public MemberController( MemberService memberService ) {
        this.memberService = memberService;
    }

    @GetMapping
    public List< MemberResponse > getMembers( ) {
        return memberService.getMembers( );
    }


}
