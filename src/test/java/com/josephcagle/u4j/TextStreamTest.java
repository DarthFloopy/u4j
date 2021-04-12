package com.josephcagle.u4j;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Unit tests for {@link TextStream}
 */
public class TextStreamTest {

    private static final List<String> genericTestData = List.of("a", "b", "c");

    @Test
    public void testEquals() throws Exception {
        TextStream ts = new TextStream(genericTestData);
        TextStream ts2 = new TextStream(genericTestData);

        assertTrue(ts.equals(ts));
        assertFalse(ts.equals(null));
        assertFalse(ts.equals("42"));

        assertTrue(ts.equals(ts2));
        assertFalse(ts.equals(new TextStream(List.of("a", "b", "c", "d"))));
    }

    @Test
    public void testHashCode() throws Exception {
        int hc1 = new TextStream(List.of("a", "b", "c")).hashCode();
        int hc2 = new TextStream(genericTestData).hashCode();

		assertEquals(hc1, hc2);
    }

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

    @Test
    public void testHead() {
        TextStream ts = new TextStream(genericTestData);

        TextStream expected = new TextStream(genericTestData.subList(0, 2));
        TextStream actual = ts.head(2);

        assertEquals(expected, actual);
    }

    @Test
    public void testTail() {
        TextStream ts = new TextStream(genericTestData);

        TextStream expected = new TextStream(genericTestData.subList(1, 3));
        TextStream actual = ts.tail(2);

        assertEquals(expected, actual);
    }
}
