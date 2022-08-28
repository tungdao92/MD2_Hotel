package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.RoomController;
import rikkei.academy.model.Room;

import java.util.List;

public class ViewRoom {
    private RoomController roomController = new RoomController();
    List<Room> roomList = roomController.showListRoom();

    public void formCreateRoom(){
        int id;
        if(roomList.size() == 0){
            id = 1;
        } else {
            id = roomList.get(roomList.size() - 1).getId() + 1;
        }

       int type;
        while(true){
            System.out.println("Enter type - 1 or 2 bed");
            type = Integer.parseInt(Config.scanner().nextLine());
            if(type == 1 || type == 2){
                break;
            } else {
                System.out.println("The type is not! Please try again");
            }
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

        int price = 0;
        if (type == 1){
            price = 500;
        } else if(type == 2){
            price = 1000;
        }

        int totalMoney;
        totalMoney = days *price;
        System.out.println("Đặt phòng thành công");
        System.out.println(" Tổng tiền là: " + totalMoney + "K");
        System.out.println("Bạn có muốn sử dụng thêm gói dịch vụ vui vẻ đặc biệt từ A-Z trị giá 1000k của chúng tui bao gồm ghế tình iu, áo mưa có gai và ...., hãy đăng ký và cảm nhận dịch vụ.");
        System.out.println(" Bạn có muốn trải nghiệm? Y / N");
        String check = Config.scanner().nextLine();
        if (check.equalsIgnoreCase("y")){
            totalMoney = totalMoney + 1000;
            System.out.println("Cảm ơn đã sử dụng dịch vụ của chúng tôi, chúc vui vẻ");
            System.out.println("Tổng số tiền phải trả là: " + totalMoney + "K");
        } if (check.equalsIgnoreCase("n")){
            System.out.println("Cảm ơn quý khách");
        }

        System.out.println("Quý khách hãy mang chứng minh thư kèm mã đặt phòng tới quầy lễ tân để nhận phòng");
        Room room = new Room(id, type, days,price, totalMoney);
        roomController.createRoom(room);
        roomController.showListRoom();
        new ViewHome();

    }


    public void showListRoom(){
        System.out.printf("| %-10s | %-15s | %-15s | %-15s | %-15s | %n", "id", "type(Số giường)", "days", "price", "totalMoney");
        for (int i = 0; i < roomList.size(); i++){
            System.out.printf("| %-10s | %-15s | %-15s | %-15s | %-15s | %n", roomList.get(i).getId(), roomList.get(i).getTypeRoom(), roomList.get(i).getDays(), roomList.get(i).getPrice(), roomList.get(i).getTotalMoney());
        }
        new ViewHome();
    }


    public void showListRoomUser(){
        System.out.printf("| %-10s | %-15s | %-15s | %-15s | %-15s| %-15s | %n", "id", "type(Số giường)", "days", "price", "totalMoney","User");
        for (int i = 0; i < roomList.size(); i++){
            System.out.printf("| %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %n", roomList.get(i).getId(), roomList.get(i).getTypeRoom(),
                    roomList.get(i).getDays(), roomList.get(i).getPrice(), roomList.get(i).getTotalMoney(),roomList.get(i).getUser().getUsername());
        }
        new ViewHome();
    }

    public void deleteRoom(){
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
        } else if (check.equalsIgnoreCase("N")){
            new ViewHome();
        }
        new ViewHome();


    }

    private boolean isValid(int id){
        int size = roomController.showListRoom().size();
        return id >= 0 && id<= size;
    }

}
