package com.coderising.myknowledgepoint;

/**
 * Created by thomas_young on 31/12/2017.
 */
public class ATest {
    public static void main(String[] args) {
        System.out.println(ATest.class.getClassLoader().getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
