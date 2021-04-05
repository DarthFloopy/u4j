
package com.josephcagle.u4j;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.regex.Pattern;
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

    /*
     * From here on is a bunch of transformations on TextStream
     * TODO: Add links to manpages for each emulated Unix tool
     */

    public final TextStream head(int numLines) {
        return new TextStream(
            this.lines.limit(numLines).collect(toList()).stream()
        );
    }
    public final TextStream head() { return head(10); }

    public final TextStream grep(String regex) {
        return new TextStream(
            this.lines
                .filter(line -> {
                    return Pattern.compile(regex).matcher(line).find();
                }).collect(toList()).stream()
        );
    }

    public final void cat() {
        lines.forEach(System.out::println);
    }
}

