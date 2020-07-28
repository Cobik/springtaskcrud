package web.service.interf;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRole(String name);
    List<Role> getAllRoles();
}
