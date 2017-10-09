
package net.entrofi.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for Springs pagination support
 *
 * @author Hasan COMAK
 */
public final class SpringPagingHelper {

    public static final String DEFAULT_DIRECTION_DELIMITER = ",";

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringPagingHelper.class);

    private SpringPagingHelper() {

    }

    /**
     * Generates a spring pageable for the given parameters with default delimiter {@link #DEFAULT_DIRECTION_DELIMITER}
     *
     * @param limit page size
     * @param offset offset to start
     * @param sortQuery query string which is delimited with {{@link #DEFAULT_DIRECTION_DELIMITER}}
     * @return created boundary or null if the page is not in the boundary i.e. offset or limit is less than 0.
     */
    public static Pageable generatePageable(int limit, int offset, String sortQuery) {
        return generatePageable(limit, offset, sortQuery, DEFAULT_DIRECTION_DELIMITER);
    }


    /**
     * Generates a spring pageable for the given parameters where the #sortQuery is delimited with
     * #sortDirectionDelimiter
     *
     * @param limit page size
     * @param offset page offset
     * @param sortQuery query string
     * @param sortDirectionDelimiter delimiter used in sort query string
     * @return created boundary or null if the page is not in the boundary i.e. offset or limit is less than 0.
     */
    public static Pageable generatePageable(int limit, int offset, String sortQuery, String sortDirectionDelimiter) {
        if (!isInLimitOffsetBoundary(limit, offset)) {
            return null;
        }
        boolean sortEmpty = StringUtils.isEmpty(sortQuery);
        boolean delimiterEmpty = StringUtils.isEmpty(sortDirectionDelimiter);
        Sort sort;
        if (!sortEmpty && !delimiterEmpty) {
            sort = parseSort(sortQuery, sortDirectionDelimiter);
            return new PageRequest(offset, limit, sort);
        } else if (!sortEmpty && delimiterEmpty) {
            sort = parseSort(sortQuery, DEFAULT_DIRECTION_DELIMITER);
            return new PageRequest(offset, limit, sort);
        }
        return new PageRequest(offset, limit);
    }


    /**
     * Generates a spring pageable for the given parameters with default delimiter {@link #DEFAULT_DIRECTION_DELIMITER}
     *
     * @param limit
     * @param offset
     * @param sort
     * @return created boundary or null if the page is not in the boundary i.e. offset or limit is less than 0.
     */
    public static Pageable generatePageable(int limit, int offset, Sort sort) {
        if (!isInLimitOffsetBoundary(limit, offset)) {
            return null;
        }
        return new PageRequest(offset, limit, sort);
    }

    /**
     * Parses the <code>#source</code> using  <code>#directionDelimiter</code> parameterd and generates a
     * {@link Sort} instance.
     *
     * @param source assumed to be not null (no null check)
     * @param directionDelimiter query string delimiter.
     * @return parsed {@link Sort} or null.
     */
    public static Sort parseSort(String source, String directionDelimiter) {
        LOGGER.debug("Parsing page sort matrix..." + source);
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        List<Sort.Order> orderList = new ArrayList<Sort.Order>();
        String[] ordersStrings = source.split("\\&");

        for (String orderParam : ordersStrings) {
            String[] parts = orderParam.split(directionDelimiter);

            Sort.Direction direction = parts.length <= 1 ? null : Sort.Direction.fromString(parts[parts.length - 1]);

            for (int i = 0; i < parts.length; i++) {
                if (i == parts.length - 1 && direction != null) {
                    continue;
                }
                String property = parts[i];
                if (StringUtils.hasText(property)) {
                    orderList.add(new Sort.Order(direction, property));
                }
            }
        }
        return orderList.isEmpty() ? null : new Sort(orderList);
    }

    /**
     * Generates a page from a list of items and pageable
     * @param list contains all items (not just the page content)
     * @param pageable can be null
     * @param <T>
     * @return
     */
    public static <T> Page<T> generatePage(List<T> list, Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<>(list);
        }
        int lastOffset = pageable.getOffset() + pageable.getPageSize();
        if (lastOffset > list.size()) {
            lastOffset = list.size();
        }
        return new PageImpl<>(list.subList(pageable.getOffset(), lastOffset), pageable, list.size());
    }

    /**
     * DOC documenation:method
     *
     * @param limit
     * @param offset
     * @return
     */
    private static boolean isInLimitOffsetBoundary(int limit, int offset) {
        if (limit > 0 && offset >= 0) {
            return true;
        }
        LOGGER.warn(MessageFormatter.format("Provided limit and offset values {} " +
                        "are not in logically suitable boundaries ",
                String.valueOf(limit), String.valueOf(offset)).getMessage());
        return false;
    }
}
