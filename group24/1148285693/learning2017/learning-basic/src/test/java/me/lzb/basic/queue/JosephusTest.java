package me.lzb.basic.queue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class JosephusTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExecute() {

        //6没死啊
        Assert.assertEquals("[1, 3, 5, 0, 4, 2]", Josephus.execute(7, 2).toString());


        Assert.assertEquals("[2, 5, 1, 6, 4]", Josephus.execute(7, 3).toString());
    }

}
