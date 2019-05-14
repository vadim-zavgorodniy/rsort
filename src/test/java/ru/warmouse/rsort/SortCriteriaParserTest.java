package ru.warmouse.rsort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by warmouse, Vadim Zavgorodniy <iwarmouse@gmail.com> on 18.12.18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SortCriteriaParserTest {

    @Spy
    private SortCriteriaParser sortParser;

    @Test
    public void parse_emptyString_emptyResult() {
        final List<SortCriteria> mustBeEmpty = sortParser.parse("");

        assertThat(mustBeEmpty).isEmpty();
    }

    @Test
    public void parse_nullString_emptyResult() {

        final List<SortCriteria> mustBeEmpty = sortParser.parse(null);

        assertThat(mustBeEmpty).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_unknownSortOrder_throwsIllegalArgumentException() {
        sortParser.parse("name:UNKNOWN");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_noField_throwsIllegalArgumentException() {
        sortParser.parse(":ASC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_fieldIsEmpty_throwsIllegalArgumentException() {
        sortParser.parse("  :ASC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_noOrder_throwsIllegalArgumentException() {
        sortParser.parse("name:");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_orderIsEmpty_throwsIllegalArgumentException() {
        sortParser.parse("name:  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_noColon_throwsIllegalArgumentException() {
        sortParser.parse("nameASC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_manyColon_throwsIllegalArgumentException() {
        sortParser.parse("name:ASC:DESC");
    }

    @Test
    public void parse_oneAscExpression_ascResult() {
        final List<SortCriteria> res = sortParser.parse("name:ASC");

        assertThat(res).hasSize(1);
        final SortCriteria criteria = res.get(0);
        assertThat(criteria.getField()).isEqualTo("name");
        assertThat(criteria.getOrder()).isEqualTo(SortCriteria.SortOrder.ASC);
    }

    @Test
    public void parse_oneDescExpression_descResult() {
        final List<SortCriteria> res = sortParser.parse("name:DESC");

        assertThat(res).hasSize(1);
        final SortCriteria criteria = res.get(0);
        assertThat(criteria.getField()).isEqualTo("name");
        assertThat(criteria.getOrder()).isEqualTo(SortCriteria.SortOrder.DESC);
    }

    @Test
    public void parse_threeExpressions_returnsThreeItems() {
        final List<SortCriteria> res = sortParser.parse("name:DESC,age:ASC,sex:ASC");

        assertThat(res).hasSize(3);
    }

    @Test
    public void parse_fieldWithSpaces_spacesAreIgnored() {
        final List<SortCriteria> res = sortParser.parse(" name:DESC");

        assertThat(res).hasSize(1);
        final SortCriteria criteria = res.get(0);
        assertThat(criteria.getField()).isEqualTo("name");
    }

    @Test
    public void parse_orderWithSpaces_spacesAreIgnored() {
        final List<SortCriteria> res = sortParser.parse("name:DESC ");

        assertThat(res).hasSize(1);
        final SortCriteria criteria = res.get(0);
        assertThat(criteria.getField()).isEqualTo("name");
    }

    @Test
    public void parse_twoExprWithSpaces_spacesAreIgnored() {
        final List<SortCriteria> res = sortParser.parse(" name:DESC , age:ASC");

        assertThat(res).hasSize(2);
        final SortCriteria criteria1 = res.get(0);
        final SortCriteria criteria2 = res.get(1);
        assertThat(criteria1.getField()).isEqualTo("name");
        assertThat(criteria2.getField()).isEqualTo("age");
    }

}
