package blend.buddyapp.api.resources.profiles;
import blend.buddyapp.api.resources.users.model.UserType;
import javassist.bytecode.ByteArray;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("${openapi.swaggerBuddyProject.base-path:/api}")
public class ProfileController {

    @Autowired
    ProfileService service;
    @RequestMapping(value = "/profiles",  produces ={"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<?> getProfiles(Principal principal){
        if (!(principal instanceof KeycloakPrincipal))
            return new ResponseEntity<>(service.getAllProfiles(), HttpStatus.INTERNAL_SERVER_ERROR);

        AccessToken token = ((KeycloakPrincipal<?>) principal).getKeycloakSecurityContext().getToken();
        List<String> roles = token.getRealmAccess()
                .getRoles()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        if (roles.contains("admin"))
            return new ResponseEntity<>(service.getAllProfiles(), HttpStatus.OK);
        else if (roles.contains("buddy"))
            return new ResponseEntity<>(service.getProfilesWhereUsersStudentTypeEquals(UserType.PUPIL), HttpStatus.OK);
        else if (roles.contains("pupil"))
            return new ResponseEntity<>(service.getProfilesWhereUsersStudentTypeEquals(UserType.BUDDY), HttpStatus.OK);
        else
            return new ResponseEntity<>("role not found", HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/profiles", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<?> createProfile(@Valid @RequestBody Profile profile, Principal principal){

        AccessToken token = ((KeycloakPrincipal<?>) principal).getKeycloakSecurityContext().getToken();
        List<String> roles = token.getRealmAccess()
                .getRoles()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        String username = token.getPreferredUsername();
        service.addProfile(profile, username);

        return new ResponseEntity(profile, HttpStatus.CREATED);
    }



    @RequestMapping(value = "/profiles", consumes= {"multipart/form-data"}, produces = {"application/json"},  method = RequestMethod.PUT)
    public ResponseEntity<?> postImage(@RequestPart(value = "file", required = false) MultipartFile req,
                                       @RequestPart("first") String first,
                                       @RequestPart("last") String last,
                                       @RequestPart("bio") String bio,

                                       Principal principal){

        AccessToken token = ((KeycloakPrincipal<?>) principal).getKeycloakSecurityContext().getToken();
        String username = token.getPreferredUsername();

        return service.setImage(username, req, first, last, bio);




        //return new ResponseEntity(HttpStatus.OK);
    }


}
