

package net.entrofi.commons.util;


import net.entrofi.commons.util.model.SearchCriteria;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static net.entrofi.commons.util.model.SearchCriteria.Operator.*;

public final class SearchCriteriaHelper {

    private static final Pattern FILTER_PATTERN =
            Pattern.compile("^([a-zA-Z0-9_.]+)(!?)([:<>])(\\*?)([a-zA-Z0-9_,]+)(\\*?)$");

    // filter regex groups
    private static final int NAME = 1;
    private static final int NEGATION = 2;
    private static final int OPERATOR = 3;
    private static final int STAR_PREFIX = 4;
    private static final int VALUE = 5;
    private static final int STAR_SUFFIX = 6;

    private SearchCriteriaHelper() {
    }

    public static List<SearchCriteria> parseFilters(String filters) {
        List<SearchCriteria> result = new ArrayList<>();
        for (String part : StringUtils.split(filters, ";")) {
            result.add(parseFilter(part));
        }
        return result;
    }

    private static SearchCriteria parseFilter(String s) {
        Matcher matcher = FILTER_PATTERN.matcher(s);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Filter syntax error: " + s);
        }

        return createSearchCriteria(
                matcher.group(NAME),
                !matcher.group(NEGATION).isEmpty(),
                matcher.group(OPERATOR).charAt(0),
                !matcher.group(STAR_PREFIX).isEmpty(),
                matcher.group(VALUE),
                !matcher.group(STAR_SUFFIX).isEmpty());

    }

    private static SearchCriteria createSearchCriteria(
            String name, boolean negated, char operator, boolean starPrefix, String value, boolean starSuffix) {
        switch (operator) {
            case ':':
                return createEqualsSearchCriteria(name, negated, starPrefix, value, starSuffix);
            case '<':
                return new SearchCriteria(name, LESS_THAN, value, negated);
            case '>':
                return new SearchCriteria(name, GREATER_THAN, value, negated);
            default:
                // impossible
                throw new IllegalArgumentException();
        }
    }

    private static SearchCriteria createEqualsSearchCriteria(
            String name, boolean negated, boolean starPrefix, String value, boolean starSuffix) {
        if (starPrefix && starSuffix) {
            return new SearchCriteria(name, CONTAINS, value, negated);
        } else if (starSuffix) {
            return new SearchCriteria(name, STARTS_WITH, value, negated);
        } else if (starPrefix) {
            return new SearchCriteria(name, ENDS_WITH, value, negated);
        } else {
            return new SearchCriteria(name, EQUALS, value, negated);
        }
    }
}
