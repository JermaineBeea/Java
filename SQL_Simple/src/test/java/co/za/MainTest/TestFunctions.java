package co.za.MainTest;

import org.junit.jupiter.api.Assertions;
import co.za.Main.QueryFunction;

public class TestFunctions {

    @org.junit.jupiter.api.Test
    public void testQueryFunction() {
        Long a = 5L;
        Long b = 3L;
        Long c = 2L;

        QueryFunction queryFunction = new QueryFunction(a, b, c);

        Assertions.assertEquals(5L, queryFunction.returnA());
        Assertions.assertEquals(3L, queryFunction.returnB());
        Assertions.assertEquals(2L, queryFunction.returnC());
    }

}
