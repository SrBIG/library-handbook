package web.servlet.page;

import model.auth.AuthService;
import model.auth.VkAuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthPageServlet extends HttpServlet {
    private AuthService vkAuthService;

    @Override
    public void init() throws ServletException {
        vkAuthService = new VkAuthService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/authorization.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authUri = vkAuthService.getAuthUri();
        response.sendRedirect(authUri);
    }
}
