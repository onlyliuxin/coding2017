package org.litejunit.v3.sample;


import org.litejunit.v3.Test;
import org.litejunit.v3.runner.RunWith;
import org.litejunit.v3.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.litejunit.v3.Assert.assertEquals;


/**
 * Created by john on 2017/9/6.
 */
@RunWith(Parameterized.class)//1.必须
public class ParametTestUnit {
    private int input;
    private boolean expected;//expected result

    /**
     * 2.public 构造器赋值一组测试数据
     */
    public ParametTestUnit(int input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }

    /**
     * 3.由@Parameterized.Parameters修饰一个
     * public static Collection xxx()
     */
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {1, true},
                {3, true},//
                {6, false},
                {11, true},
                {22, false},
                {23, true}
        });
    }

    /**
     * 4.JUnit循环地使用各组数据
     */
    @Test
    public void testOdd() {
        System.out.println("Parameterized Number is : " + input);
        assertEquals(expected, input % 2 != 0);
    }
}
