package blend.buddyapp.api.resources.token;

import blend.buddyapp.api.wrappers.Result;
import blend.buddyapp.api.resources.token.models.LoginRequestBody;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.ws.rs.NotAuthorizedException;


@Repository
public class TokenRepository {
    private final String buddyRealm;
    private final String serverUrl;
    private final String tokenRequestServiceClientId;
    private final String tokenRequestServiceClientSecret;

    @Autowired
    TokenRepository (Environment properties) {
        this.buddyRealm = properties.getProperty("keycloak.realm");
        this.serverUrl = properties.getProperty("keycloak.auth-server-url");
        this.tokenRequestServiceClientSecret = properties.getProperty("keycloak.credentials.secret");
        this.tokenRequestServiceClientId = properties.getProperty("keycloak.resource");
    }

    private KeycloakBuilder keyCloakBuilderWithUsernameAndPassword(String username, String password){
        return KeycloakBuilder.builder()
                .realm(buddyRealm)
                .serverUrl(serverUrl)
                .clientId(tokenRequestServiceClientId)
                .clientSecret(tokenRequestServiceClientSecret)
                .password(password)
                .username(username);
    }

    public Result<?> getKeyCloakToken (LoginRequestBody user) {
        try {
            AccessTokenResponse response = keyCloakBuilderWithUsernameAndPassword(user.getStudentNumber(), user.getPassword())
                    .grantType(OAuth2Constants.PASSWORD)
                    .build()
                    .tokenManager()
                    .getAccessToken();
            return new Result<>(response, HttpStatus.OK);
        }
        catch (Exception ex) {
            if (ex instanceof NotAuthorizedException)
                return new Result<>("wrong credentials", HttpStatus.UNAUTHORIZED);
            return new Result<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
