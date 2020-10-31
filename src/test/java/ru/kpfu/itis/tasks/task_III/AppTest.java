package ru.kpfu.itis.tasks.task_III;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.kpfu.itis.tasks.task_III.App;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class AppTest {
    private static final String ERROR_STRING = "Error:";

    private PrintStream originalOut;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void before(){
        originalOut = System.out;
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void after(){
        System.setOut(originalOut);
    }

    @Test
    public void lineEquality(){
        App.main(new String[0]);
        String[] lines = testOut.toString().split("\\R");
        int firstLength = lines.length;
        for(String line:lines){
            if(line.length() != firstLength){
                Assertions.fail("Some line's length is not equal to the first's.");
            }
        }
    }

    @Test
    public void outputFormat(){
        App.main(new String[0]);
        Pattern p = Pattern.compile("(?:(?:\\d+ +){9}\\R+){9}", Pattern.DOTALL);
        Assertions.assertTrue(p.matcher(testOut.toString()).matches());
    }
}
