package blend.buddyapp.api.resources.token.controller;
import blend.buddyapp.api.resources.token.models.LoginRequestBody;
import blend.buddyapp.api.resources.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("${openapi.swaggerBuddyProject.base-path:/api}")
public class TokenController implements TokenApi {

    @Autowired
    TokenService service;

    @Override
    public ResponseEntity<?> login(@Valid LoginRequestBody loginRequestBody)  {
        return service.login(loginRequestBody).toResponseEntity();
    }
}