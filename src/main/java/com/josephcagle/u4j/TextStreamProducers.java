
package com.josephcagle.u4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class TextStreamProducers {

    public static TextStream echo(String s) {
        if (s == null)
            throw new IllegalArgumentException("s must not be null");
        return new TextStream(List.of(s));
    }

    public static TextStream cat(String filename) throws IOException {
        return new TextStream(
            Files.readAllLines(Path.of(filename))
        );
    }

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

