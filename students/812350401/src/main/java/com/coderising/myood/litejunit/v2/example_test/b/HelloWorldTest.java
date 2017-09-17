package com.coderising.myood.litejunit.v2.example_test.b;

import com.coderising.myood.litejunit.v2.TestCase;
import com.coderising.myood.litejunit.v2.example.b.HelloWorld;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class HelloWorldTest extends TestCase {

    public HelloWorldTest(String name) {
        super(name);
    }

    public void testSay(){
        HelloWorld hi = new HelloWorld();
        assertEquals("Hello,World1!", hi.Say());
    }
}
