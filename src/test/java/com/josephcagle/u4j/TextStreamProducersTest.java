
package com.josephcagle.u4j;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TextStreamProducersTest {

    @Test(expected = IllegalArgumentException.class)
    public void echo_null_throwsIllegalArgumentException() {
        TextStreamProducers.echo(null);
    }

    @Test
    public void echo_stringOfText_returnsExpectedResult() {
        TextStream expected = new TextStream(List.of("who's on second?"));
        TextStream actual = TextStreamProducers.echo("who's on second?");

        assertEquals(expected, actual);
    }

    @Test
    public void echo_emptyString_returnsExpectedResult() {
        TextStream expected = new TextStream(List.of(""));
        TextStream actual = TextStreamProducers.echo("");

        assertEquals(expected, actual);
    }

    @Test
    public void echo_stringWithUnixNewlines_returnsExpectedResult() {
        TextStream expected =
            new TextStream(List.of("no, Who's on first", "what?"));
        TextStream actual =
            TextStreamProducers.echo("no, Who's on first\nwhat?");

        assertEquals(expected, actual);
    }

    @Test
    public void echo_stringWithWindowsNewlines_returnsExpectedResult() {
        TextStream expected =
            new TextStream(List.of("Who's on first.", "What's on second."));
        TextStream actual =
            TextStreamProducers.echo("Who's on first.\r\nWhat's on second.");

        assertEquals(expected, actual);
    }

    @Test
    public void echo_stringWithWindowsAndUnixNewlines_returnsExpectedResult() {
        TextStream expected =
            new TextStream(List.of(
                "Tell me the name",
                "of the guy on third.",
                "I don't know"));

        TextStream actual =
            TextStreamProducers.echo(
                "Tell me the name\nof the guy on third.\r\nI don't know"
            );

        assertEquals(expected, actual);
    }
}

