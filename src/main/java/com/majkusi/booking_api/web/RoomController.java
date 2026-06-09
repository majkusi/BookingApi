package com.majkusi.booking_api.web;

import com.majkusi.booking_api.application.RoomService;
import com.majkusi.booking_api.web.dto.CreateRoomRequest;
import com.majkusi.booking_api.web.dto.RoomResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/rooms" )
public class RoomController {

    private final RoomService roomService;

    public RoomController( RoomService roomService ) {
        this.roomService = roomService;
    }

    @GetMapping
    public List< RoomResponse > getAll( ) {
        return roomService.getAllAsResponse( );
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public RoomResponse createRoom( @Valid @RequestBody CreateRoomRequest request ) {
        return roomService.create( request );
    }
}
