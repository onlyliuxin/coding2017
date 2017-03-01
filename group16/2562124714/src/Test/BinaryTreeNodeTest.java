package Test;

import com.coding.basic.BinaryTreeNode;
import com.coding.basic.TreeData;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangwj on 2017/2/23.
 */
public class BinaryTreeNodeTest {
    private BinaryTreeNode binaryTree = new BinaryTreeNode();
    @Before
    public void setUp() throws Exception {
        //System.out.println("初始化二叉树，5, 4, 7");
        TreeData element = new TreeData();
        element.setT((Integer)5);
        binaryTree.insert(element);
        TreeData element2 = new TreeData();
        element2.setT((Integer)4);
        binaryTree.insert(element2);
        TreeData element3 = new TreeData();
        element3.setT((Integer)7);
        binaryTree.insert(element3);
//        binaryTree.PriOder(this.binaryTree);
    }

    @Test
    public void getData() throws Exception {
        assertEquals(5, binaryTree.getData().getT());

    }

    @Test
    public void setData() throws Exception {
        TreeData element = new TreeData();
        element.setT(6);
        binaryTree.setData(element);
        assertEquals(6, binaryTree.getData().getT());
//        binaryTree.PriOder(this.binaryTree);

    }

    @Test
    public void getLeft() throws Exception {
        assertEquals(4, binaryTree.getLeft().getData().getT());

    }

    @Test
    public void setLeft() throws Exception {
        TreeData element = new TreeData();
        element.setT(2);
        BinaryTreeNode NewTreeNode = new BinaryTreeNode();
        NewTreeNode.setData(element);
        binaryTree.setLeft(NewTreeNode);
        assertEquals(2, binaryTree.getLeft().getData().getT());
//        binaryTree.PriOder(this.binaryTree);
    }

    @Test
    public void getRight() throws Exception {
        assertEquals(7, binaryTree.getRight().getData().getT());

    }

    @Test
    public void setRight() throws Exception {
        TreeData element = new TreeData();
        element.setT(9);
        BinaryTreeNode NewTreeNode = new BinaryTreeNode();
        NewTreeNode.setData(element);
        binaryTree.setRight(NewTreeNode);
        assertEquals(9, binaryTree.getRight().getData().getT());
    }

    @Test
    public void priOder() throws Exception {


    }

    @Test
    public void insert() throws Exception {
        TreeData element = new TreeData();
        element.setT(2);
        binaryTree.insert(element);
        binaryTree.PriOder(this.binaryTree);
        element.setT(9);
        binaryTree.insert(element);
        binaryTree.PriOder(this.binaryTree);
        element.setT(8);
//        binaryTree.PriOder(this.binaryTree);

    }

}