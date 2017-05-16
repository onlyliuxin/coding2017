package com.aaront.execrise.basic;

import com.aaront.exercise.basic.FileList;
import org.junit.Test;

import java.io.File;

/**
 * @author tonyhui
 * @since 17/5/8
 */
public class FileListTest {

    @Test
    public void testList() {
        FileList list = new FileList();
        File file = new File("/Users/tonyhui/Code/coding2017/group01/954958168");
        list.list(file, 0);
    }
}
