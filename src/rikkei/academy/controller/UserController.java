package rikkei.academy.controller;

import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;
import rikkei.academy.service.role.IRoleSerVice;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    IUserService userService = new UserServiceIMPL();
    IRoleSerVice roleSerVice = new RoleServiceIMPL();
    User currentUser = userService.getCurrentUser();

    public List<User> showListUser() {
        return userService.findAll();
    }

    public ResponseMessenger register(SignUpDTO signUpDTO) {
        if (userService.existsByUserName(signUpDTO.getUsername())) {
            return new ResponseMessenger("username_existed");
        }
        if (userService.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseMessenger("email_existed");
        }

        Set<String> strRoles = signUpDTO.getStrRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleSerVice.findByName(RoleName.ADMIN);
                    roles.add(adminRole);
                    break;
                case "user":
                    Role userRole = roleSerVice.findByName(RoleName.USER);
                    roles.add(userRole);
                    break;
                case "pm":
                    Role pmRole = roleSerVice.findByName(RoleName.PM);
                    roles.add(pmRole);
                    break;
            }
        });
        User user = new User(signUpDTO.getId(), signUpDTO.getName(), signUpDTO.getUsername(), signUpDTO.getEmail(), signUpDTO.getPassword(), roles);
        userService.save(user);
        showListUser();
        return new ResponseMessenger("success");
    }


    public ResponseMessenger login(SignInDTO signInDTO) {
        if (!userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())) {
            return new ResponseMessenger("login failed");
        } if(userService.findByUserName(signInDTO.getUsername()).isStatus()){
            return new ResponseMessenger("blocked");
        }

        User userLogin = userService.findByUserName(signInDTO.getUsername());
        userService.saveCurrentUser(userLogin);
        return new ResponseMessenger("login_successful");
    }

    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    public void logout() {
        userService.saveCurrentUser(null);
    }

    public void deleteUser(int id) {
        userService.deleteById(id);
    }

    public ResponseMessenger changePassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(currentUser.getPassword())) {
            return new ResponseMessenger("not_match");
        }
        currentUser.setPassword(newPassword);
        userService.findAll();
        return new ResponseMessenger("success");
    }

    public ResponseMessenger changeRole(int id, String roleName) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        if (!roleName.equals("user") && !roleName.equals("pm")) {
            return new ResponseMessenger("invalid_role");
        }
        Role role = roleName.equals("user") ? roleSerVice.findByName(RoleName.USER) : roleSerVice.findByName(RoleName.PM);
        userService.changeRole(id, role);
        showListUser();
        return new ResponseMessenger("success");

    }

    public ResponseMessenger blockUser(int id){
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_fond");
        }
        userService.changeStatus(id);
        boolean check = userService.findById(id).isStatus();
        if(check){
            return new ResponseMessenger("blocked");
        } else {
            return new ResponseMessenger("unblocked");
        }

    }
    public void changeProfile(User user){
        userService.changeProfile(user);
    }

    public boolean exitsByEmail(String email){
        return userService.existsByEmail(email);
    }

}
