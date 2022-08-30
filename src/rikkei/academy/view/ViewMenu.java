package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.RoomController;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.Room;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ViewMenu {
    UserController userController = new UserController();
    RoomController roomController = new RoomController();
    List<User> userList = userController.showListUser();
    List<Room> roomList = roomController.showListRoom();

    public void menu() {
        System.out.println("---______MENU_____----");
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("3: Show Room Empty");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                formRegister();
                break;
            case 2:
                formLogin();
                break;
            case 3:
                new ViewMenu().showRoomEmptyMenu();
                break;
        }
    }





    private void formRegister() {
        System.out.println("---______FORM_REGISTER_____----");
        int id;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }


        String name;
        while (true) {
            System.out.println("Enter name: ");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z ]{1,20}")) {
                break;
            } else {
                System.out.println("Invalid name, please try again.");
            }
        }


        String username;
        while (true) {
            System.out.println("Enter username: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z0-9]{1,20}")) {
                break;
            } else {
                System.out.println("Invalid username, please try again.");
            }
        }

        String email;
        while (true) {
            System.out.println("Enter email: ");
            email = Config.scanner().nextLine();
            if (email.matches(".+@.+")) {
                break;
            } else {
                System.out.println("Invalid email, please try again.");
            }
        }

        String password;
        while (true) {
            System.out.println("Enter password: ");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,20}")) {
                break;
            } else {
                System.out.println("Invalid password, please try again.");
            }
        }

        String role = "user";
        Set<String> strRoles = new HashSet<>();
        strRoles.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(id, name, username, email, password, strRoles);

        ResponseMessenger responseMessenger = userController.register(signUpDTO);

        switch (responseMessenger.getMessage()) {
            case "username_existed":
                System.out.println("Username already existed.");
                new ViewMenu().menu();
                break;
            case "email_existed":
                System.out.println("Email already existed");
                new ViewMenu().menu();
                break;
            case "invalid_role":
                System.out.println("Invalid role, please try again.");
                new ViewMenu().menu();
                break;
            case "success":
                System.out.println("Register successful");
                new ViewMenu().menu();
                break;
        }

    }


    public void formLogin() {
        String username;
        boolean validateUsername;
        while (true) {
            System.out.println("Enter the username: ");
            username = Config.scanner().nextLine();
            validateUsername = Pattern.matches("[a-zA-Z0-9]{1,20}", username);
            if (validateUsername) {
                break;
            } else {
                System.err.println("The username failed! Please try again!");
            }
        }

        String password;
        boolean validatePassword;
        while (true) {
            System.out.println("Enter the password: ");
            password = Config.scanner().nextLine();
            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,20}", password);
            if (validatePassword) {
                break;
            } else {
                System.err.println("The password failed! Please try again!");
            }
        }
        ResponseMessenger messenger = userController.login(new SignInDTO(username, password));
        switch (messenger.getMessage()) {
            case "blocked":
                System.out.println("The user is blocked");
                break;
            case "login_successful":
                System.out.println("Login successful");
                new ViewHome();
                break;
            case "login_failed":
                System.out.println("Username or password is incorrect");
        }
    }

    public void showListUser() {
        System.out.printf("%-10s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s|%-15s| %n", "ID", "NAME", "USERNAME", "EMAIL", "AVATAR", "STATUS", "ROLE", "PASSWORD");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf("%-10s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s|%-15s| %n", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUsername(),
                    userList.get(i).getEmail(), userList.get(i).getAvatar(), (userList.get(i).isStatus()?"Blocked":"Not Blocked"), userList.get(i).getRoles().iterator().next().getRoleName(), userList.get(i).getPassword());
        }
//        new ViewHome();
    }

    public void deleteUser() {
        System.out.println("Enter ID user want delete");
        int id = Integer.parseInt(Config.scanner().nextLine());
        if (!isValid(id)) {
            System.out.println("Not pound");
            new ViewHome();
        }
        System.out.println("You want to delete ( Y/N ) ?");
        String check = Config.scanner().nextLine();
        if (check.equalsIgnoreCase("Y")) {
            userController.deleteUser(id);
            userController.showListUser();
            showListUser();
        } else if (check.equalsIgnoreCase("N")) {
            new ViewHome();
        }
        new ViewHome();
    }


    private boolean isValid(int id) {
        int size = userController.showListUser().size();
        return id >= 0 && id <= size;
    }

    public void changePassword() {
        String oldPassword;
        while (true) {
            System.out.println("Enter old password");
            oldPassword = Config.scanner().nextLine();
            if (oldPassword.matches("[a-zA-Z0-9]{1,20}")) {
                break;
            } else {
                System.out.println("Passwords do not match");
            }
        }

        System.out.println("Enter new password");
        String newPassword = Config.scanner().nextLine();
        System.out.println("Repeat the new password");
        String newPasswordRepeat = Config.scanner().nextLine();
        if (!newPasswordRepeat.equals(newPassword)) {
            System.out.println("New Passwords do not match");
        } else {
            ResponseMessenger responseMessenger = userController.changePassword(oldPassword, newPassword);
            switch (responseMessenger.getMessage()) {
                case "not_match":
                    System.out.println("Old password dose not matches");
                    break;
                case "success":
                    System.out.println("Change password successful");
                    userController.logout();
                    new ViewMenu().menu();
            }

        }

    }

    public void  showRoomEmptyMenu() {
        System.out.println("---______SHOW_ROOMS_EMPTY_____----");
        System.out.printf("%-10s |%-15s |%-15s| %-15s %n", "ID", "TYPE", "PRICE", "STATUS ROOM");
        for (Room room : roomList) {
            System.out.printf("%-10d |%-15s |%-15d |%-15s %n ", room.getId(), room.getTypeRoom(), room.getPrice(), (room.isStatus() ? "Empty" : "Not Empty"));
        }
        System.out.println("Enter 'back' to back menu");
        String back = Config.scanner().nextLine();
        if (back.equalsIgnoreCase("back")) {
            new ViewMenu().menu();
        }
    }


        public void blockUser() {
        showListUser();
            System.out.println("Enter id user to block or unblock");
            int id = Config.scanner().nextInt();
            ResponseMessenger messenger = userController.blockUser(id);

            switch (messenger.getMessage()) {
                case "not_fond":
                    System.out.println("ID user not found");
                    break;
                case "blocked":
                    System.out.println("You just blocked user id " + id);
                    break;
                case "unblocked":
                    System.out.println("You just unblocked user id " + id);
                    break;
            }
        }

}
