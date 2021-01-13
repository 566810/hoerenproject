package blend.buddyapp.api.resources.match.model;

import blend.buddyapp.api.resources.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "matches")
public class Match {


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }





    //user 1
    @ManyToOne
    @JsonIgnoreProperties("matches")
    private User user1;

    //user 2
    @ManyToOne
    @JsonIgnoreProperties("matches")
    private User user2;



    @Column
    private String additionalInfo;


}
