package code07;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yyglider on 2017/4/19.
 */
public class Token {

    public static final List<String> OPERATORS = Arrays.asList("+","-","*","/");

    private static final Map<String,Integer> priorities = new HashMap<String, Integer>();

    static {
        priorities.put("+",1);
        priorities.put("-",1);
        priorities.put("*",2);
        priorities.put("/",2);
    }

    public static final int OPERATOR = 1;
    public static final int NUMBER = 2;

    private String value;
    private int type;

    public Token(int type, String value) {
        this.value = value;
        this.type = type;
    }

    public boolean isNumber(){
        return type == NUMBER;
    }

    public boolean isOperator(){
        return type == OPERATOR;
    }

    public int getIntValue(){
        return Integer.valueOf(value).intValue();
    }

    public String getStringValue(){
        return value;
    }

    public int getType() {
        return type;
    }

    public boolean hasHigherPriority(Token t){
        if(!this.isOperator() && !t.isOperator()){
            throw new RuntimeException("numbers can't compare priority");
        }
        return priorities.get(this.value) - priorities.get(t.value) > 0;
    }

}
