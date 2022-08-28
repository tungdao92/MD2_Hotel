package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.RoomController;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.Room;
import rikkei.academy.model.User;

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
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                formRegister();
                break;
            case 2:
                formLogin();
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


        System.out.println("Enter Role");
        String role = Config.scanner().nextLine();
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


    public void formLogin(){
        String username;
        boolean validateUsername;
        while (true){
            System.out.println("Enter the username: ");
            username = Config.scanner().nextLine();
            validateUsername = Pattern.matches("[a-zA-Z0-9]{1,10}",username);
            if(validateUsername){
                break;
            } else {
                System.err.println("The username failed! Please try again!");
            }
        }

        String password;
        boolean validatePassword;
        while (true){
            System.out.println("Enter the password: ");
            password = Config.scanner().nextLine();
            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}",password);
            if(validatePassword){
                break;
            } else {
                System.err.println("The password failed! Please try again!");
            }
        }
        ResponseMessenger messenger = userController.login(new SignInDTO(username, password));
        if(messenger.getMessage().equals("login_failed")){
            System.err.println("Login Filed");
            formLogin();
        } else {
            new  ViewHome();

        }
    }

    public void showListUser() {
        System.out.printf("%-10s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s|%-15s| %n", "ID", "NAME", "USERNAME", "EMAIL", "AVATAR", "STATUS", "ROLE","PASSWORD");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf("%-10s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s|%-15s| %n", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUsername(),
                    userList.get(i).getEmail(), userList.get(i).getAvatar(), userList.get(i).isStatus(), userList.get(i).getRoles().iterator().next().getRoleName(), userList.get(i).getPassword());
        }
        new ViewHome();
    }

    public void deleteUser(){
        System.out.println("Enter ID user want delete");
        int id = Integer.parseInt(Config.scanner().nextLine());
        if (!isValid(id)){
            System.out.println("Not pound");
            new ViewHome();
        }
        System.out.println("You want to delete ( Y/N ) ?");
        String check = Config.scanner().nextLine();
        if (check.equalsIgnoreCase("Y")){
            userController.deleteUser(id);
            userController.showListUser();
            showListUser();
        } else if (check.equalsIgnoreCase("N")){
            new ViewHome();
        }
        new ViewHome();
    }



    private boolean isValid(int id){
        int size = userController.showListUser().size();
        return id >= 0 && id<= size;
    }

}
