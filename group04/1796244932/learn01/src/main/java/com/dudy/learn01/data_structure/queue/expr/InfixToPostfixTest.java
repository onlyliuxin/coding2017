package com.dudy.learn01.data_structure.queue.expr;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by dudy on 2017/5/7.
 */
public class InfixToPostfixTest {


    @Test
    public void testEvaluate() {
        List<Token> tokens = InfixToPostfix.convert("2+3*4+5");
        System.out.println(tokens);

        Assert.assertEquals("[2, 3, 4, *, +, 5, +]", tokens.toString());

        //10 2 3 * - 50 +
        List<Token> list1 = InfixToPostfix.convert("10-2*3+50");
        System.out.println(list1);

        Assert.assertEquals("[10, 2, 3, *, -, 50, +]", list1.toString());
    }
}
