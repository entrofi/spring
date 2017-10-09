

package net.entrofi.commons.util.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public interface PredicateBuilder {
    Predicate build(Path path, Object value, CriteriaBuilder builder);
}
