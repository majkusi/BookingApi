package com.majkusi.booking_api.web;

import com.majkusi.booking_api.application.AuthService;
import com.majkusi.booking_api.web.dto.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/auth" )
public class AuthController {

    private final AuthService authService;

    public AuthController( AuthService authService ) {
        this.authService = authService;
    }

    @PostMapping( "/register" )
    @ResponseStatus( HttpStatus.CREATED )
    public String register( @RequestBody RegisterRequest request ) {
        authService.register( request );
        return "todo";
    }
}
