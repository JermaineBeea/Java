package za.co.wethinkcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestMethods {

    @Test
    void testModify(){
        Methods methods = new Methods();
        methods.modify(5);
        assertEquals(15, methods.getVar());
    }
    
}
