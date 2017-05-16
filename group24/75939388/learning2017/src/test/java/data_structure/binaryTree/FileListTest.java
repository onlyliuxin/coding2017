package data_structure.binaryTree;

import basic.dataStructure.binaryTree.FileList;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by macvi on 2017/5/16.
 */
public class FileListTest {

    FileList fl = null;

    @Before
    public void init(){
        fl = new FileList();
    }

    @Test
    public void test(){
        String path1 = "E:\\Downloads";
        String path2 = "E:\\系统ISO";

        fl.list(new File(path2), 0);
    }
}
