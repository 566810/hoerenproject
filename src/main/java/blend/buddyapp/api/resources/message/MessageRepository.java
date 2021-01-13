package blend.buddyapp.api.resources.message;

import blend.buddyapp.api.resources.match.model.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends CrudRepository<Message, Long> {


    @Query(nativeQuery = true, value = "Select * From messages where from_id IN (:from,:to) and to_id IN(:from,:to)")
    public Set<Message> findProfilesBy(@Param("from") Long from, @Param("to") Long to);

}
