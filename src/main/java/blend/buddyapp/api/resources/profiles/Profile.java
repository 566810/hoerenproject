package blend.buddyapp.api.resources.profiles;
import blend.buddyapp.api.resources.users.model.User;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name ="firstName")
    private String firstName;

    @NotNull
    @Column(name = "lastName")
    private String lastName;

    @NotNull
    @Column(name = "bio")
    private String biography;

    @Lob
    @Column(name="profilePicture", length = 100000)
    private byte[] profilePicture;




    @JsonIgnoreProperties("profile")
    @OneToOne
    private User user;

    Profile(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @JsonIgnoreProperties("profile")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] image) {
        this.profilePicture = image;
    }
}
