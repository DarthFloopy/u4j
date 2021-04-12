
package com.josephcagle.u4j;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A TextStream represents an immutable, finite stream of lines of text. You
 * can transform TextStreams to process data.<br>
 * <br>
 * - Implementation note: TextStream data is stored in a {@link Stream} of
 *   {@link String}s.  This means that operations returning a transformed
 *   TextStream instance must {@link Stream#collect(java.util.stream.Collector)
 *   collect()} the elements and then {@link java.util.Collection#stream()
 *   stream()} them again so we get a stateful pipeline.
 */
public class TextStream {
    private final Stream<String> lines;

    protected TextStream(Stream<String> lines) {
        this.lines = lines;
    }

    @Override
    public final String toString() {
        return lines.collect(joining(System.lineSeparator()));
    }

    /**
     *
     * @return this TextStream's lines of text in a {@link List}.
     */
    public final List<String> toList() {
        return lines.collect(Collectors.toList());
    }

    /**
     *
     * @return this TextStream's lines of text in a {@link Stream}.
     */
    public final Stream<String> toStream() {
        return lines;
    }

    /**
     *
     * @return the length of this TextStream, in lines of text.
     */
    public final int length() {
        return lines.collect(Collectors.toList()).size();
    }


    /*
     * From here on is a bunch of transformations on TextStream
     * TODO: Add links to manpages for each emulated Unix tool
     */

    public final TextStream head(int numLines) {
        return new TextStream(
            this.lines.limit(numLines).collect(Collectors.toList()).stream()
        );
    }
    public final TextStream head() { return head(10); }

    public final TextStream grep(String regex) {
        return new TextStream(
            this.lines
                .filter(line -> {
                    return Pattern.compile(regex).matcher(line).find();
                }).collect(Collectors.toList()).stream()
        );
    }

    public final void cat() {
        lines.forEach(System.out::println);
    }
}

