package org.xukai.common;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xukai
 * @desc
 * @date 2017-02-20-下午 5:02
 */
public class BinaryTreeNodeTest {


    @Test
    public void testInsert() throws Exception {
        BinaryTreeNode node = new BinaryTreeNode();
        node.insert(5);
        node.insert(9);
        node.insert(3);
        node.insert(7);
        node.insert(2);
        node.insert(8);
        node.insert(4);
        node.insert(6);
        node.insert(1);
    }
}