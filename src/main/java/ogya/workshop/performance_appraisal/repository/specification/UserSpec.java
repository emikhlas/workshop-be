package ogya.workshop.performance_appraisal.repository.specification;

import jakarta.persistence.criteria.Join;
import ogya.workshop.performance_appraisal.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class UserSpec {

    public static Specification<User> hasName(String name){
        return (root, query, builder) ->
                name == null ? null : builder.like(builder.lower(root.get("fullName")),
                        "%" + name.toLowerCase() + "%" );
    }

    public static Specification<User> hasPosition(String position){
        return (root, query, builder) ->
                position == null ? null : builder.like(builder.lower(root.get("position")),
                        "%" + position.toLowerCase() + "%" );
    }

    public static Specification<User> hasEmail(String email){
        return (root, query, builder) ->
                email == null ? null : builder.like(builder.lower(root.get("emailAddress")),
                        "%" + email.toLowerCase() + "%");
    }

    public static Specification<User> hasDivision(UUID divisionId){
        return (root, query, builder) -> {
            if(divisionId == null){
                return null;
            }

            Join<Object, Object> divisionJoin = root.join("division");
            return builder.equal(divisionJoin.get("id"), divisionId);
        };
    }

    
}
