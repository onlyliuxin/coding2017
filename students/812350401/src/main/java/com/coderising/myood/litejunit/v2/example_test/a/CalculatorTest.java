package com.coderising.myood.litejunit.v2.example_test.a;

import com.coderising.myood.litejunit.v2.Assert;
import com.coderising.myood.litejunit.v2.TestCase;
import com.coderising.myood.litejunit.v2.example.a.Calculator;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class CalculatorTest extends TestCase {
    public CalculatorTest(String name) {
        super(name);
    }

    /** *//**
     * 在Junit3.8中，测试方法满足如下原则
     * 1)public
     * 2)void
     * 3)无方法参数
     * 4）最重要的方法名称必须以test开头
     */
    private Calculator cal;

    //在执行每个test之前，都执行setUp；
    public void setUp(){
        cal = new Calculator();
    }

    //在执行每个test之后，都要执行tearDown
    public void tearDown(){
    }

    public void testAdd()
    {
        Calculator cal = new Calculator();
        int result = cal.add(1, 2);
        //断言assert
        Assert.assertEquals(3, result);
    }

    public void testMinus()
    {
        Calculator cal = new Calculator();
        int result = cal.minus(5, 2);
        throw new RuntimeException("我是异常");
//        Assert.assertEquals(3, result);
    }

    public void testMultiply()
    {
        Calculator cal = new Calculator();
        int result = cal.multiply(4, 2);
        Assert.assertEquals(8,result);
    }

    public void testDivide()
    {
        Calculator cal = new Calculator();
        int result = 0;
        try {
            result = cal.divide(10,5);
        } catch (Exception e) {
            e.printStackTrace();
            //我们期望result = cal.divide(10,5);正常执行；如果进入到catch中说明失败；
            //所以我们加上fail。
            Assert.fail();//如果这行没有执行。说明这部分正确。
        }
        Assert.assertEquals(2,result);
    }

    public void testDivide2()
    {
        Throwable tx = null;
        try
        {
            // Calculator cal = new Calculator();
            cal.divide(10,0);
            //正常来讲cal.divide(10,0);已经抛出异常，之后的代码不会被执行。
            //我们也期望是这样的。所以说如果下面的Assert.fail();执行了。
            //我的的测试就失败了。
            Assert.fail();//当执行到这里测试失败，后面的代码将不被执行。
        } catch (Exception e) {
            tx = e;
        }
        Assert.assertNotNull(tx);      //断言tx不为空。也就是说肯定有异常。
        Assert.assertEquals(Exception.class,tx.getClass());//断言tx的类型为Exception类型
        Assert.assertEquals("除数不能为零", tx.getMessage());
    }
}
