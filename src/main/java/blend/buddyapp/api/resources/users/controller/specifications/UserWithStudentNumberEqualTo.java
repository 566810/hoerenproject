package blend.buddyapp.api.resources.users.controller.specifications;

import blend.buddyapp.api.resources.users.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserWithStudentNumberEqualTo implements Specification<User> {
    private final String studentNumber;

    public UserWithStudentNumberEqualTo(String studentNumber){
        this.studentNumber = studentNumber;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (studentNumber == null)  return criteriaBuilder.isTrue(criteriaBuilder.literal(true));//NO FILTERING!
        return criteriaBuilder.equal(root.get("studentNumber"), this.studentNumber); //FILTER ON STUDENT NUMBER
    }
}
