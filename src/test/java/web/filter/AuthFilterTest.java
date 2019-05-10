package web.filter;

import model.user.User;
import model.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private FilterConfig config;
    @Mock
    private UserService userService;
    @Mock
    private User user;

    @InjectMocks
    private AuthFilter filter;

    @Before
    public void setup() {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
    }

    @Test
    public void testDoFilter() throws IOException, ServletException {
        filter.doFilter(request, response, chain);
    }

    @Test
    public void testInit() throws IOException, ServletException {
        filter.init(config);
    }

    @Test
    public void testDestroy() throws IOException, ServletException {
        filter.destroy();
    }

}
