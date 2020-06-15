package web.service;

import web.dao.UserDao;
import web.dao.UserHibernateDAO;
import web.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl {
    private static UserServiceImpl instance;

    private UserDao daoFactory = UserHibernateDAO.getInstance();

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }


    public List<User> getAllUsers() {
        try {
            return daoFactory.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user) throws SQLException {

        if (daoFactory.validateUser(user)) {
            return false;
        } else {
            daoFactory.addUser(user);
            return true;
        }
    }

    public String getRoleByUsername(String username) {
        try {
            return daoFactory.getRoleByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public boolean deleteUser(Long id) throws SQLException {
        return daoFactory.deleteUser(id);
    }

    public boolean signInUser(User user) throws SQLException {
        return daoFactory.validateUser(user);
    }

    public boolean validateUser(User user) throws SQLException {
        return daoFactory.validateUser(user) || daoFactory.validateUserByUsername(user);
    }

    public boolean updateUser(User user, Long id) throws SQLException {
        return daoFactory.updateUser(user, id);
    }

    public User getUserById(Long id) {
        try {
            return daoFactory.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
