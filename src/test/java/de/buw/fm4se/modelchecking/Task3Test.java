package de.buw.fm4se.modelchecking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3Test {

    /**
     * Test the specification of task 3a
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testTask3a() {
        File file = new File("src/resources/task3a.txt");
        
        assertTrue(file.exists(), "File not found");
        assertTrue(file.length() > 0, "File is empty");
        try (
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("Loop starts here")) {
                    return;
                }
            }
            assertTrue(false, "No Counterexample found");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test the specification of task 3b
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testTask3b() {
        File file = new File("src/resources/task3b.txt");
        
        assertTrue(file.exists(), "File not found");
        assertTrue(file.length() > 0, "File is empty");
        try (
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("Loop starts here")) {
                    return;
                }
            }
            assertTrue(false, "No Counterexample found");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
