package blend.buddyapp.api.resources.match;

import blend.buddyapp.api.resources.match.model.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {

}