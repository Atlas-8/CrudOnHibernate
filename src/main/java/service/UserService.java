package service;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.List;

public class UserService {

    private static UserService userService;
    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    private static UserDAO getUserDAO() {
//        return new UserJdbcDAO(getMysqlConnection());
//    }

    private static UserDAO getUserDAO() {
        return new UserHibernateDAO(DBHelper.getSessionFactory().openSession());
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    public User getUserById(long id) throws Exception {
        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public User getUserByName(String name) throws ServletException {
        UserDAO dao = getUserDAO();
        try {
            return dao.getUserByName(name);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public List<User> getAllUsers() throws ServletException {
        UserDAO dao = getUserDAO();
        try {
            dao.createTable();
            return dao.getAllUsers();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void deleteUserById(long id) throws SQLException {
        UserDAO dao = getUserDAO();
        dao.deleteUserById(id);
    }

    public void addUser(User user) throws Exception {
        UserDAO dao = getUserDAO();
        dao.addUser(user);
    }

    public void cleanUp() throws Exception {
        UserDAO dao = getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void createTable() throws Exception{
        UserDAO dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").          //db type
                    append("localhost:").             //host name
                    append("3306/").                  //port
                    append("CRUD?").                  //db name
                    append("user=Atlas&").            //login
                    append("password=1987010688&").   //password
                    append("useSSL=false&").           //disable SSL
                    append("serverTimezone=UTC");

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
