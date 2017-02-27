package com.coding.api;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luoziyihao on 2/25/17.
 */
public class ArrayListTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testAdd(){
        List<Object> list = new ArrayList<Object>(Arrays.asList(0, 1, 2, 3));
        logger.info("list={}", list);
        list.add(5, 2);
        logger.info("list={}", list);
    }
}
