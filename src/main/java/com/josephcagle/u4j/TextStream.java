
package com.josephcagle.u4j;

import static java.util.stream.Collectors.joining;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A TextStream represents an immutable, finite stream of lines of text. You
 * can transform TextStreams to process text data.
 *
 * @author Joseph Cagle
 */
public class TextStream {
    private final List<String> lines;

    /**
     * The constructor takes a {@link List} of lines of text. To create a
     * {@link TextStream} based on other types of sources, such as single lines or
     * whole files, use methods in {@link TextStreamProducers}. (E.g.,
     * {@link TextStreamProducers#echo(String)} gives you a TextStream from a
     * single line.)
     *
     * @param lines A {@link List} of the lines of text for this {@link TextStream}.
     */
    public TextStream(List<String> lines) {
        if (lines == null)
            throw new IllegalArgumentException("lines must not be null");
        for (String line : lines)
            if (line == null)
                throw new IllegalArgumentException("lines must not contain null");

        // make sure it's immutable -- see e.g. #toList()
        this.lines = Collections.unmodifiableList(lines);
    }

    /**
     *
     * Compares the specified object with this TextStream for equality.
     *
     * @return true if and only if the specified object is {@code instanceof}
     *         {@link TextStream} and it contains the same lines of text.
     */
    @Override
    public final boolean equals(Object that) {
        if (this == that)
            return true;
        if (that == null)
            return false;

        if (!(that instanceof TextStream))
            return false;
        TextStream tsThat = (TextStream) that;

        return this.lines.equals(tsThat.lines);
    }

    /**
     * Implements a hash code method in accordance with the equals() contract.
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    @Override
    public int hashCode() {
        return this.lines.hashCode();
    }

    /**
     * Returns the text data as a {@link String}.
     *
     * @return this {@link TextStream}'s lines of text, separated by
     *         {@link System#lineSeparator()}.
     */
    @Override
    public final String toString() {
        return lines.stream().collect(joining(System.lineSeparator()));
    }

    /**
     *
     * @return this TextStream's lines of text in a {@link List}.
     */
    public final List<String> toList() {
        return lines;
    }

    /**
     *
     * @return this TextStream's lines of text in a {@link Stream}.
     */
    public final Stream<String> toStream() {
        return lines.stream();
    }

    /**
     *
     * @return the length of this TextStream, in lines of text.
     */
    public final int length() {
        return lines.size();
    }


    /*
     * From here on is a bunch of transformations on TextStream
     * TODO: Add links to manpages for each emulated Unix tool
     */

    /**
     * Gets the first {@code numLines} lines of text.
     *
     * @param numLines the number of lines to take from the beginning
     * @return a new {@link TextStream} of the first {@code numLines} lines of this
     *         {@link TextStream}
     * @throws IllegalArgumentException if {@code lines} < 0 or if {@code lines} >
     *                                  {@link #length()}
     */
    public final TextStream head(int numLines) {
        if (numLines < 0)
            throw new IllegalArgumentException("numLines must be >= 0");
        if (numLines > length())
            throw new IllegalArgumentException("numLines must be <= length()");

        return new TextStream(this.lines.subList(0, numLines));
    }

    /**
     * A convenience method, which calls {@link #head(int)} with an argument of 10.
     *
     * @return {@code head(10)}
     */
    public final TextStream head() { return head(10); }

    /**
     * Gets the last {@code numLines} lines of text.
     *
     * @param numLines the number of lines to take from the end
     * @return a new {@link TextStream} of the last {@code numLines} lines of this
     *         {@link TextStream}
     * @throws IllegalArgumentException if {@code lines} < 0 or if {@code lines} >
     *                                  {@link #length()}
     */
    public final TextStream tail(int numLines) {
        if (numLines < 0)
            throw new IllegalArgumentException("numLines must be >= 0");
        if (numLines > length())
            throw new IllegalArgumentException("numLines must be <= length()");

        return new TextStream(
            lines.subList(lines.size() - numLines, lines.size())
        );
    }

    /**
     * Gets all lines containing at least one match for a regex.
     *
     * @param regex A string containing the regex to search for
     * @return A new {@link TextStream} of all lines containing a match for
     *         {@code regex}
     * @throws IllegalArgumentException if {@code regex} is invalid
     */
    public final TextStream grep(String regex) {
        if (regex == null)
            throw new IllegalArgumentException("regex must not be null");

        Pattern pattern;
        try {
            pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException(e);
        }

        return new TextStream(this.lines.stream()
            .filter(line -> {
                return pattern.matcher(line).find();
            })
            .collect(Collectors.toList())
        );
    }

    /**
     * Prints all lines of this {@link TextStream} to System.out.
     */
    public final void cat() {
        for (String line : lines)
            System.out.println(line);
    }
}

