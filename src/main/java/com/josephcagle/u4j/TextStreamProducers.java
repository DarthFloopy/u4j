
package com.josephcagle.u4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * A utility class providing various methods to get {@link TextStream}s.
 *
 * @author Joseph Cagle
 */
public final class TextStreamProducers {

    /**
     * Constructs a {@link TextStream} of one line.
     *
     * If the argument contains one or more newlines, the returned
     * {@link TextStream} will contain multiple lines, consisting of the
     * substrings which were separated by the newline(s).
     *
     * @param s The line(s) of text to use in the {@link TextStream}
     * @return the new {@link TextStream}
     * @throws IllegalArgumentException if {@code s} is {@code null}
     */
    public static TextStream echo(String s) {
        if (s == null)
            throw new IllegalArgumentException("s must not be null");

        return new TextStream(
            List.of(s.split("\r?\n"))
        );
    }


    /**
     * Constructs a {@link TextStream} from a text file. The file is read using
     * {@link Files#readAllLines(Path)} and {@link Path#of(String)}. See those
     * methods for possible exceptions and their explanations.
     *
     * @param filename The name of the text file
     * @return a new {@link TextStream} of the lines of the text file
     * @throws IllegalArgumentException if {@code filename} is {@code null}
     * @see Files#readAllLines(Path)
     * @see Path#of(String, String...)
     */
    public static TextStream cat(String filename) throws IOException {
        if (filename == null)
            throw new IllegalArgumentException("filename must not be null");

        return new TextStream(
            Files.readAllLines(Path.of(filename))
        );
    }

    /**
     * Reads lines from {@link System#in} into a new {@link TextStream}.
     *
     * @return the new {@link TextStream}
     */
    public static TextStream cat() {
        List<String> lines = new LinkedList<>();
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNext()) {
                lines.add(in.nextLine());
            }
        } catch (Exception e) {
            throw e;
        }

        return new TextStream(lines);
    }
}

