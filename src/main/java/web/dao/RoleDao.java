package web.dao;

import web.model.Role;

import java.util.List;


public interface RoleDao {
    Role getRoleByRoleName(String name);

    List<Role> getAllRoles();
}
