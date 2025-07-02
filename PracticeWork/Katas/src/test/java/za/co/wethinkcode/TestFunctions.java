package za.co.wethinkcode;

import static za.co.wethinkcode.Methods.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestFunctions {
    
    @Test
    public void test_sum(){
        assertEquals(7, sumFunction(3, 4));
    }
}
