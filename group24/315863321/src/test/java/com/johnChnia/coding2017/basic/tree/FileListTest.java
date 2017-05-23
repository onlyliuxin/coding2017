package com.johnChnia.coding2017.basic.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by john on 2017/5/20.
 */
public class FileListTest {
    private FileList fileList;
    private static final String PATH = "/Users/john/Desktop/zst";

    @Before
    public void setUp() throws Exception {
        fileList = new FileList();
    }

    @Test
    public void list() throws Exception {
        FileList.TreeInfo treeInfo = fileList.list(new File(PATH));
        Assert.assertEquals(3, treeInfo.dirs.size());
        Assert.assertEquals(8, treeInfo.files.size());

    }

}