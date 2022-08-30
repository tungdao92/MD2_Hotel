package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.RoomController;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.Room;

import java.util.List;

public class ViewRoom {
    private RoomController roomController = new RoomController();
    List<Room> roomList = roomController.showListRoom();



    public void creatRoom() {
        int id;
        if (roomList.size() == 0) {
            id = 1;
        } else {
            id = roomList.get(roomList.size() - 1).getId() + 1;
        }
        System.out.println("Enter type room");
        int type = Integer.parseInt(Config.scanner().nextLine());
        System.out.println("Enter Price room");
        int price = Integer.parseInt(Config.scanner().nextLine());
        boolean status = true;
        Room room = new Room(id, type, price, status);
        roomController.createRoom(room);
        System.out.println("Create room successfully");
            System.out.println("Enter 'back' to back menu");
            String back = Config.scanner().nextLine();
            if (back.equalsIgnoreCase("back")) {
                new ViewHome();
            }

    }


    public void showListRoom(){
        System.out.println("---______SHOW_ROOMS_EMPTY_____----");
        System.out.printf("%-10s |%-15s |%-15s| %-15s %n", "ID", "TYPE", "PRICE", "STATUS ROOM");
        for (Room room : roomList) {
            System.out.printf("%-10d |%-15s |%-15d |%-15s %n ", room.getId(),room.getTypeRoom(), room.getPrice() ,(room.isStatus()?"Empty": "Not Empty" ));
        }
//        System.out.println("Enter 'back' to back menu");
//        String back = Config.scanner().nextLine();
//        if (back.equalsIgnoreCase("back")) {
//            new ViewHome();
//        }

    }


    public void bookRoom(){
        showListRoom();
        System.out.println("Enter id want book");
        int id = Integer.parseInt(Config.scanner().nextLine());
        ResponseMessenger messenger = roomController.changeStatusRoom(id);
        switch (messenger.getMessage()){
            case "not_found":
                System.out.println("ID not found");
                break;
            case "booking":
                System.out.println("Room " + id + " has been booked");
                break;
            case "cancel_booking":
                System.out.println("Room " + id + "has been cancelled");
                break;
        }


        int days;
        while(true){
            System.out.println("Nhập số ngày thuê");
            days = Integer.parseInt(Config.scanner().nextLine());
            if(days > 0){
                break;
        } else {
                System.out.println("Nhập lại");
            }
        }



        int totalMoney;
        totalMoney = days * roomList.get(id).getPrice();
        System.out.println("Đặt phòng thành công");
        System.out.println(" Tổng tiền là: " + totalMoney + "K");
        System.out.println("Bạn có muốn dùng thêm dịch vụ 'từ A tới Z' của chúng tôi. Giá là 1000k. (Y / N)");
        String check = Config.scanner().nextLine();
        if(check.equalsIgnoreCase("Y")) {
            System.out.println("Chúc bạn vui vẻ");
            totalMoney = totalMoney + 1000;
            System.out.println("Tổng tiền phải thanh toán: " + totalMoney);
        } if(check.equalsIgnoreCase("N")) {
            System.out.println("Thank you!");
        }

        System.out.println("Quý khách hãy mang chứng minh thư kèm mã đặt phòng tới quầy lễ tân để nhận phòng");
        Room room = new Room(id, days, totalMoney);
        roomController.bookRoom(id, room);
        System.out.printf("%-10s |%-15s |%-15s| %-15s |%-15s|%-15s %n", "ID", "TYPE", "PRICE", "DAYS","TOTAL MONEY","STATUS ROOM");
        for (int i = 0; i < roomList.size(); i++) {
            System.out.printf("%-10s |%-15s |%-15s| %-15s |%-15s |%-15s %n",roomList.get(i).getId(),roomList.get(i).getTypeRoom(),roomList.get(i).getPrice(),roomList.get(i).getDays(),roomList.get(i).getTotalMoney(), (roomList.get(i).isStatus()? "Empty":"Not Empty"));

        }

        new ViewHome();

    }


    public void showListRoomBooked() {
        System.out.printf("%-10s |%-15s |%-15s| %-15s |%-15s %n", "ID", "TYPE", "PRICE", "DAYS","TOTAL MONEY");
        for (Room room : roomList) {
            System.out.printf("%-10d |%-15s |%-15d |%-15d |%-15s%n ", room.getId(),room.getTypeRoom(), room.getPrice(),room.getDays() ,room.getTotalMoney());
        }

    }




    public void deleteRoom(){
        showListRoom();
        System.out.println("Enter ID room want delete");
        int id = Integer.parseInt(Config.scanner().nextLine());
        if (!isValid(id)){
            System.out.println("Not pound");
            new ViewHome();
        }
        System.out.println("You want to delete ( Y/N ) ?");
        String check = Config.scanner().nextLine();
        if (check.equalsIgnoreCase("Y")){
            roomController.deleteRoom(id);
            roomController.showListRoom();
            showListRoom();
            System.out.println("Delete successfully");
        } else if (check.equalsIgnoreCase("N")){
            new ViewHome();
        }
        new ViewHome();


    }

    private boolean isValid(int id){
        int size = roomController.showListRoom().size();
        return id >= 0 && id<= size;
    }



    public void totalMoneyRoom() {
        int totalMoneyRoom = 0;
        for (int i = 0; i < roomList.size(); i++) {
            totalMoneyRoom += roomList.get(i).getTotalMoney();
            System.out.println(roomList.get(i).getTotalMoney());
        }
        System.out.println(" Tổng doanh thu là: "+totalMoneyRoom+ " K");
    }
}
