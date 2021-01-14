package blend.buddyapp.api.resources.helloworld;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  HelloWorldRepository extends CrudRepository<TesEntity, Long> {

}
