package servlets;

import model.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/saveUser")
public class AdditionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        String name = req.getParameter("name");
        long age  = 0;
        try {
            age = Long.parseLong(req.getParameter("age"));
        } catch (NumberFormatException e) {
            age = 0;
        }
        User user = new User(name, age);
        if (age > 0) {
            try {
                userService.addUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/CRUD_war/");
    }
}