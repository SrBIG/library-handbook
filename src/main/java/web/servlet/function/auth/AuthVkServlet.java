package web.servlet.function.auth;

import model.auth.AuthService;
import model.auth.VkAuthService;
import model.user.User;
import model.user.UserService;
import org.apache.http.client.methods.HttpGet;
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

public class AuthVkServlet extends HttpServlet {
    private AuthService vkAuthService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        vkAuthService = new VkAuthService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String tokenUri = vkAuthService.getTokenUri(code);

        JSONObject json = getAccessTokenJson(tokenUri);

        if (!json.has("access_token")) {
            response.sendRedirect(request.getContextPath() + "/books");
            return;
        }

        String accessToken = json.getString("access_token");
        int id = json.getInt("user_id");

        User user = takeUserInfo(accessToken, id);

        userService.checkUser(user);

        request.getSession().setAttribute("user", user);

        response.sendRedirect(request.getContextPath() + "/authorization");
    }

    private JSONObject getAccessTokenJson(String tokenUri) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault(); // not closeable
        HttpGet get = new HttpGet(tokenUri);
        InputStream responseStream = httpClient.execute(get).getEntity().getContent();

        return new JSONObject(new JSONTokener(new InputStreamReader(responseStream)));
    }

    private User takeUserInfo(String accessToken, int id) throws IOException {
        String uri = vkAuthService.getTakeUserInfoUri(id, accessToken);

        CloseableHttpClient httpClient = HttpClients.createDefault(); // not closeable
        HttpGet get = new HttpGet(uri);

        InputStream responseStream = httpClient.execute(get).getEntity().getContent();
        JSONObject json = new JSONObject(new JSONTokener(new InputStreamReader(responseStream)));
        if (json.has("error")) {
            System.out.println(json.toString());
            return null;
        }

        User user = createUser(json);
        user.setId(id);

        return user;
    }

    private User createUser(JSONObject json) {
        User user = new User();

        JSONArray response = json.getJSONArray("response");
        for (int entry = 0; entry < response.length(); entry++) {
            JSONObject object = response.getJSONObject(entry);

            if (object.has("first_name")) {
                String firstName = decode(object.getString("first_name"));
                user.setFirstName(firstName);
            }
            if (object.has("last_name")) {
                String lastName = decode(object.getString("last_name"));
                user.setLastName(lastName);
            }
        }
        user.setAuthorized(true);
        return user;
    }

    private String decode(String encoded) {
        return new String(encoded.getBytes(), StandardCharsets.UTF_8);
    }
}
