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
    }

    @Override
    public List<Role> findAll() {
        return roleList;
    }

    @Override
    public Role findByName(RoleName roleName) {
        for (int i = 0; i < roleList.size(); i++) {
            if (roleName == roleList.get(i).getRoleName()) {
                return roleList.get(i);
            }
        }
        return null;
    }
}
