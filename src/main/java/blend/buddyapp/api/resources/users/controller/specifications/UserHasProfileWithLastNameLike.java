package blend.buddyapp.api.resources.users.controller.specifications;
import blend.buddyapp.api.resources.profiles.Profile;
import blend.buddyapp.api.resources.users.model.User;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;

public class UserHasProfileWithLastNameLike implements Specification<User> {

    private final String lastName;

    public UserHasProfileWithLastNameLike(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (lastName == null)  return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        Join<User, Profile>  lj = root.join("profile");
        return criteriaBuilder.like(lj.get("lastName"), "%" + lastName + "%");

    }
}
