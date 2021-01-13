package blend.buddyapp.api.resources.message;

import blend.buddyapp.api.resources.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.james.mime4j.field.datetime.DateTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages" +
        "")
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String message;

    @Column(name = "senderId")
    private Long senderId;

    @Column(name ="receiverId")
    private Long receiverId;


    @ManyToOne
    @JsonIgnoreProperties("messages")
    User from;

    @ManyToOne
    @JsonIgnoreProperties("messages")
    User to;

    @Column(name= "date")
    private LocalDateTime date;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId(Long to) {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
