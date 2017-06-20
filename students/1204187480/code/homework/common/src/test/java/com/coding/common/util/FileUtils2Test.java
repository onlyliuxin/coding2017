package com.coding.common.util;

import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by luoziyihao on 4/28/17.
 */
public class FileUtils2Test {
    @Test
    public void getCanonicalPath() throws Exception {
        System.out.println(FileUtils2.getCanonicalPath(new File("")));
    }

    @Test
    public void getBytes() throws Exception {
        byte[] bytes = FileUtils2.getBytes(new File("pom.xml"));
        System.out.println(new String(bytes));
        System.out.println(ByteUtils.byteToHexString(bytes));

    }


    @Test
    public void listAllFiles() throws Exception {
        String currentPath = new File("").getCanonicalPath();
        List<File> files = FileUtils2.listAllFiles(new File(currentPath ));
        for (File file : files) {
            System.out.println(file.getCanonicalPath());
        }
    }

}