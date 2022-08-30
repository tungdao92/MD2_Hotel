package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.ArrayList;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

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
        }
    }


    private void ViewAdmin() {
        System.out.println("WELCOME ADMIN:  " + currentUser.getName());
        System.out.println("1: Manager User");
        System.out.println("2: Category Manager");
        System.out.println("3: Show list of Room");
        System.out.println("4: Hủy phòng khách đặt");
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
                new ViewRoom().showListRoomUser();
                break;
            case 9:
                new ViewMenu().changePassword();
                break;

            case 0:
                userController.logout();
                new ViewMenu().menu();
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
        System.out.println("WELCOME PM: " + currentUser.getName());
        System.out.println("0: LogOut");

        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                userController.logout();
                new ViewMenu().menu();
                break;
        }
    }

    private void ViewUser() {
        System.out.println("WELCOME USER:  " + currentUser.getName());
        System.out.println("1: Book room");
        System.out.println("2: List Booked room");
        System.out.println("3: Delete booked room");
        System.out.println("9: Change password");
        System.out.println("0: LogOut");

        int choiceUser = Integer.parseInt(Config.scanner().nextLine());
        switch (choiceUser) {
            case 1:
                new ViewRoom().formCreateRoom();
                break;
            case 2:
                new ViewRoom().showListRoom();
                break;
            case 3:
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
