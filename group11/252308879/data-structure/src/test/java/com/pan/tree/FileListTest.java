package com.pan.tree;

import org.junit.Test;

import java.io.File;

/**
 * Created by Pan on.
 * 2017/5/9
 */
public class FileListTest {

    @Test
    public void testFileList(){
        FileList fileList = new FileList();
        fileList.list(new File("E:\\DevWorkspace\\GitHub\\coding2017\\group11"));
    }

}
