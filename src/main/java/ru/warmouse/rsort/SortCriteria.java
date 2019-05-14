package ru.warmouse.rsort;

/**
 * Sorting criteria item.
 *
 * <p>Created by warmouse, Vadim Zavgorodniy (iwarmouse@gmail.com) on 18.12.18.
 */
public class SortCriteria {
    /**
     * Sorting order.
     */
    public enum SortOrder {
        /**
         * Ascending.
         */
        ASC,
        /**
         * Descending.
         */
        DESC
    }

    private final String field;
    private final SortOrder order;

    /**
     * Constructor.
     *
     * @param field field name
     * @param order sorting order
     */
    public SortCriteria(String field, SortOrder order) {
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public SortOrder getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "SortCriteria{"
                + "field='" + field + '\''
                + ", order=" + order
                + '}';
    }
}
