package com.coding.api;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by luoziyihao on 2/25/17.
 */
public class ArraysTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void testCopyOf(){
        Object[] a = new Object[]{1, 2, 3, 4};
        Object[] b = Arrays.copyOf(a, 10);
        logger.info("a={}, b={}", Arrays.toString(a), Arrays.toString(b));
    }
}
