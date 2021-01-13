package blend.buddyapp.api.resources.users.repository;

import blend.buddyapp.api.resources.users.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(nativeQuery = true, value = "Select TOP 1 * from dbo.users where student_number = :username")
    public User findStudentByUsername(@Param("username") String username);





}
