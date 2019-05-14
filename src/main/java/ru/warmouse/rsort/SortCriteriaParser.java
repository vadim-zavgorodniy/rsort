package ru.warmouse.rsort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorting expression parser.
 *
 * <p>Created by warmouse, Vadim Zavgorodniy (iwarmouse@gmail.com) on 18.12.18.
 */
public class SortCriteriaParser {

    private static final String INVALID_SORT_ITEM = "Invalid sorting expression: '%s'. Must be 'name:(ASC|DESC)'";

    /**
     * Parses string containing sorting conditions list.
     * Sorting conditions separated by "," (comma) symbol.
     *
     * @param expr string with sorting expressions
     *             Sample: name:DESC,age:ASC
     * @return sorting conditions list
     * @throws IllegalArgumentException in case invalid expression string
     */
    public List<SortCriteria> parse(String expr) throws IllegalArgumentException {
        if (expr == null || expr.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(expr.split(","))
                .map(this::parseItem)
                .collect(Collectors.toList());
    }

    private SortCriteria parseItem(String item) {
        final String[] parts = item.split(":");

        if (parts.length != 2
                || parts[0].trim().isEmpty()
                || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException(String.format(INVALID_SORT_ITEM, item));
        }

        final String field = parts[0].trim();
        final SortCriteria.SortOrder order = SortCriteria.SortOrder.valueOf(parts[1].trim());

        return new SortCriteria(field, order);
    }
}
