package blend.buddyapp.api.resources.users;
import blend.buddyapp.api.helpers.HelperMethods;
import blend.buddyapp.api.resources.match.MatchRepository;
import blend.buddyapp.api.resources.match.model.Match;
import blend.buddyapp.api.resources.profiles.Profile;
import blend.buddyapp.api.resources.profiles.ProfileRepository;
import blend.buddyapp.api.resources.users.model.User;
import blend.buddyapp.api.resources.users.repository.KeyCloakUserRepository;
import blend.buddyapp.api.resources.users.repository.UserRepository;
import blend.buddyapp.api.wrappers.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Stream;

@org.springframework.stereotype.Service
public class UserService {
    @Autowired
    KeyCloakUserRepository keyCloakUserRepository;
    @Autowired
    UserRepository repository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    MatchRepository matchRepository;

    public Result<?> getUsers(Specification spec) {
        return new Result<>(repository.findAll(spec), HttpStatus.OK);
    }

    public Result<?> getUserById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (!optionalUser.isPresent()) return new Result<>("not found", HttpStatus.NOT_FOUND);
        return new Result<>(optionalUser, HttpStatus.OK);
    }

    public Result<?> getUserBName(String username) {
        return new Result<>(repository.findStudentByUsername(username), HttpStatus.OK);
    }

    public Result<?> addUser(User user) throws Exception {
        String username = user.getStudentNumber().toString();
        HttpStatus status = keyCloakUserRepository.addUser(user);
        if (status.isError()) return new Result<>("Could not add user", status);
        status = keyCloakUserRepository.subscribeUserToRole(user.getUserType(), user.getStudentNumber().toString());
        if (status.isError() && !keyCloakUserRepository.deleteUser(username).isError())
            return new Result<>("Couldn't subscribe user to Role, account deleted", status);
        else if (status.isError())
            return new Result<>("Couldn't subscribe user to Role, Could also not delete this account.. Your tokens will be worthless, contact your admin", status);
        repository.save(user);
        return new Result<>("account added", HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteUserById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (!optionalUser.isPresent())
            return new ResponseEntity("something went wrong", HttpStatus.BAD_REQUEST);
        User user = optionalUser.get();
        if (keyCloakUserRepository.deleteUser(user.getStudentNumber().toString()).isError())
            return new ResponseEntity("something went wrong", HttpStatus.BAD_REQUEST);
        repository.delete(user);
        return new ResponseEntity("nice!", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteProfileByUserId(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (!optionalUser.isPresent())  return new ResponseEntity("ffs", HttpStatus.NOT_FOUND);
        User user = optionalUser.get();
        Profile p = user.getProfile();
        if (p == null)  return new ResponseEntity("", HttpStatus.NOT_FOUND);
        user.setProfile(null);
        profileRepository.delete(p);
        return new ResponseEntity("ok", HttpStatus.OK);
    }

    public void changeProfileByUserId(Long id, Map<String, Object> updates) {
        Optional<User> u = repository.findById(id);
        if (! u.isPresent()) return;
        Profile profile =  u.get().getProfile();
        HelperMethods.patch(profile, updates); //is a void method that uses reflection in order to change the profile
        profileRepository.save(profile);
    }


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public ResponseEntity addMatchFromUser(Long Userid, Long Userid2) {
        Optional<User> OptionalUser =  repository.findById(Userid);
        Optional<User> OptionalUser2 =  repository.findById(Userid2);

        if (!Stream.of(OptionalUser, OptionalUser2).allMatch(Optional::isPresent)

        )
            return new ResponseEntity("ok", HttpStatus.NOT_FOUND);

        User user = OptionalUser.get();
        User user2 = OptionalUser2.get();
        Match match = new Match();
        match.setUser1(user);
        match.setUser2(user2);
        matchRepository.save(match);
        System.out.print(match.id);
        List<Match> x = user.getMatches();
        x.add(match);
        List<Match> z = user2.getMatches();
        z.add(match);
        user.setMatches(x);
        user2.setMatches(z);
        repository.saveAll(Arrays.asList(user, user2));

        return new ResponseEntity(match, HttpStatus.OK);

        //repository.getMatch()
    }

    public void getMatches(Long id){

    }
}
