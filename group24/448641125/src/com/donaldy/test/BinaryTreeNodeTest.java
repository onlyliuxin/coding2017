package com.donaldy.test;

import com.donaldy.basic.BinaryTreeNode;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by donal on 2017/3/11.
 */
public class BinaryTreeNodeTest {

    private BinaryTreeNode root = new BinaryTreeNode(10);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        BinaryTreeNode node = new BinaryTreeNode(4);
        BinaryTreeNode node2 = new BinaryTreeNode(13);
        root.setLeft(node);
        root.setRight(node2);
        node.setLeft(new BinaryTreeNode(3));
        node2.setRight(new BinaryTreeNode(19));
    }

    @Test
    public void testSet() {
        root.getLeft().setData(4);
        assertEquals(4, root.getLeft().getData());
        assertEquals(13, root.getRight().getData());
        assertEquals(5, root.insert(5).getData());
    }

}
