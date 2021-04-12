
package com.josephcagle.u4j;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A TextStream represents an immutable, finite stream of lines of text. You
 * can transform TextStreams to process data.
 */
public class TextStream {
    private final List<String> lines;

    protected TextStream(List<String> lines) {
        this.lines = lines;
    }

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

    public final TextStream head(int numLines) {
        return new TextStream(this.lines.subList(0, numLines));
    }
    public final TextStream head() { return head(10); }

    public final TextStream grep(String regex) {
        return new TextStream(
            this.lines.stream()
                .filter(line -> {
                    return Pattern.compile(regex).matcher(line).find();
                })
                .collect(Collectors.toList())
        );
    }

    public final void cat() {
        lines.forEach(System.out::println);
    }
}

