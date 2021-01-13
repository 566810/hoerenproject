package blend.buddyapp.api.resources.message;


import blend.buddyapp.api.resources.users.UserService;
import org.jboss.resteasy.annotations.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.time.LocalDateTime;


@Validated
@RestController
@RequestMapping("${openapi.swaggerBuddyProject.base-path:/api}")
public class MessageController {
    @Autowired
    MessageService service;

    @RequestMapping(value = "/messages", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<?> postMessage(@RequestBody @Valid MessageRequestBody requestBody){
        service.createMessage(requestBody.message, requestBody.from, requestBody.to);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/messages/{from}/{to}", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<?> getMessage(@PathVariable("from") Long from, @PathVariable("to") Long to){
        return service.getMessages(from, to);
        //return new ResponseEntity(HttpStatus.OK);
    }





}
