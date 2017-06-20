package com.coding.basic.array;

import com.coding.basic.List;
import com.coding.basic.array.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by luoziyihao on 2/25/17.
 */
public class ArrayListTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private List list = new ArrayList();

    @Before
    public void before() {

    }

    @Test
    public void add() throws Exception {
        list.add(1);
    }

    @Test
    public void get() throws Exception {
        add();
        logger.info("{}", list.get(0));
    }

}