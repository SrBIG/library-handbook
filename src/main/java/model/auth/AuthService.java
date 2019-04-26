package model.auth;

public interface AuthService {
    String getAuthUri();

    String getTokenUri(String code);

    String getTakeUserInfoUri(int id, String accessToken);
}
