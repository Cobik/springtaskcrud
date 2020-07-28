package web.service.interf;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    boolean addUser(User user) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    boolean updateUser(User user) throws SQLException;
    void deleteUser(long id) throws SQLException;
    User getUsersById(long id) throws SQLException;


    User findByUsername(String username);
}
