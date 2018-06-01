package no.itera.bloggingplatform.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void shouldJoinThreeWords() {        // name of the test should be concise, short and descriptive
        // arrange (input conditions of the test case)
        List<String> elements = Arrays.asList("first", "second", "third");

        // act (run the tested method)
        String actual = StringUtils.join(elements, ",");

        // assert (output conditions of the test case)
        assertEquals("first,second,third", actual);
    }

    @Test
    public void shouldJoinOneWord() {
        List<String> elements = Arrays.asList("first");

        String actual = StringUtils.join(elements, ",");

        assertEquals("first", actual);
    }

    @Test
    public void shouldJoinZeroWords() {
        List<String> elements = new ArrayList<>();

        String actual = StringUtils.join(elements, ",");

        assertEquals("", actual);
    }

    @Test
    public void shouldReturnEmptyStringWhenElementsAreNull() {
        List<String> elements = null;

        String actual = StringUtils.join(elements, ",");

        assertEquals("", actual);
    }

    @Test
    public void shouldOmitNullElements() {
        List<String> elements = Arrays.asList("first", null, "third");

        String actual = StringUtils.join(elements, ",");

        assertEquals("first,third", actual);
    }

    @Test
    public void shouldJoinWhenSeparatorIsNull() {
        List<String> elements = Arrays.asList("first", "second", "third");

        String actual = StringUtils.join(elements, null);

        assertEquals("first,second,third", actual);
    }
}