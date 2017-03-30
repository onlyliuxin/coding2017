package com.coding.api;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by luoziyihao on 2/25/17.
 */
public class SystemTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

        @Test
    public void testArrayCopy() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] b = new int[]{11, 22, 33, 44, 55, 66, 77};
        System.arraycopy(a, 2, b, 4, 3);
        logger.info("b={}", Arrays.toString(b));

    }
}
