package model.auth;

import java.util.ResourceBundle;

public class VkAuthService implements AuthService {
    private ResourceBundle authBundle = ResourceBundle.getBundle("auth");

    public String getAuthUri() {
        StringBuilder authUri = new StringBuilder();
        authUri.append(authBundle.getString("vk.authUri"))
                .append("?").append("client_id=").append(authBundle.getString("vk.clientId"))
                .append("&").append("display=page")
                .append("&").append("response_type=code")
                .append("&").append("v=").append(authBundle.getString("vk.version"))
                .append("&").append("redirect_uri=").append(authBundle.getString("vk.redirectURI"));
        return authUri.toString();
    }

    public String getTokenUri(String code) {
        StringBuilder tokenUri = new StringBuilder();
        tokenUri.append(authBundle.getString("vk.accessUri"))
                .append("?").append("client_id=").append(authBundle.getString("vk.clientId"))
                .append("&").append("client_secret=").append(authBundle.getString("vk.clientSecret"))
                .append("&").append("redirect_uri=").append(authBundle.getString("vk.redirectURI"))
                .append("&").append("code=").append(code)
                .append("&").append("v=").append(authBundle.getString("vk.version"));
        return tokenUri.toString();
    }

    public String getTakeUserInfoUri(int id, String accessToken) {
        StringBuilder infoUri = new StringBuilder();
        infoUri.append(authBundle.getString("vk.takeUserInfoURI"))
                .append("?").append("user_ids=").append(id)
                .append("&").append("access_token=").append(accessToken)
                .append("&").append("v=").append(authBundle.getString("vk.version"));
        return infoUri.toString();
    }
}
