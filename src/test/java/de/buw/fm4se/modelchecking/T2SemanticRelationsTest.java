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
public class T2SemanticRelationsTest {

    /**
     * Test the specification of task 2a
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    @Order(1)
    public void testTask2a() throws IOException, InterruptedException {
        String spec = FmPlay.getCodeFromPermalink(Tasks.task2_a);
        String res = NuxmvExecutor.runNuxmv(spec);
        assertTrue(res.contains("((TRUE U fileWritten) -> (!(!((( X openFile) U openFile) U ( X ( X openFile))) U !(!((!truncate & fileWritten) xor  X FALSE))) U fileWritten))  is false"),
            "Check for phi -> psi is missing");
        
        assertTrue(res.contains("((!(!((( X openFile) U openFile) U ( X ( X openFile))) U !(!((!truncate & fileWritten) xor  X FALSE))) U fileWritten) -> (TRUE U fileWritten))  is true"), 
            "Check for psi -> phi is missing");

        assertTrue(res.contains("((TRUE U fileWritten) <-> (!(!((( X openFile) U openFile) U ( X ( X openFile))) U !(!((!truncate & fileWritten) xor  X FALSE))) U fileWritten))  is false"), 
            "Check for phi <-> psi is missing");
    }


    /**
     * Test the specification of task 2b
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    @Order(2)
    public void testTask2b() throws IOException, InterruptedException {
        String spec = FmPlay.getCodeFromPermalink(Tasks.task2_b);
        String res = NuxmvExecutor.runNuxmv(spec);
        assertTrue(res.contains("((( X (brewing |  G brewing)) U depressure) -> ( F !(!FALSE U !depressure) & !(!( G depressure) U !(!(depressure & !p1)))))  is false"),
            "Check for phi -> psi is missing");
        
        assertTrue(res.contains("(( F !(!FALSE U !depressure) & !(!( G depressure) U !(!(depressure & !p1)))) -> (( X (brewing |  G brewing)) U depressure))  is false"), 
            "Check for psi -> phi is missing");

        assertTrue(res.contains("((( X (brewing |  G brewing)) U depressure) <-> ( F !(!FALSE U !depressure) & !(!( G depressure) U !(!(depressure & !p1)))))  is false"), 
            "Check for phi <-> psi is missing");
    }

    /**
     * Test the specification of task 2c
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    @Order(3)
    public void testTask2c() throws IOException, InterruptedException {
        String spec = FmPlay.getCodeFromPermalink(Tasks.task2_c);
        String res = NuxmvExecutor.runNuxmv(spec);
        assertTrue(res.contains("((TRUE U !( F ( X !(!( X FALSE) U !greenLight)))) ->  G ( F !greenLight))  is true"),
            "Check for phi -> psi is missing");
        
        assertTrue(res.contains("( G ( F !greenLight) -> (TRUE U !( F ( X !(!( X FALSE) U !greenLight)))))  is true"), 
            "Check for psi -> phi is missing");

        assertTrue(res.contains("((TRUE U !( F ( X !(!( X FALSE) U !greenLight)))) <->  G ( F !greenLight))  is true"), 
            "Check for phi <-> psi is missing");
    }
    
}
