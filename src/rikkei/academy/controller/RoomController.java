package rikkei.academy.controller;

import rikkei.academy.model.Room;
import rikkei.academy.service.room.IRoomService;
import rikkei.academy.service.room.RoomServiceIMPL;

import java.util.List;

public class RoomController {

    private IRoomService roomService = new RoomServiceIMPL();

    public List<Room> showListRoom(){
        return roomService.findAll();
    }

    public void createRoom( Room room ) {
        roomService.save(room);
        showListRoom();
    }

    public void editRoom( int id, Room room ) {
        Room room1 = roomService.findById(id);
        room.setTypeRoom(room.getTypeRoom());
        room.setDays(room.getDays());

    }

    public Room searchRoom(int id){
        return roomService.findById(id);
    }

    public void deleteRoom( int id ) {
        roomService.deleteById(id);
    }
}
