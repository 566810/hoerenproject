package blend.buddyapp.api.resources.users.controller.specifications;

import blend.buddyapp.api.resources.users.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserWithStudentTypeEqualTo implements Specification<User> {

    String usertype;

    public UserWithStudentTypeEqualTo(String userType){
        this.usertype = userType;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (usertype == null) return  criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        return criteriaBuilder.equal(root.get("userType"), this.usertype);
    }
}
