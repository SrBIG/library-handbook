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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CharSetFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private FilterConfig config;

    @InjectMocks
    private CharsetFilter filter;

    @Before
    public void setup() {
        HttpSession session = mock(HttpSession.class);
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
