package com.coding.week6.exprNew;

import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
public class TokenParserTest {

    @Test
    public void testParse() throws Exception {
        TokenParser tokenParser = new TokenParser();
        List<Token> tokens = tokenParser.parse("3* 20+1 2*5-40/2");
        System.out.println(tokens);
    }
}