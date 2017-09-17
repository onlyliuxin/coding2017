package com.coderising.myood.litejunit.v2.example_test.b;

import com.coderising.myood.litejunit.v2.TestCase;
import com.coderising.myood.litejunit.v2.example.b.Byebye;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class ByebyeTest extends TestCase {
    public ByebyeTest(String name) {
        super(name);
    }
    public void testSay(){
        Byebye bye = new Byebye();
        assertEquals("Byebye!", bye.Say());
    }
}
