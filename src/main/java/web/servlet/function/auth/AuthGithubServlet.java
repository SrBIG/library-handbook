package web.servlet.function.auth;

import model.auth.AuthService;
import model.auth.GithubAuthService;
import model.auth.VkAuthService;
import model.user.User;
import model.user.UserService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AuthGithubServlet extends HttpServlet {
    private AuthService githubAuthService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        githubAuthService = new GithubAuthService();
        userService = new UserService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String tokenUri = githubAuthService.getTokenUri(code);

        JSONObject json = getAccessTokenJson(tokenUri);

        if (!json.has("access_token")) {
            response.sendRedirect(request.getContextPath() + "/books");
            return;
        }

        String accessToken = json.getString("access_token");

        User user = takeUserInfo(accessToken);

        userService.checkUser(user);

        request.getSession().setAttribute("user", user);

        response.sendRedirect(request.getContextPath() + "/authorization");
    }

    private JSONObject getAccessTokenJson(String tokenUri) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault(); // not closeable

        HttpPost post = new HttpPost(tokenUri);
        post.addHeader("Accept", "application/json");

        InputStream responseStream = httpClient.execute(post).getEntity().getContent();

        return new JSONObject(new JSONTokener(new InputStreamReader(responseStream)));
    }

    private User takeUserInfo(String accessToken) throws IOException {
        String uri = githubAuthService.getTakeUserInfoUri(0, accessToken);

        CloseableHttpClient httpClient = HttpClients.createDefault(); // not closeable

        HttpGet get = new HttpGet(uri);

        InputStream responseStream = httpClient.execute(get).getEntity().getContent();
        JSONObject json = new JSONObject(new JSONTokener(new InputStreamReader(responseStream)));
        if (json.has("error")) {
            System.out.println(json.toString());
            return null;
        }

        String login = json.getString("login");
        Integer id = json.getInt("id");

        User user = createUser(id, login);

        return user;
    }

    private User createUser(Integer id, String login) {
        User user = new User();

        user.setLogin(login);
        user.setId(id);

        user.setAuthorized(true);
        return user;
    }

    private String decode(String encoded) {
        return new String(encoded.getBytes(), StandardCharsets.UTF_8);
    }
}
