package za.co.wethinkcode;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class Methods{

    private int var1 = 10;

    public void modify(int num){
        var1 += num;
    }

    public int getVar(){
        return var1;
    }
}

class TestMethods{

 
    @Test
    void testModify(){
        Methods methods = new Methods();
        methods.modify(5);
        assertEquals(15, methods.getVar());
    }
}