package com.coderising.jvm.test;

public class Example{
    public void disp(char c){
        System.out.println(c);
    }
    public void disp(int c){
       System.out.println(c );
    }
    public static void main(String args[]){
        Example obj = new Example();
        obj.disp('a');
        obj.disp(5);
    }
}

