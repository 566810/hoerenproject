import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class mWebsocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        System.out.println("ff");

    }
}
