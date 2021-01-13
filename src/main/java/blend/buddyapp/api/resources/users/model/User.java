package blend.buddyapp.api.resources.users.model;

import blend.buddyapp.api.resources.match.model.Match;
import blend.buddyapp.api.resources.message.Message;
import blend.buddyapp.api.resources.profiles.Profile;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class , property = "@Id")




@Entity
@Table(name="users")
@JsonPropertyOrder({"id", "student_number", "student_type", "profile"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userid;



    @NotNull
    @JsonProperty("student_number")
    @Column(name = "studentNumber")
    private String studentNumber;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @NotNull
    @JsonProperty("student_type")
    @Column(name = "studentType")
    @Pattern(regexp = "^buddy$|^admin$|^pupil$", flags =  Pattern.Flag.CASE_INSENSITIVE, message = "you can only use buddy, admin or pupil as your student_type")
    private String userType;



    @OneToOne( cascade = CascadeType.ALL, mappedBy = "user")
    private Profile profile;


    //list of matches
    @ManyToMany()
    //@JoinColumn(name = "match_id", referencedColumnName = "id")
    private List<Match> matches;



    public User(@NotNull String studentNumber, @NotNull String password, @NotNull String studentType) {
        this.studentNumber = studentNumber;
        this.password = password;
        this.userType = studentType;
    }
    public User(){}

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatches(List<Match> matches){
        this.matches.addAll(matches);
    }



    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }




    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    @JsonIgnoreProperties("user")
    public Profile getProfile() {
        return profile;
    }

    @OneToMany()
    private List<Message> messages;


}


