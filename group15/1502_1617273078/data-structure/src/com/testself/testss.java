package com.testself;

import java.lang.reflect.Method;

/**
 * Created by Funy on 2017/3/4.
 */
public class testss {
    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.coderising.litestruts.LoginAction");
            Method[] methods=c.getDeclaredMethods();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
