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
    public List<User> showListUser() {
        return userService.findAll();
    }

    public ResponseMessenger register (SignUpDTO signUpDTO) {
        if (userService.existsByUserName(signUpDTO.getUsername())){
            return new ResponseMessenger("username_existed");
        }
        if (userService.existsByEmail(signUpDTO.getEmail())){
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
            }
        });
        User user = new User(signUpDTO.getId(), signUpDTO.getName(), signUpDTO.getUsername(), signUpDTO.getEmail(), signUpDTO.getPassword(), roles);
        userService.save(user);
        showListUser();
        return new ResponseMessenger("success");
    }


    public ResponseMessenger login (SignInDTO signUpDTO) {
        if(userService.checkLogin(signUpDTO.getUsername(), signUpDTO.getPassword())){
            User userLogin = userService.findByUserName(signUpDTO.getUsername());
            userService.saveCurrentUser(userLogin);
            return new ResponseMessenger("success");
        } else {
            return new ResponseMessenger("login_failed");
        }
    }

    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    public void logout(){
        userService.saveCurrentUser(null);
    }

}
