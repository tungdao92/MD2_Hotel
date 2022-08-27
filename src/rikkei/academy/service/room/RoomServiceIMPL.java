package rikkei.academy.service.room;

import rikkei.academy.config.Config;
import rikkei.academy.model.Room;
import rikkei.academy.model.User;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
}
