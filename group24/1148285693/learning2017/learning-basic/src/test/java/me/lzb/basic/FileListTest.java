package me.lzb.basic;

import org.junit.Test;

import java.io.File;

/**
 * @author LZB
 * @date 2017/5/10
 */
public class FileListTest {

    @Test
    public void listTest(){
        File file = new File("D:\\code\\learning\\tmp");


        FileList list = new FileList();
        list.list(file);

    }

}
