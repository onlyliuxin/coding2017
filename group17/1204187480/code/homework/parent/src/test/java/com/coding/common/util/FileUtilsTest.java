package com.coding.common.util;

import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by luoziyihao on 4/28/17.
 */
public class FileUtilsTest {


    @Test
    public void listAllFiles() throws Exception {
        String currentPath = new File("").getAbsolutePath();
        List<File> files = FileUtils.listAllFiles(new File(currentPath ));
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

}