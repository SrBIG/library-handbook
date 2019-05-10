package web.filter;

import model.auth.AuthService;
import model.auth.VkAuthService;
import model.user.User;
import model.user.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (Objects.isNull(user) || !user.isAuthorized()) {
            response.sendRedirect(request.getContextPath() + "/authorization");
        } else if (user.getRole() != UserRole.ADMIN || user.getRole() != UserRole.OWNER) {
            response.sendRedirect(request.getContextPath() + "/authorization?needAdmin=true");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
