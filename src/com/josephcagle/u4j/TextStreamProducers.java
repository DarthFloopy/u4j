
package com.josephcagle.u4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public final class TextStreamProducers {
    static TextStream echo(String s) {
        return new TextStream(Stream.of(s));
    }

    static TextStream cat(String filename) throws IOException {
        return new TextStream(
            Files.readAllLines(Path.of(filename)).stream()
        );
    }

    static TextStream cat() {
        List<String> lines = new LinkedList<>();
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNext()) {
                lines.add(in.nextLine());
            }
        } catch (Exception e) {
            throw e;
        }

        return new TextStream(lines.stream());
    }
}

