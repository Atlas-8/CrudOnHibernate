package servlets;

import model.User;
import service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/updateUser")
public class UpdationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        UserService userService = UserService.getInstance();
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        long age = Long.parseLong(req.getParameter("age"));
        User user = new User(id, name, age);
        try {
            userService.deleteUserById(id);
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/CRUD_war/");
    }
}