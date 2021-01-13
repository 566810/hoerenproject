package blend.buddyapp.api.resources.users.controller;
import blend.buddyapp.api.helpers.HelperMethods;
import blend.buddyapp.api.resources.users.UserService;
import blend.buddyapp.api.resources.users.controller.specifications.UserHasProfileWithFirstNameLike;
import blend.buddyapp.api.resources.users.controller.specifications.UserHasProfileWithLastNameLike;
import blend.buddyapp.api.resources.users.controller.specifications.UserWithStudentTypeEqualTo;
import blend.buddyapp.api.resources.users.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-14T03:02:35.640+02:00[Europe/Prague]")
@Validated
@RestController
@RequestMapping("${openapi.swaggerBuddyProject.base-path:/api}")
public class UserController implements UserApi {
    @Autowired
    UserService service;
    //GET ALL USERS
    @RequestMapping(value = "/users", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(
            @RequestParam(required = false, value = "studentNumber") String studentNumber,
            @RequestParam(value = "studentType", required = false) String studentType,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "firstName", required = false) String firstName) throws JsonProcessingException {

        if (studentNumber != null)
            return service.getUserBName(studentNumber).toResponseEntity();

        Specification spec = Specification
                .where(new UserWithStudentTypeEqualTo(studentType))
                .and(new UserHasProfileWithLastNameLike(lastName))
                .and(new UserHasProfileWithFirstNameLike(firstName));
        List<User> userlist = (List<User>) service.getUsers(spec).getObject();
        HelperMethods.JsonStringIgnoreProperties(Arrays.asList("profile"),userlist);
        return service.getUsers(spec).toResponseEntity();
    }
    //GET USER BY ID
    @RequestMapping(value = "/users/{id}", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        return service.getUserById(id).toResponseEntity();
    }
    //POST USER
    @Override
    public ResponseEntity<?> postUser(@Valid @RequestBody User student) throws Exception {
        return service.addUser(student).toResponseEntity();
    }
    //DELETE USER
    @RequestMapping(value = "/users/{id}", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteUserById(@PathVariable("id") Long id){
        return service.deleteUserById(id);
    }
    //CREATE A PROFILE FOR A USER (NOT DOING THIS)



    //GET A PROFILE FROM A USER (NOT DOING THIS)

    //DELETE A PROFILE FROM A SPECIFIC USER
    @RequestMapping(value = "/users/{id}/profile", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteProfileByUserId(@PathVariable("id") Long id){
        return service.deleteProfileByUserId(id);
    }
    //PATCH PROFILE FROM USER ID ||
    @RequestMapping(value = "/users/{id}/profile", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.PATCH)
    public ResponseEntity<?> PatchProfile(@RequestBody Map<String, Object> updates, @PathVariable("id") Long id  ){
        service.changeProfileByUserId(id, updates);
        return new ResponseEntity("" , HttpStatus.OK);
    }


    @RequestMapping(value = "/users/{id}/matches/{id2}", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<?> matchUser(@PathVariable("id") Long id, @PathVariable("id2") Long id2   ){
        return service.addMatchFromUser(id, id2);

        //return new ResponseEntity("" , HttpStatus.OK);
    }


}
