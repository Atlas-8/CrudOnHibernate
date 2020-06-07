package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers() throws SQLException;

    User getUserById(long id) throws SQLException;

    void deleteUserById(long id) throws SQLException;

    User getUserByName(String name) throws SQLException;

    void addUser(User user) throws SQLException;

    void createTable() throws SQLException;

    void dropTable() throws SQLException;

}
