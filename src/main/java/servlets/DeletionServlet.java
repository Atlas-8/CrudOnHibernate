package servlets;

import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/deleteUser")
public class DeletionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        UserService userService = UserService.getInstance();
        try {
            userService.deleteUserById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("/CRUD_war/");
    }
}