package servlets;

import model.User;
import service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        List<User> users = userService.getAllUsers();
        resp.setContentType("text/html");
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/homePage.jsp");
        dispatcher.forward(req, resp);
    }
}