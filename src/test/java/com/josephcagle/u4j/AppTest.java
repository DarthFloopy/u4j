package com.josephcagle.u4j;

import static com.josephcagle.u4j.TextStreamProducers.cat;
import static com.josephcagle.u4j.TextStreamProducers.echo;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        System.out.println(echo("hi there"));
        System.out.println(System.getProperty("user.dir"));
        try {
            cat("test.txt").grep("i").head(2).cat();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
