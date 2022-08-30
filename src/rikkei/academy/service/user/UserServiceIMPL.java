package rikkei.academy.service.user;

import rikkei.academy.config.Config;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;
import rikkei.academy.service.role.RoleServiceIMPL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceIMPL implements IUserService, Serializable {
    public static String PATH_USER = "D:\\_____Case study MD2______\\src\\rikkei\\academy\\database\\user.txt";
    public static String PATH_USER_SAVE = "D:\\_____Case study MD2______\\src\\rikkei\\academy\\database\\userSave.txt";
    public static Config<List<User>> userConfig = new Config<>();
    public static List<User> userList = userConfig.readFile(PATH_USER);
    static {
        if (userList == null) {
            userList = new ArrayList<>();
            Set<Role> roles = new HashSet<>();
            roles.add(new RoleServiceIMPL().findByName(RoleName.ADMIN));
            userList.add(
                    new User(
                            0, "ADMIN","admin", "admin@admin.com","admin", roles
                    )
            );
        }
    }


    @Override
    public List<User> findAll() {
        userConfig.writeFile(PATH_USER, userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        userConfig.writeFile(PATH_USER, userList);

    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                return userList.get(i);
            }
        }
        return null;
    }


    @Override
    public void deleteById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                userList.remove(i);
            }
        }

    }

    @Override
    public boolean existsByUserName(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userName.equals(userList.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String userName, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userName.equals(userList.get(i).getUsername()) && password.equals(userList.get(i).getPassword())){
                return true;
            }
        }return false;
    }

    @Override
    public User findByUserName(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userName.equals(userList.get(i).getUsername())) {
                return userList.get(i);
            }
        }
            return null;
        }

    @Override
    public User getCurrentUser() {
        User user = new Config<User>().readFile(PATH_USER_SAVE);
        if (user == null) {
            return null;
        }
        return findByUserName(user.getUsername());
    }

    @Override
    public void saveCurrentUser(User user) {
        new Config<User>().writeFile(PATH_USER_SAVE, user);
    }

    @Override
    public void changeRole(int id, Role role) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        findById(id).setRoles(roles);
        findAll();

    }

    public void changeStatus(int id){
        User user = findById(id);
        user.setStatus(!user.isStatus());
        findAll();
    }
}
