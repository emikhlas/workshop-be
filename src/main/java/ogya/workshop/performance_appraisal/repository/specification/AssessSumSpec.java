package ogya.workshop.performance_appraisal.repository.specification;

import jakarta.persistence.criteria.Join;
import ogya.workshop.performance_appraisal.entity.AssessSum;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class AssessSumSpec {
    public static Specification<AssessSum> hasName(String name) {
        return (root, query, builder) -> {
            if (name == null) {
                return null;
            }
            Join<Object, Object> userJoin = root.join("user");
            return builder.like(
                    builder.lower(userJoin.get("fullName")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<AssessSum> hasYear(int year) {
        return (root, query, builder) -> {
            if (year == 0) {
                return null;
            }
            return builder.equal(root.get("year"), year);
        };
    }

    public static Specification<AssessSum> hasPosition(String position) {
        return (root, query, builder) -> {
            if (position == null) {
                return null;
            }
            Join<Object, Object> userJoin = root.join("user");
            return builder.like(
                    builder.lower(userJoin.get("position")),
                    "%" + position.toLowerCase() + "%"
            );
        };
    }

    public static Specification<AssessSum> hasEmail(String email) {
        return (root, query, builder) -> {
            if (email == null) {
                return null;
            }
            Join<Object, Object> userJoin = root.join("user");
            return builder.like(
                    builder.lower(userJoin.get("emailAddress")),
                    "%" + email.toLowerCase() + "%"
            );
        };
    }

    public static Specification<AssessSum> hasDivision(List<UUID> divisionIds) {
        return (root, query, builder) -> {
            if (divisionIds == null || divisionIds.isEmpty()) {
                return null;
            }
            Join<Object, Object> userJoin = root.join("user");
            Join<Object, Object> divisionJoin = userJoin.join("division");
            return divisionJoin.get("id").in(divisionIds);
        };
    }

}
