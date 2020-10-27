package ru.kpfu.itis.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class Task14Test {

    @Test
    public void lowPositive() {
        Assertions.assertSame(24, Task14.calculateFactorial(4));
    }

    @Test
    public void lowPositive2() {
        Assertions.assertSame(120, Task14.calculateFactorial(5));
    }

    @Test
    public void one(){
        Assertions.assertSame(1, Task14.calculateFactorial(1));
    }

    @Test
    public void two(){
        Assertions.assertSame(2, Task14.calculateFactorial(2));
    }

    @Test
    public void zero(){
        Assertions.assertSame(1, Task14.calculateFactorial(0));
    }

    @Test
    @Timeout(1)
    public void negative(){
        try {
            int result = Task14.calculateFactorial(-10);
            Assertions.assertSame(-1, result);
        }
        catch(IllegalArgumentException ex){}
        catch(Exception ex){
            Assertions.fail("Wrong exception has been thrown");
        }
    }

    @Test
    @Timeout(1)
    public void veryBig(){
        Assertions.assertSame(-1, Task14.calculateFactorial(Integer.MAX_VALUE));
    }

}
