package org.litejunit.v3.sample;


import org.litejunit.v3.*;
import org.litejunit.v3.runner.JUnitCore;

import static org.litejunit.v3.Assert.assertEquals;

/**
 * Created by john on 2017/9/4.
 */
public class CalculatorTest  {

    Calculator calculator =null;
    @Before
    public void prepare(){
        calculator = new Calculator();
    }
    @After
    public void clean(){
        calculator = null;
    }
    @Test
    public void testAdd(){

        calculator.add(10);
        assertEquals(15,calculator.getResult());
    }
    @Test
    public void testSubtract(){
        calculator.add(10);
        calculator.subtract(5);
        assertEquals(5,calculator.getResult());
    }
    @BeforeClass
    public static void prepareGlobalResouce(){
        System.err.println("prepare global resource");
    }
    @AfterClass
    public static void cleanGlobalResouce(){
        System.err.println("clean global resource");
    }


    public static void main(String[] args){
        JUnitCore.main("org.litejunit.v3.sample.CalculatorTest");
//        JUnitCore.runClasses(CalculatorTest.class);

    }
}