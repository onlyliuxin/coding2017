package com.byhieg.coding2017test;

import com.byhieg.coding2017.BinaryTree;
import com.byhieg.coding2017.BinaryTreeNode;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/22.
 * Mail to byhieg@gmail.com
 */
public class BinaryTreeTest extends TestCase {

    public void testPrintTree() throws Exception {
        BinaryTree tree = new BinaryTree(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(6);
        tree.insert(4);
        tree.insert(8);
        tree.printTree();
    }

}