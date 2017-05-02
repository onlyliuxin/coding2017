package data_structure.stack;

import basic.dataStructure.stack.expr.InfixExpr;
import basic.dataStructure.stack.expr.InfixToPostfix;
import basic.dataStructure.stack.expr.PostfixExpr;
import basic.dataStructure.stack.expr.PrefixExpr;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : 温友朝
 * @date : 2017/4/27
 */
public class ExprTest {


    @Test
    public void testInfixEvaluate() {
        //InfixExpr expr = new InfixExpr("300*20+12*5-20/4");

//		{
//			InfixExpr expr = new InfixExpr("2+3*4+5");
//			Assert.assertEquals(19.0, expr.evaluate(), 0.001f);
//		}
        {
            InfixExpr expr = new InfixExpr("3*20+12*5-40/2");
            Assert.assertEquals(100.0, expr.evaluate(), 0.001f);
        }

        {
            InfixExpr expr = new InfixExpr("3*20/2");
            Assert.assertEquals(30, expr.evaluate(), 0.001f);
        }

        {
            InfixExpr expr = new InfixExpr("20/2*3");
            Assert.assertEquals(30, expr.evaluate(), 0.001f);
        }

        {
            InfixExpr expr = new InfixExpr("10-30+50");
            Assert.assertEquals(30, expr.evaluate(), 0.001f);
        }
        {
            InfixExpr expr = new InfixExpr("10-2*3+50");
            Assert.assertEquals(54, expr.evaluate(), 0.001f);
        }
    }


    @Test
    public void testPostfixEvaluate() {
        {
            PostfixExpr expr = new PostfixExpr("6 5 2 3 + 8 * + 3 + *");
            Assert.assertEquals(288, expr.evaluate(),0.0f);
        }
        {
            //9+(3-1)*3+10/2
            PostfixExpr expr = new PostfixExpr("9 3 1-3*+ 10 2/+");
            Assert.assertEquals(20, expr.evaluate(),0.0f);
        }
        {
            //10-2*3+50
            PostfixExpr expr = new PostfixExpr("10 2 3 * - 50 +");
            Assert.assertEquals(54, expr.evaluate(),0.0f);
        }
    }

    @Test
    public void testPrefixEvaluate() {
        {
            // 2*3+4*5
            PrefixExpr expr = new PrefixExpr("+ * 2 3* 4 5");
            Assert.assertEquals(26, expr.evaluate(),0.001f);
        }
        {
            // 4*2 + 6+9*2/3 -8
            PrefixExpr expr = new PrefixExpr("-++6/*2 9 3 * 4 2 8");
            Assert.assertEquals(12, expr.evaluate(),0.001f);
        }
        {
            //(3+4)*5-6
            PrefixExpr expr = new PrefixExpr("- * + 3 4 5 6");
            Assert.assertEquals(29, expr.evaluate(),0.001f);
        }
        {
            //1+((2+3)*4)-5
            PrefixExpr expr = new PrefixExpr("- + 1 * + 2 3 4 5");
            Assert.assertEquals(16, expr.evaluate(),0.001f);
        }
    }

    @Test
    public void textInfixToPostfixExpr(){
        {
            //9+(3-1)*3+10/2  =   9 3 1 - 3 * 10 2 / + +
            String expr = "9+(3-1)*3+10/2";
            Assert.assertEquals("[9, 3, 1, -, 3, *, 10, 2, /, +, +]", InfixToPostfix.convert(expr).toString());
        }
        {
            //10-2*3+50       =   10 2 3 * 50 + -
            String expr = "10-2*3+50";
            Assert.assertEquals("[10, 2, 3, *, 50, +, -]", InfixToPostfix.convert(expr).toString());
        }
    }
}
