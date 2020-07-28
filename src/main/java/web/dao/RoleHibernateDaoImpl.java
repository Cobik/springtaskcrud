package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManagerFactory;

import java.util.List;

@Repository
public class RoleHibernateDaoImpl implements RoleDao {


    private final EntityManagerFactory entityManagerFactory;


    public RoleHibernateDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public Role getRoleByRoleName(String name) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            Query query = session.createQuery("FROM Role  where roleName =: name");
            query.setString("name", name);

            Role role = (Role) query.uniqueResult();
            session.close();
            return role;
        } catch (RuntimeException e) {
            session.close();

            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> getAllRoles() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            List<Role> roles = session.createQuery("FROM Role ").list();
            session.close();
            return roles;
        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }
    }


}
