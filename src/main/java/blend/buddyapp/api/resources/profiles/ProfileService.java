package blend.buddyapp.api.resources.profiles;

import blend.buddyapp.api.resources.users.model.User;
import blend.buddyapp.api.resources.users.model.UserType;
import blend.buddyapp.api.resources.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;
    @Autowired
    private UserRepository userRepository;

    public void addProfile(Profile profile, String username){
        User student = userRepository.findStudentByUsername(username);
        student.setProfile(profile);
        profile.setUser(student);
        repository.save(profile);
        //repository.
    }

    public List<Profile> getProfilesWhereUsersStudentTypeEquals(UserType studentType){
        return repository.findByUser_UserTypeEquals(studentType.toString().toLowerCase());
    }

    public List<Profile> getAllProfiles(){
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }


    public ResponseEntity setImage(String username, MultipartFile req, String first, String last, String bio) {


        User user = userRepository.findStudentByUsername(username);




        Profile profile = null;

        if (user.getProfile() != null){
            profile = user.getProfile();
        }  else {
            profile = new Profile();
           profile.setUser(user);
        }



        profile.setFirstName(first);
        profile.setLastName(last);
        profile.setBiography(bio);

        if (req!= null){
            try{
                byte[] byteArray = req.getBytes();
                profile.setProfilePicture(byteArray);

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        user.setProfile(profile);
        repository.save(profile);
        userRepository.save(user);

        return new ResponseEntity(profile, HttpStatus.OK );

    }
}
