package web.servlet.page;

import model.user.User;
import model.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/moderation")
public class ModerationPageServlet extends HttpServlet {
    private static final String USERS = "users";

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<User> users = userService.getAllUsers();
        int allUsers = users.size();
        int firstOnPage = (page - 1) * recordsPerPage;
        int lastOnPage = recordsPerPage * page;
        if (lastOnPage > allUsers) {
            lastOnPage = allUsers;
        }
        List<User> usersOnPage = users.subList(firstOnPage, lastOnPage);
        int numPages = (int) Math.ceil(allUsers * 1.0 / recordsPerPage);
        request.setAttribute(USERS, usersOnPage);
        request.setAttribute("numPages", numPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/moderation.jsp");
        requestDispatcher.forward(request, response);
    }
}