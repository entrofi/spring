

package net.entrofi.commons.util.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.Date;

public class SearchCriteria {

    public enum Operator {
        EQUALS((path, value1, builder) -> createEqualsPredicate(path, value1, builder)),
        GREATER_THAN((path, value1, builder) -> createGreaterThanPredicate(path, value1, builder)),
        LESS_THAN((path, value1, builder) -> createLessThanPredicate(path, value1, builder)),
        STARTS_WITH((path, value1, builder) -> createStartsWithPredicate(path, value1, builder)),
        ENDS_WITH((path, value1, builder) -> createEndsWithPredicate(path, value1, builder)),
        CONTAINS((path, value1, builder) -> createContainsPredicate(path, value1, builder)),
        NULL((path, value1, builder) -> createNullPredicate(path, builder));

        private PredicateBuilder predicateBuilder;

        Operator(PredicateBuilder predicateBuilder) {

            this.predicateBuilder = predicateBuilder;
        }

        public PredicateBuilder getPredicateBuilder() {
            return predicateBuilder;
        }
    }

    private final String name;
    private final Operator operator;
    private final Object value;
    private final boolean negated;

    public SearchCriteria(String name, Operator operator, Object value, boolean negated) {
        this.name = name;
        this.operator = operator;
        this.value = value;
        this.negated = negated;
    }

    public String getName() {
        return name;
    }

    public Operator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

    public boolean isNegated() {
        return negated;
    }



    private static Predicate createEqualsPredicate(Path path, Object value, CriteriaBuilder builder) {
        if (value.getClass().isArray()) {
            CriteriaBuilder.In in = builder.in(path);
            Arrays.stream((Object[]) value).forEach(in::value);
            return builder.or(in, builder.isNull(path));
        }
        return builder.equal(path, value);
    }

    private static Predicate createGreaterThanPredicate(Path path, Object value, CriteriaBuilder builder) {
        if (value.getClass().isAssignableFrom(Date.class)) {
            return builder.greaterThan((Path<Date>) path, (Date) value);
        } else {
            return builder.greaterThan(path, value.toString());
        }
    }

    private static Predicate createLessThanPredicate(Path path, Object value, CriteriaBuilder builder) {
        if (value.getClass().isAssignableFrom(Date.class)) {
            return builder.lessThan((Path<Date>) path, (Date) value);
        } else {
            return builder.lessThan(path, value.toString());
        }
    }

    private static Predicate createStartsWithPredicate(Path path, Object value, CriteriaBuilder builder) {
        return createLikePredicate(path, value, builder, true, false);
    }

    private static Predicate createEndsWithPredicate(Path path, Object value, CriteriaBuilder builder) {
        return createLikePredicate(path, value, builder, false, true);
    }

    private static Predicate createContainsPredicate(Path path, Object value, CriteriaBuilder builder) {
        return createLikePredicate(path, value, builder, true, true);
    }

    private static Predicate createLikePredicate(
            Path path, Object value, CriteriaBuilder builder, boolean startsWith, boolean endsWith) {
        final String wildcard = "%";

        return builder.like(path, (endsWith ? wildcard : "") + value + (startsWith ? wildcard : ""));
    }

    private static Predicate createNullPredicate(Path path, CriteriaBuilder builder) {
        return builder.isNull(path);
    }

}
