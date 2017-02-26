package com.coding.basic;
import org.junit.Test;
import  static  org.junit.Assert.assertEquals;

/**
 * Created by zhangwj on 2017/2/23.
 */
public class TestJunit {
    @Test

    public void testAdd()
    {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine", str);
    }

}
