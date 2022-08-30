package rikkei.academy.service.role;

import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;

import java.util.List;

public interface IRoleSerVice {
    List<Role> findAll();
    void save(Role role);
    Role findByName(RoleName roleName);
}
