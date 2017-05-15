package datastructure.stack.expr;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TokenParserTest {

    @Test
    public void test() {
        
        {
            TokenParser tokenParser = new TokenParser("2+3*4+5");
            List<Token> tokenList = tokenParser.parse();
            Assert.assertEquals("2", tokenList.get(0).getValue());
            Assert.assertEquals("*", tokenList.get(3).getValue());
            Assert.assertEquals("5", tokenList.get(6).getValue());
        }
        {
            TokenParser tokenParser = new TokenParser("3*20+12*5-40/2");
            List<Token> tokenList = tokenParser.parse();
            Assert.assertEquals("20", tokenList.get(2).getValue());
            Assert.assertEquals("12", tokenList.get(4).getValue());
            Assert.assertEquals("5", tokenList.get(6).getValue());
        }
        
    }
}
