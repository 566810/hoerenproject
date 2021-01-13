package blend.buddyapp.api.resources.profiles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
/*
    @Query(nativeQuery = true, value = "Select * from dbo.student where student_number = :username")
    public Set<Student> findProfilesBy(@Param("userType") String username);
*/

    public List<Profile> findByUser_UserTypeEquals(String string);


}
