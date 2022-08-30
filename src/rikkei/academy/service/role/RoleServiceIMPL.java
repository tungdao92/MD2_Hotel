package rikkei.academy.service.role;

import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleSerVice {
    public static List<Role> roleList = new ArrayList<>();

    static {
        roleList.add(new Role(1, RoleName.ADMIN));
        roleList.add(new Role(2, RoleName.USER));
        roleList.add(new Role(3, RoleName.PM));
    }

    @Override
    public List<Role> findAll() {
        return roleList;
    }
    public void save(Role role) {
        roleList.add(role);
    }


    @Override
    public Role findByName(RoleName roleName) {
        for (Role role : roleList) {
            if (role.getRoleName() == roleName) {
                return role;
            }
        }
        return null;
    }
}
