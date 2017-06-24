package com.coderising.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by luoziyihao on 4/28/17.
 */
@Slf4j
public class FileTest {

    @Test
    public void testFile() {
        File file = new File("./hahah");
        Assert.assertFalse(file.isDirectory());

    }

    @Test
    public void  testAbsolutePath() throws IOException {
        File file = new File("../src");
        log.info("isDirectory={}", file.isDirectory());
        log.info(file.getAbsolutePath());
        log.info(file.getCanonicalPath());
        log.info(file.getName());
        log.info(file.getPath());
    }
}
