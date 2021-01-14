package blend.buddyapp.api.resources.profiles;
import blend.buddyapp.api.resources.users.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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



    @RequestMapping(value = "/profiles", consumes= {"multipart/form-data"}, produces = {"application/json"},  method = RequestMethod.PUT)
    public ResponseEntity<?> postImage(@RequestPart(value = "file", required = false) MultipartFile req,
                                       @RequestPart("first") String first,
                                       @RequestPart("last") String last,
                                       @RequestPart("bio") String bio,
                                       @RequestPart("username") String username){


        return service.setImage(username, req, first, last, bio);


    }


}
