package com.majkusi.booking_api.web;

import com.majkusi.booking_api.application.MemberService;
import com.majkusi.booking_api.application.dto.MemberResponse;
import com.majkusi.booking_api.web.dto.CreateMemberRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/members" )
public class MemberController {
    private final MemberService memberService;

    public MemberController( MemberService memberService ) {
        this.memberService = memberService;
    }

    @GetMapping
    public List< MemberResponse > getUsers( ) {
        return memberService.getMembers( );
    }

    @PostMapping
    public MemberResponse createMember( @Valid @RequestBody CreateMemberRequest request ) {
        return memberService.create( request.name( ), request.email( ) );
    }
}
