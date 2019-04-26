package web.servlet.function.auth;

import model.auth.AuthService;
import model.auth.GithubAuthService;
import model.auth.VkAuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInServlet extends HttpServlet {
    private AuthService authService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String target = request.getParameter("target");
        String authUri;
        switch (target) {
            case "vk":
                authService = new VkAuthService();
                authUri = authService.getAuthUri();
                break;
            case "github":
                authService = new GithubAuthService();
                authUri = authService.getAuthUri();
                break;
            default:
                authUri = request.getContextPath();
        }
        response.sendRedirect(authUri);
    }
}
