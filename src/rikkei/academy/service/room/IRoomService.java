package rikkei.academy.service.room;

import rikkei.academy.model.Room;
import rikkei.academy.service.IGenericService;

public interface IRoomService extends IGenericService<Room> {
    void changeStatus(int id);
    void updateData();
}
