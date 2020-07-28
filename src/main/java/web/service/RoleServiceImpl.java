package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;
import web.service.interf.RoleService;

import java.util.List;
import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Role getRole(String name) {
        return roleDao.getRoleByRoleName(name);
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }


}
