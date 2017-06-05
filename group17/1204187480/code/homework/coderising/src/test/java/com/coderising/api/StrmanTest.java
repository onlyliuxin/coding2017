package com.coderising.api;

import org.junit.Test;
import strman.Strman;

/**
 * Created by luoziyihao on 5/3/17.
 */
public class StrmanTest {

    @Test
    public void testFormat() {

        System.out.println(Strman.format("className is not found in classpath, className={1}", ",333 ","cccc"));

    }
}
