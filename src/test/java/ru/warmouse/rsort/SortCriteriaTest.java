package ru.warmouse.rsort;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * <p>Created by warmouse, Vadim Zavgorodniy (zavgorodniy@infsys.ru) on 14.05.19.
 */
public class SortCriteriaTest {
    @Test
    public void ctor_initializeFields() {
        final SortCriteria sortCriteria = new SortCriteria("field", SortCriteria.SortOrder.ASC);

        assertThat(sortCriteria.getField()).isEqualTo("field");
        assertThat(sortCriteria.getOrder()).isEqualTo(SortCriteria.SortOrder.ASC);
    }

    @Test
    public void toString_containsFieldValues() {
        final SortCriteria sortCriteria = new SortCriteria("fieldName", SortCriteria.SortOrder.ASC);

        assertThat(sortCriteria.toString()).contains("fieldName", SortCriteria.SortOrder.ASC.toString());
    }
}
