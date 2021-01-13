/*
package blend.buddyapp.api.resources.users.controller.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ObjectHasPropertyWithFirstNameLike<T> implements Specification {

    private String property;
    private String objectName;

    public ObjectHasPropertyWithFirstNameLike(String property) {
        this.property = property;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }
}
*/
