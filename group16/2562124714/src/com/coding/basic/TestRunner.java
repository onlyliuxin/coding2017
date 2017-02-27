package com.coding.basic;

import com.sun.net.httpserver.Authenticator;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runners.model.TestClass;

import javax.xml.transform.Result;

/**
 * Created by zhangwj on 2017/2/23.
 */
public class TestRunner {
    public  static void main(String[] args)
    {
        org.junit.runner.Result result = JUnitCore.runClasses(TestJunit.class);

        for (Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
