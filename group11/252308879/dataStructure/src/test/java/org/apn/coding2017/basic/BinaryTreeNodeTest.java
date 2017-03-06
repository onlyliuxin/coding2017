package org.apn.coding2017.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pan on 2017/2/26.
 */
public class BinaryTreeNodeTest {

    BinaryTreeNode binaryTreeNode;

    @Before
    public void setUp() throws Exception {
        binaryTreeNode = new BinaryTreeNode();
        binaryTreeNode.push(1, "A");
        binaryTreeNode.push(2, "B");
        binaryTreeNode.push(3, "C");
        binaryTreeNode.push(4, "D");
    }

    @Test
    public void size() throws Exception {
        System.out.println(binaryTreeNode.size());
    }

    @Test
    public void get() throws Exception {
        System.out.println(binaryTreeNode.get(3));
    }

    @Test
    public void push() throws Exception {
        binaryTreeNode.push(5, "E");
        System.out.println(binaryTreeNode);
    }

}