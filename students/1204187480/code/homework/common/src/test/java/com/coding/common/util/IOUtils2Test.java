package com.coding.common.util;

import org.junit.Test;

import java.util.List;

import static com.coding.common.util.FileUtils2.openStream;
import static com.coding.common.util.IOUtils2.readToStringList;
import static org.junit.Assert.*;

/**
 * <p>
 * <p>
 *
 * @author raoxiang <xiangrao@qilin99.com>
 * @version 6/13/17
 * @since 1.8
 */
public class IOUtils2Test {
    @Test
    public void readToStringListTest() throws Exception {
        List<String> poms = readToStringList(openStream("test.json"));
        System.out.println(poms);
    }

}