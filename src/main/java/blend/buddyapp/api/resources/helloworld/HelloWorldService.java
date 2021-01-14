package blend.buddyapp.api.resources.helloworld;


import blend.buddyapp.api.resources.message.MessageRequestBody;
import blend.buddyapp.api.resources.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class HelloWorldService {
    @Autowired
    private HelloWorldRepository hwr;

    public void addTest(TesEntity tes) {
        hwr.save(tes);


    }
}
