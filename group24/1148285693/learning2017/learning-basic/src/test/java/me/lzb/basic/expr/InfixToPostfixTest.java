package me.lzb.basic.expr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by LZB on 2017/4/20.
 */
public class InfixToPostfixTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testChange() {
        {
            InfixToPostfix toPostfix = new InfixToPostfix("((2+3)*8+5+3)*6");
            Assert.assertEquals("2 3 + 8 * 5 + 3 + 6 *", toPostfix.change());
        }

        {
            InfixToPostfix toPostfix = new InfixToPostfix("6*(5+(2+3)*8+3)");
            Assert.assertEquals("6 5 2 3 + 8 * + 3 + *", toPostfix.change());
        }
        {
            InfixToPostfix toPostfix = new InfixToPostfix("9+(3-1)*3+10/2");
            Assert.assertEquals("9 3 1 - 3 * + 10 2 / +", toPostfix.change());
        }

        {
            InfixToPostfix toPostfix = new InfixToPostfix("10-2*3+50");
            Assert.assertEquals("10 2 3 * - 50 +", toPostfix.change());
        }
    }
}
