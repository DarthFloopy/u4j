package com.josephcagle.u4j;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Unit tests for {@link TextStream}
 */
public class TextStreamTest {

    private static final List<String> genericTestData = List.of("a", "b", "c");

    @Test
    public void testToString() {
        TextStream ts = new TextStream(genericTestData);

        String expected =
            genericTestData.stream().collect(joining(System.lineSeparator()));
        String actual = ts.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testToList() {
		TextStream ts = new TextStream(genericTestData);

        List<String> expected = genericTestData;
        List<String> actual = ts.toList();

		assertEquals(expected, actual);
    }

    @Test
    public void testToStream() {
        TextStream ts = new TextStream(genericTestData);

        List<String> expected =
            genericTestData.stream().collect(Collectors.toList());
        List<String> actual = ts.toStream().collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    public void testLength() {
        TextStream ts = new TextStream(genericTestData);

        int expected = genericTestData.size();
        int actual = ts.length();

        assertEquals(expected, actual);
    }
}
