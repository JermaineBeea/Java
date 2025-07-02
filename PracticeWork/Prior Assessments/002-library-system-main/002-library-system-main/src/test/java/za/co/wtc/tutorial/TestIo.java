package za.co.wtc.tutorial;

import java.math.BigDecimal;
import java.util.List;

import za.co.wethinkcode.io.Io;
import za.co.wethinkcode.prompt.Checker;
import za.co.wethinkcode.prompt.Prompt;
import za.co.wethinkcode.prompt.Reply;

public class TestIo implements Io {

    @Override
    public Io println(String var1){
        return this;
    };

    public Io println(){
        return this;
    };

    public Io print(String var1){
        return this;
    };

    public Prompt prompt(String var1, Checker... var2){
        return new Prompt(var1, var2);
    };

    public int anyInteger(String var1){
        return 0;
    };

    public String anyString(String var1){
        return "";
    };

    public double anyDouble(String var1){
        return 0;
    };

    public float anyFloat(String var1){
        return 0;
    };

    public BigDecimal anyDecimal(String var1){
        return BigDecimal.valueOf(0);
    };

    public String nonEmpty(String var1){
        return "";
    };

    public List<Reply> manyIntegers(String var1, int var2){
        return null;
    };

    
}
