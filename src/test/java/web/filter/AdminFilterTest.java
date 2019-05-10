package web.filter;

import model.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminFilterTest {
    @Mock
    private ServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private FilterConfig config;
    @Mock
    private UserService userService;

    @InjectMocks
    private AdminFilter filter;

    @Before
    public void setup() {
        when(userService.checkOwner()).thenReturn(true);
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
