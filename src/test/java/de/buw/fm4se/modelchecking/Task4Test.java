package de.buw.fm4se.modelchecking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import de.buw.fm4se.modelchecking.exec.NuxmvExecutor;
import de.buw.fm4se.modelchecking.task.Tasks;
import de.buw.fm4se.modelchecking.utils.FmPlay;


public class Task4Test {
    /**
     * Test the specification of task 1a
     * The specification should be a valid LTL specification with the operators G and F
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testTask1a() throws IOException, InterruptedException {
        String spec = FmPlay.getCodeFromPermalink(Tasks.task4);
        String testSpec = spec + "\nLTLSPEC\n!(F (knight[0]=7 & knight[1]=7));";
        String res = NuxmvExecutor.runNuxmv(testSpec);
        
        assertTrue(res.contains("!( F (knight[0] = 7 & knight[1] = 7))  is false"), 
        "Starting at (0,1) the knight can reach (7,7)");
        
        testSpec = spec + "\nLTLSPEC\n(knight[0]=0 & knight[1]=1);";
        res = NuxmvExecutor.runNuxmv(testSpec);
        assertTrue(res.contains("(knight[0] = 0 & knight[1] = 1)  is true"),
        "Knight is not starting at (0,1)");
        
        testSpec = spec + "\nLTLSPEC\n! next(knight[0]=2 & knight[1]=2);";
        res = NuxmvExecutor.runNuxmv(testSpec);
        assertTrue(res.contains("!next((knight[0] = 2 & knight[1] = 2))  is false"),
        "Starting at (0,1) the knight can reach (2,2) in the next step, which is not a legal move");
        
        testSpec = spec + "\nLTLSPEC\n! next(knight[0]=1 & knight[1]=2);";
        res = NuxmvExecutor.runNuxmv(testSpec);
        assertTrue(res.contains("!next((knight[0] = 1 & knight[1] = 2))  is true"),
        "Starting at (0,1) the knight can not reach (1,2) in the next step, which is a legal move");
        
    }
}
