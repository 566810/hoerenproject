package blend.buddyapp.api.resources.users.controller.specifications;
import blend.buddyapp.api.resources.profiles.Profile;
import blend.buddyapp.api.resources.users.model.User;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;

public class UserHasProfileWithFirstNameLike implements Specification<User> {

    private final String firstName;

    public UserHasProfileWithFirstNameLike(String firstName) {
        this.firstName= firstName;
    }
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (firstName == null)  return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        Join<User, Profile>  lj = root.join("profile");
        return criteriaBuilder.like(lj.get("firstName"), "%" + firstName + "%");
    }

}
