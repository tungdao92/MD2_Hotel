package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.ArrayList;

public class ViewHome {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();

    public ViewHome() {
        switch (roleName) {
            case ADMIN:
                ViewAdmin();
                break;
            case USER:
                ViewUser();
                break;
            case PM:
                ViewPM();
                break;
        }
    }


    private void ViewAdmin() {
        System.out.println("WELCOME ADMIN:  " + currentUser.getName());
        System.out.println("1: User Manager");
        System.out.println("2: Category Manager");
        System.out.println("3: Room Manager");
        System.out.println("4: ");
        System.out.println("5: ");
        System.out.println("6: ");
        System.out.println("9: Change passwords");
        System.out.println("0: LogOut");
        int choiceAdmin = Integer.parseInt(Config.scanner().nextLine());
        switch (choiceAdmin) {
            case 1:
                managerUser();
                break;
            case 2:
                new ViewCategory().menuCategory();
                break;
            case 3:
                managerRoom();
                break;
            case 4:
                new ViewRoom().showListRoom();
                break;
            case 9:
                new ViewMenu().changePassword();
                break;

            case 0:
                userController.logout();
                new ViewMenu().menu();
        }
    }

    public void managerRoom() {
        System.out.println("ROOM MANAGER");
        System.out.println("1: Create a new room");
        System.out.println("2: List Room ");
        System.out.println("3: Delete a room");
        System.out.println("4: Room booked");
        System.out.println("9: Tổng doanh thu");
        System.out.println("0: Back menu");

        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                new ViewRoom().creatRoom();
                break;
            case 2:
                new ViewRoom().showListRoom();
                break;
            case 3:
                new ViewRoom().deleteRoom();
                break;
                case 4:
                    new ViewRoom().showListRoomBooked();
                    break;
            case 9:
                new ViewRoom().totalMoneyRoom();
            case 0:
                new ViewHome();
                break;

        }
    }

    private void managerUser() {
        System.out.println("USER MANAGER");
        System.out.println("1: Delete user");
        System.out.println("2: Change Role");
        System.out.println("3: Block user");
        System.out.println("4: List users");
        System.out.println("9: Back Menu");


        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                new ViewMenu().deleteUser();
                break;
            case 2:
                new ViewMenu().changeRole();
                break;
            case 3:
                new ViewMenu().blockUser();
                break;
            case 4:
                new ViewMenu().showListUser();
                break;
            case 9:
                new ViewRoom().totalMoneyRoom();
                break;


        }
    }



    public void ViewPM() {
        System.out.println("WELCOME PM:  " + currentUser.getName());
        System.out.println("1: User Manager");
        System.out.println("2: Category Manager");
        System.out.println("3: Room Manager");
        System.out.println("4: ");
        System.out.println("5: ");
        System.out.println("5: ");
        System.out.println("9: Change passwords");
        System.out.println("0: LogOut");
        int choiceAdmin = Integer.parseInt(Config.scanner().nextLine());
        switch (choiceAdmin) {
            case 1:
                managerUser();
                break;
            case 2:
                new ViewCategory().menuCategory();
                break;
            case 3:
                managerRoom();
                break;
            case 4:
                new ViewRoom().showListRoom();
                break;
            case 9:
                new ViewMenu().changePassword();
                break;

            case 0:
                userController.logout();
                new ViewMenu().menu();
        }
    }

    private void ViewUser() {
        System.out.println("WELCOME USER:  " + currentUser.getName());
        System.out.println("1: List room Empty");
        System.out.println("2: Book room");
        System.out.println("3: Cancel booked room");
        System.out.println("4: Room booked and total, days");
        System.out.println("9: Change password");
        System.out.println("0: LogOut");

        int choiceUser = Integer.parseInt(Config.scanner().nextLine());
        switch (choiceUser) {
            case 1:
                new ViewRoom().showListRoom();
                break;
            case 2:
                new ViewRoom().bookRoom();
                break;
            case 4:
                new ViewRoom().showListRoomBooked();
                break;
            case 9:
                new ViewMenu().changePassword();
                break;
            case 0:
                userController.logout();
                new ViewMenu().menu();
                break;
        }
    }

}
