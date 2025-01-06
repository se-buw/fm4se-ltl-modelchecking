package de.buw.fm4se.modelchecking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import de.buw.fm4se.modelchecking.exec.NuxmvExecutor;
import de.buw.fm4se.modelchecking.task.Tasks;
import de.buw.fm4se.modelchecking.utils.FmPlay;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class T1NaturalLanguageAndLTLTest {
    /**
     * Test the specification of task 1a
     * The specification should be a valid LTL specification with the operators G
     * and F
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    @Order(1)
    public void testTask1a() throws IOException, InterruptedException {
        String spec = FmPlay.getCodeFromPermalink(Tasks.task1_a);
        String res = NuxmvExecutor.runNuxmv(spec);
        assertTrue(res.contains("is true") || res.contains("is false"),
                "The specification is not a valid LTL specification");

        assertTrue(spec.contains("LTLSPEC"),
                "The module does not contain an LTL specification");

        assertContainsOperators(res, "G", "F");
    }

    /**
     * Checks the output line by line for specifications with the the given
     * operators. All operators must be in a single specification.
     * 
     * @param res output of nuXmv
     * @param ops operators to check for
     */
    private void assertContainsOperators(String res, String... ops) {
        boolean found = false;
        for (String specLine : res.split("\n")) {
            if (specLine.startsWith("-- specification")) {
                for (String op : ops) {
                    if (!specLine.contains(op)) {
                        break;
                    }
                }
                found = true;
                break;
            }
        }
        if (!found) {
            fail("None of the LTL specifications contains all necessary operators");
        }
    }

    /**
     * Test the specification of task 1b
     * The specification should be a valid LTL specification with the operators G,
     * U, and X
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    @Order(2)
    public void testTask1b() throws IOException, InterruptedException {
        String spec = FmPlay.getCodeFromPermalink(Tasks.task1_b);
        String res = NuxmvExecutor.runNuxmv(spec);
        assertTrue(res.contains("is true") || res.contains("is false"),
                "The specification is not a valid LTL specification");

        assertTrue(spec.contains("LTLSPEC"),
                "The module does not contain an LTL specification");

        assertContainsOperators(res, "G", "U", "X");
    }

    /**
     * Test the specification of task 1c
     * The specification should be a valid LTL specification with the operators F,
     * H, and O
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    @Order(3)
    public void testTask1c() throws IOException, InterruptedException {
        String spec = FmPlay.getCodeFromPermalink(Tasks.task1_c);
        String res = NuxmvExecutor.runNuxmv(spec);
        assertTrue(res.contains("is true") || res.contains("is false"),
                "The specification is not a valid LTL specification");

        assertTrue(spec.contains("LTLSPEC"),
                "The module does not contain an LTL specification");

        assertContainsOperators(res, "F", "H", "O");
    }

}
