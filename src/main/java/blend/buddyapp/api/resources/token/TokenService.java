package blend.buddyapp.api.resources.token;


import blend.buddyapp.api.wrappers.Result;
import blend.buddyapp.api.resources.token.models.LoginRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    TokenRepository repository;
    public Result<?> login(LoginRequestBody login) {
        return this.repository.getKeyCloakToken(login);
    }

}
