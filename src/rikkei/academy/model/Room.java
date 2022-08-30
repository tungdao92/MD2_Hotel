package rikkei.academy.model;

import java.io.Serializable;

public class Room implements Serializable {
    private int id;
    private int typeRoom;
    private int days;
    private int price;

    private int totalMoney;
    private User user;

    private boolean status;

    public Room(int id, int days, int totalMoney) {
        this.id = id;
        this.days = days;
        this.totalMoney = totalMoney;
    }

    public Room(int id, int typeRoom,int price, boolean status) {
        this.id = id;
        this.typeRoom = typeRoom;
        this.price = price;
        this.status = status;
    }

    public Room(int id, int typeRoom, int days, int price, int totalMoney, User user, boolean status) {
        this.id = id;
        this.typeRoom = typeRoom;
        this.days = days;
        this.price = price;
        this.totalMoney = totalMoney;
        this.user = user;
        this.status = status;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(int typeRoom) {
        this.typeRoom = typeRoom;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", typeRoom=" + typeRoom +
                ", days=" + days +
                ", price=" + price +
                ", totalMoney=" + totalMoney +
                ", user=" + user +
                '}';
    }
}
