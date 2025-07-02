package za.co.wethinkcode;

import java.util.List;

public abstract class MyClass {
    public abstract Long function1 (int arg);
    public abstract List<Integer> function2();
}

class Test extends MyClass{

    @Override
    public Long function1(int arg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Integer> function2(){
        return null;
    }
}
