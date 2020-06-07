package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {


    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public User getUserById(long id) {
        String hql = "FROM User WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        session.clear();
        return (User) query.uniqueResult();
    }

    @Override
    public void deleteUserById(long id) throws SQLException {
        String sql = "delete from users where id=" + id;
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        int deleted = query.executeUpdate();
        query.executeUpdate();
        session.getTransaction().commit();
        session.clear();
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        String hql = "FROM Car WHERE name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        session.clear();
        return (User) query.uniqueResult();
    }

    @Override
    public void addUser(User user) throws SQLException {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void dropTable() throws SQLException {

    }

}
