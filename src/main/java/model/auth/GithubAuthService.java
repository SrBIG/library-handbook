package model.auth;

import java.util.ResourceBundle;

public class GithubAuthService implements AuthService {
    private ResourceBundle authBundle = ResourceBundle.getBundle("auth");

    public String getAuthUri() {
        StringBuilder authUri = new StringBuilder();
        authUri.append(authBundle.getString("github.authUri"))
                .append("?").append("client_id=").append(authBundle.getString("github.clientId"))
                .append("&").append("scope=").append(authBundle.getString("github.scope"))
                .append("&").append("state=").append(authBundle.getString("github.state"))
                .append("&").append("allow_signup=false")
                .append("&").append("redirect_uri=").append(authBundle.getString("github.redirectURI"));
        return authUri.toString();
    }

    public String getTokenUri(String code) {
        StringBuilder tokenUri = new StringBuilder();
        tokenUri.append(authBundle.getString("github.accessUri"))
                .append("?").append("client_id=").append(authBundle.getString("github.clientId"))
                .append("&").append("client_secret=").append(authBundle.getString("github.clientSecret"))
                .append("&").append("redirect_uri=").append(authBundle.getString("github.redirectURI"))
                .append("&").append("code=").append(code);
        return tokenUri.toString();
    }

    public String getTakeUserInfoUri(int id, String accessToken) {
        StringBuilder infoUri = new StringBuilder();
        infoUri.append(authBundle.getString("github.takeUserInfoURI"))
                .append("?").append("access_token=").append(accessToken);
        return infoUri.toString();
    }
}
