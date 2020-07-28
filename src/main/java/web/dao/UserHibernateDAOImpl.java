package web.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import web.model.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserHibernateDAOImpl implements UserDao {

    private final EntityManagerFactory entityManagerFactory;


    public UserHibernateDAOImpl(EntityManagerFactory entityManagerFactory) {

        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public void addUser(User user) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction transaction = session.beginTransaction();
        try {

            session.save(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            session.close();
            transaction.rollback();
            e.printStackTrace();
        }

    }


    @Override
    public User getUserById(Long id) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            Query query = session.createQuery("from User where  id =:id");
            query.setString("id", String.valueOf(id));

            User user1 = (User) query.uniqueResult();
            session.close();
            return user1;

        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByUsername(String userName) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            Query query = session.createQuery("from User where username =:username");
            query.setString("username", userName);


            User user = (User) query.uniqueResult();
            session.close();
            return user;

        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validateUser(User user) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            Query query = session.createQuery("from User where username =:username and password =:password");
            query.setString("username", user.getUsername());
            query.setString("password", user.getPassword());

            User user1 = (User) query.uniqueResult();
            session.close();
            return user1 != null;

        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction transaction = session.beginTransaction();
        ;
        try {
            Query query = session.createQuery("delete User where id = :id");
            query.setString("id", String.valueOf(id));
            query.executeUpdate();
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            session.close();
            return false;
        }

    }

    @Override
    public boolean updateUser(User user) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction transaction = session.beginTransaction();
        ;
        try {

            session.update(user);
            transaction.commit();
            session.close();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            List<User> userList = session.createQuery("From User").list();
            session.close();
            return userList;
        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean validateUserByUsername(User user) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            Query query = session.createQuery("from User where username =:username");
            query.setString("username", user.getUsername());

            User user1 = (User) query.uniqueResult();
            session.close();
            return user1 != null;

        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }


    }

    @Override
    public String getRoleByUsername(String username) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            Query query = session.createQuery("select Role from User where username =:username");
            query.setString("username", username);
            String role = query.uniqueResult().toString();
            session.close();
            return role;
        } catch (RuntimeException e) {
            session.close();
            throw new RuntimeException(e);
        }
    }
}