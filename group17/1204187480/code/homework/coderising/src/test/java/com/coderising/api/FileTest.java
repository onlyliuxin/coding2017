package com.coderising.api;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by luoziyihao on 4/28/17.
 */
public class FileTest {

    @Test
    public void testFile() {
        File file = new File("./hahah");
        Assert.assertFalse(file.isDirectory());

    }
}
