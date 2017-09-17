package com.coderising.myood.litejunit.v2.example.a;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class Calculator {
    public int add(int a,int b){
        return a + b;
    }
    public int minus(int a,int b){
        return a - b;
    }
    public int multiply(int a, int b ){
        return a * b;
    }
    public int divide(int a , int b )throws Exception
    {
        if(b == 0){
            throw new Exception("除数不能为零");
        }
        return a / b;
    }
}
