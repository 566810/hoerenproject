package blend.buddyapp.api.resources.helloworld;


import javax.persistence.*;

@Entity
@Table(name="test")
public class TesEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Column(name = "test")
    private String test;

}
