package main.coding_170423;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by peter on 2017/4/23.
 */
public class PostfixExprTest extends TestCase {
    @Test
    public void testEvaluate() throws Exception {
        PostfixExpr post1 = new PostfixExpr("3 4 + 5 * 6 -");
        Assert.assertEquals(29,post1.evaluate(),0.001f);

        PostfixExpr post2 = new PostfixExpr("2 8 4 / + 5 -");
        Assert.assertEquals(-1,post2.evaluate(),0.001f);

    }

}