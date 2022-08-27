package rikkei.academy.model;

import java.io.Serializable;

public class Room implements Serializable {
    private int id;
    private int typeRoom;
    private int days;
    private int price;

    private int totalMoney;
    private User user;


    public Room(int id, int typeRoom, int days, int price, int totalMoney, User user) {
        this.id = id;
        this.typeRoom = typeRoom;
        this.days = days;
        this.price = price;
        this.totalMoney = totalMoney;
        this.user = user;
    }

    public Room(int id, int type, int days, int price, int totalMoney) {
        this.id = id;
        this.typeRoom = type;
        this.days = days;
        this.price = price;
        this.totalMoney = totalMoney;
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
