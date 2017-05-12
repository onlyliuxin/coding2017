package com.donaldy.basic.tree;

import org.junit.Test;

import java.io.File;

/**
 * Created by DonaldY on 2017/5/11.
 */
public class FileListTest {

    @Test
    public void test() {

        FileList fileList = new FileList();

        fileList.list(new File("D:\\computer\\SoftwareEngineering\\软件工程讲稿(第三版)"));

    }


}
