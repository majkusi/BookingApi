package com.majkusi.booking_api.application;

import com.majkusi.booking_api.domain.Room;
import com.majkusi.booking_api.web.dto.CreateRoomRequest;
import com.majkusi.booking_api.web.dto.RoomResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RoomService {
    private final Map< Long, Room > rooms = new ConcurrentHashMap<>( );
    private final AtomicLong idGenerator = new AtomicLong( );

    public RoomService( ) {
        add( new Room( null, "Sala A", 10 ) );
        add( new Room( null, "Sala B", 20 ) );
        add( new Room( null, "Sala C", 50 ) );
    }

    public RoomResponse create( CreateRoomRequest request ) {
        Room room = new Room( null, request.name( ), request.capacity( ) );
        Room saved = add( room );
        return toResponse( saved );
    }


    public List< Room > getAll( ) {
        return List.copyOf( rooms.values( ) );
    }

    public Optional< Room > getById( Long id ) {
        return Optional.ofNullable( rooms.get( id ) );
    }

    public Room add( Room room ) {
        Long id = idGenerator.getAndIncrement( );
        Room roomWithId = new Room( id, room.name( ), room.capacity( ) );
        rooms.put( id, roomWithId );
        return roomWithId;
    }

    public List< RoomResponse > getAllAsResponse( ) {
        return getAll( ).stream( )
                .map( room -> new RoomResponse( room.id( ), room.name( ), room.capacity( ) ) )
                .toList( );
    }

    private RoomResponse toResponse( Room room ) {
        return new RoomResponse( room.id( ), room.name( ), room.capacity( ) );
    }

}
