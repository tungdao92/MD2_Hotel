package rikkei.academy.service.room;

import rikkei.academy.config.Config;
import rikkei.academy.controller.RoomController;
import rikkei.academy.model.Room;
import rikkei.academy.model.User;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoomServiceIMPL implements IRoomService, Serializable {
//    IRoomService roomService = new RoomServiceIMPL();
    IUserService userService = new UserServiceIMPL();
    public static String PATH_ROOM = "D:\\_____Case study MD2______\\src\\rikkei\\academy\\database\\room.txt";
    public static Config<List<Room>> roomConfig = new Config<>();
    public static List<Room> roomList = roomConfig.readFile(PATH_ROOM);
    static {
        if (roomList == null) {
            roomList = new ArrayList<>();
        }
    }
//static List<Room> roomList = new ArrayList<>();
//    static {
//        roomList.add(new Room(1,2,1500,true));
//        roomList.add(new Room(2,1,1000,true));
//        roomList.add(new Room(3,2,1500,true));
//        roomList.add(new Room(4,1,1000,true));
//        roomList.add(new Room(5,1,1000,true));
//        roomList.add(new Room(6,1,1000,true));
//        roomList.add(new Room(7,1,1000,true));
//        roomList.add(new Room(8,2,1500,true));
//        roomList.add(new Room(9,1,1000,true));
//
//
//
//    }

    @Override
    public List<Room> findAll() {
        roomConfig.writeFile(PATH_ROOM, roomList);
        return roomList;
    }

    @Override
    public void save(Room room) {
        User user = userService.getCurrentUser();
        room.setUser(user);
        roomList.add(room);

    }

    @Override
    public Room findById(int id) {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getId() == id) {
                return roomList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getId() == id) {
                roomList.remove(i);
            }
        }
    }

    @Override
    public void changeStatus(int id) {
        Room room = findById(id);
        room.setStatus(!room.isStatus());
        findAll();
    }

    @Override
    public void updateData() {
        roomConfig.writeFile(PATH_ROOM, roomList);
    }
}
