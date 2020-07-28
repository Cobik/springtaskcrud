package web.dao;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void addUser(User user) throws SQLException;

    User getUserById(Long id) throws SQLException;

    User findByUsername(String userName) throws SQLException;

    boolean validateUser(User user) throws SQLException;

    boolean deleteUser(Long id) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    boolean validateUserByUsername(User user) throws SQLException;

    String getRoleByUsername(String username) throws SQLException;
}
