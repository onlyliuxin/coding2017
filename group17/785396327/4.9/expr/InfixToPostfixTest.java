package expr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IBM on 2017/4/23.
 */
public class InfixToPostfixTest {


    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testConvert() {
        {
            Assert.assertEquals("[2, 3, 4, *, +, 5, +]", InfixToPostfix.convert("2+3*4+5").toString());
        }
        {
            Assert.assertEquals("[3, 20, *, 12, 5, *, +, 40, 2, /, -]", InfixToPostfix.convert("3*20+12*5-40/2").toString());
        }

        {
            Assert.assertEquals("[3, 20, *, 2, /]", InfixToPostfix.convert("3*20/2").toString());
        }

        {
            Assert.assertEquals("[20, 2, /, 3, *]", InfixToPostfix.convert("20/2*3").toString());
        }
    }
}
