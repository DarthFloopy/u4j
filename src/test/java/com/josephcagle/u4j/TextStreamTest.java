package com.josephcagle.u4j;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Unit tests for {@link TextStream}
 */
public class TextStreamTest {

    private static final List<String> genericTestData =
        Collections.unmodifiableList(List.of("a", "b", "c"));

    @Test
    public void equals_itself_returnsTrue() throws Exception {
        TextStream ts = new TextStream(genericTestData);
        assertTrue(ts.equals(ts));
    }

    @Test
    public void equals_null_returnsFalse() throws Exception {
        TextStream ts = new TextStream(genericTestData);
        assertFalse(ts.equals(null));

    }

    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void equals_anyString_returnsFalse() throws Exception {
        TextStream ts = new TextStream(genericTestData);
        assertFalse(ts.equals("42"));
        assertFalse(ts.equals("flibbertigibbet"));
    }

    @Test
    public void equals_duplicateStream_returnsTrue() throws Exception {
        TextStream ts = new TextStream(genericTestData);
        TextStream ts2 = new TextStream(genericTestData);
        assertTrue(ts.equals(ts2));
    }

    @Test
    public void equals_differentStream_returnsFalse() throws Exception {
        TextStream ts = new TextStream(genericTestData);
        assertFalse(ts.equals(new TextStream(List.of("a", "b", "c", "d"))));
    }


    @Test
    public void hashCodesOfEqualObjectsAreEqual() throws Exception {
        int hc1 = new TextStream(List.of("a", "b", "c")).hashCode();
        int hc2 = new TextStream(genericTestData).hashCode();

        assertEquals(hc1, hc2);
    }

    @Test
    public void toStringReturnsExpectedResult() {
        TextStream ts = new TextStream(genericTestData);

        String expected =
            genericTestData.stream().collect(joining(System.lineSeparator()));
        String actual = ts.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void toListReturnsExpectedResult() {
        TextStream ts = new TextStream(genericTestData);

        List<String> expected = genericTestData;
        List<String> actual = ts.toList();

        assertEquals(expected, actual);
    }

    @Test
    public void toStreamReturnsExpectedResult() {
        TextStream ts = new TextStream(genericTestData);

        List<String> expected =
            genericTestData.stream().collect(Collectors.toList());
        List<String> actual = ts.toStream().collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    public void lengthReturnsExpectedResult() {
        TextStream ts = new TextStream(genericTestData);

        int expected = genericTestData.size();
        int actual = ts.length();

        assertEquals(expected, actual);
    }


    @Test
    public void head_typicalInput_returnsExpectedResult() {
        TextStream ts = new TextStream(genericTestData);

        TextStream expected = new TextStream(genericTestData.subList(0, 2));
        TextStream actual = ts.head(2);

        assertEquals(expected, actual);
    }

    @Test
    public void head_0_returnsExpectedResult() {
        TextStream ts = new TextStream(genericTestData);

        TextStream expected = new TextStream(Collections.<String>emptyList());
        TextStream actual = ts.tail(0);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void head_tooLargeInput_throwsIllegalArgumentException() {
        new TextStream(genericTestData).head(12345678);
    }

    @Test(expected = IllegalArgumentException.class)
    public void head_tooSmallInput_throwsIllegalArgumentException() {
        new TextStream(genericTestData).head(-12345678);

    }


    @Test
    public void tail_typicalInput_returnsExpectedResult() {
        TextStream ts = new TextStream(genericTestData);

        TextStream expected = new TextStream(genericTestData.subList(1, 3));
        TextStream actual = ts.tail(2);

        assertEquals(expected, actual);
    }

    @Test
    public void tail_0_returnsExpectedResult() {
        TextStream ts = new TextStream(genericTestData);

        TextStream expected = new TextStream(Collections.<String>emptyList());
        TextStream actual = ts.tail(0);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tail_tooLargeInput_throwsIllegalArgumentException() {
        new TextStream(genericTestData).tail(12345678);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tail_tooSmallInput_throwsIllegalArgumentException() {
        new TextStream(genericTestData).tail(-12345678);
    }

}

