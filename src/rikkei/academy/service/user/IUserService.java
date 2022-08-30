package rikkei.academy.service.user;

import rikkei.academy.model.Role;
import rikkei.academy.model.User;
import rikkei.academy.service.IGenericService;

public interface IUserService extends IGenericService <User> {
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean checkLogin(String userName, String password);
    User findByUserName(String userName);
    User getCurrentUser();
    void saveCurrentUser(User user);
    void changeRole(int id, Role role);
    void changeStatus(int id);

}
