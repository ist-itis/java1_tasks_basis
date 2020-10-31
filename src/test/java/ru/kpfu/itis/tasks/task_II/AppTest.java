package ru.kpfu.itis.tasks.task_II;

import org.junit.jupiter.api.*;

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
    public void inputCountCheck(){
        for(int i=0;i<3;i++) {
            try {
                App.main(new String[i]);
                Assertions.assertTrue(testOut.toString().startsWith(ERROR_STRING));
            } catch (ArrayIndexOutOfBoundsException ex) {
                Assertions.fail("No check for input length.");
            }
        }
    }

    @Test
    public void nValueCheck(){
        App.main(new String[]{"-1", "1", "10"});
        String out = testOut.toString();
        Assertions.assertTrue(out.startsWith(ERROR_STRING));
    }


    @Test
    public void aBeforeBValueCheck(){
        App.main(new String[]{"10", "10", "1"});
        Assertions.assertTrue(testOut.toString().startsWith(ERROR_STRING));
    }

    @Test
    public void outputFormat(){
        App.main(new String[]{"10", "1", "10"});
        Pattern p = Pattern.compile("^(?:\\d+\\R){2}\\d+(?:\\.\\d{1,2})?\\R*", Pattern.DOTALL);
        Assertions.assertTrue(p.matcher(testOut.toString()).matches());
    }
}
