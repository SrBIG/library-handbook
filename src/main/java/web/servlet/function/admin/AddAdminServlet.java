package web.servlet.function.admin;

import model.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/moderation/addAdmin/*")
public class AddAdminServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getPathInfo().substring(1);
        long id = Long.parseLong(idStr);
        userService.addAdmin(id);
        response.sendRedirect(request.getContextPath() + "/moderation");
    }
}
