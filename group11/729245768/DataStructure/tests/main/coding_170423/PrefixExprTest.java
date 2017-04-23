package main.coding_170423;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by peter on 2017/4/23.
 */
public class PrefixExprTest extends TestCase {
    @Test
    public void testEvaluate() throws Exception {
        PrefixExpr pre = new PrefixExpr("- * + 3 4 5 6");
        Assert.assertEquals(29,pre.evaluate(),0.001f);
    }

}