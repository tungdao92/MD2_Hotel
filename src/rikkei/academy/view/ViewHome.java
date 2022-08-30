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
                new ViewRoom().showRoomEmpty();
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
        System.out.println("MANAGER ROOM");
        System.out.println("1: Create a new room");
        System.out.println("2: List Room ");

        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                new ViewRoom().creatRoom();
                break;
                case 2:
                    new ViewRoom().showRoomEmpty();
                    break;

        }
    }

    private void managerUser() {
        System.out.println("MANAGER USER");
        System.out.println("1: Delete user");
        System.out.println("2: Change Role");
        System.out.println("3: Block user");
        System.out.println("4: List users");
        System.out.println("9: Back Menu");
        System.out.println("0: LogOut");


        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                new ViewMenu().deleteUser();
                break;
            case 2:
                break;
            case 3:
                new ViewMenu().blockUser();
                break;
            case 4:
                new ViewMenu().showListUser();
                break;
            case 9:
                new ViewHome();
                break;
            case 0:
                userController.logout();
                new ViewMenu().menu();
                break;

        }
    }


    public void ViewPM() {
        System.out.println("WELCOME PM:  " + currentUser.getName());
        System.out.println("1: User Manager");
        System.out.println("2: Category Manager");
        System.out.println("3: List Room booked");
        System.out.println("4: List Room empty");
        System.out.println("5: Hủy phòng khách đặt");
        System.out.println("5: Thay đổi phòng khách đặt");
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

                case 4:
                    new ViewRoom().showRoomEmpty();
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
        System.out.println("3: List Booked room");
        System.out.println("4: Delete booked room");
        System.out.println("9: Change password");
        System.out.println("0: LogOut");

        int choiceUser = Integer.parseInt(Config.scanner().nextLine());
        switch (choiceUser) {
            case 1:
                new ViewRoom().showRoomEmpty();
                break;
            case 2:
                new ViewRoom().bookRoom();
                break;
            case 4:
                new ViewRoom().deleteRoom();
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
