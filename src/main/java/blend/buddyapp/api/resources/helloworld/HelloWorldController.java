package blend.buddyapp.api.resources.helloworld;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@Validated
@RestController
@RequestMapping("${openapi.swaggerBuddyProject.base-path:/api}")
public class HelloWorldController {


    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    public ResponseEntity<String> helloWorld (Principal userPrincipal) {
        Principal x = userPrincipal;
        return new  ResponseEntity<String>("Server is up and running", HttpStatus.OK );
    }

    @RequestMapping(value = "/onlyBuddy", method = RequestMethod.POST)
    public ResponseEntity<String> testAuthBuddy (){
        return new  ResponseEntity<String>("under construction", HttpStatus.OK );
    }


    @RequestMapping(value = "/onlyPupil", method = RequestMethod.POST)
    public ResponseEntity<String> testAuthPupil (){
        return new  ResponseEntity<String>("under construction", HttpStatus.OK );
    }


    @RequestMapping(value = "/onlyAdmin", method = RequestMethod.POST)
    public ResponseEntity<String> testAuthAdmin (){
        return new  ResponseEntity<String>("under construction", HttpStatus.OK );
    }



}
