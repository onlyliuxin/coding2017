package com.github.wdn.coding2017.basic.stack.expr;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
public class Operator {
    private static Map<String,Integer> priorityMap = new HashMap();
    static{
        priorityMap.put("+",1);
        priorityMap.put("-",1);
        priorityMap.put("*",2);
        priorityMap.put("/",2);
    }
    public static int compare(String a, String b){
        int result = priorityMap.get(a)-priorityMap.get(b);
        return result == 0 ? 0 : result > 0 ? 1 : -1;
    }
    public static boolean isOperator(char o){
        return o=='+' || o=='-'|| o=='*'|| o=='/';
    }
    public static float calculate(float a,float b,String operator) throws IllegalAccessException {
        float result;
        switch (operator) {
            case "+":
                result = a+b;
                break;
            case "-":
                result = a-b;
                break;
            case "*":
                result = a*b;
                break;
            case "/":
                result = a/b;
                break;
            default:
                throw new IllegalAccessException();
        }
        return result;
    }
}
