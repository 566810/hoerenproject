package chattest;

import blend.buddyapp.api.resources.message.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class TestController {

    @MessageMapping("/chat")
    @SendTo("/test/messages")
    public Message send(Message message) throws Exception {
        return message;
    }

}
