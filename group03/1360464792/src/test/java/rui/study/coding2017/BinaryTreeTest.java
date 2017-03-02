package rui.study.coding2017;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 二叉树测试
 * Created by 赵睿 on 2017/2/25.
 */
public class BinaryTreeTest {
    @Test
    public void insert() throws Exception {
        BinaryTree binaryTree=new BinaryTree();
        binaryTree.insert(4);
        binaryTree.insert(7);
        binaryTree.insert(5);
        binaryTree.insert(8);
        binaryTree.insert(6);
        binaryTree.insert(3);
        binaryTree.insert(0);
        binaryTree.insert(1);
        binaryTree.insert(2);
        LinkedList linkedList=binaryTree.inorder();

        Iterator iterator=linkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }



}