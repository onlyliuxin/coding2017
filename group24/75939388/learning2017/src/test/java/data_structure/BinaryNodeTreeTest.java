package data_structure;

import org.junit.Test;
import basic.dataStructure.BinaryTreeNode;

/**
 * @author : 温友朝
 * @date : 2017/4/5
 */
public class BinaryNodeTreeTest {

//    @Test
//    public BinaryTreeNode getTree(){
//        BinaryTreeNode btn = new BinaryTreeNode(5);
//        btn.setData(3);
//
//        return btn;
//    }

    @Test
    public void testAdd(){
        BinaryTreeNode btn = new BinaryTreeNode(5);
        btn.setData(3);
        btn.setData(7);
        btn.setData(10);
        btn.setData(6);
        btn.setData(4);
    }
}
