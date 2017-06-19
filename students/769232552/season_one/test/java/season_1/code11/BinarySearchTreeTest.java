package code11;

import static org.junit.Assert.fail;

import code10.BinaryTreeNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;


public class BinarySearchTreeTest {

    BinarySearchTree<Integer> tree = null;

    @Before
    public void setUp() throws Exception {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
        root.left = new BinaryTreeNode<Integer>(2);
        root.right = new BinaryTreeNode<Integer>(8);
        root.left.left = new BinaryTreeNode<Integer>(1);
        root.left.right = new BinaryTreeNode<Integer>(4);
        root.left.right.left = new BinaryTreeNode<Integer>(3);
        tree = new BinarySearchTree<Integer>(root);
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }

    @Test
    public void testFindMin() {
        Assert.assertEquals(1, tree.findMin().intValue());

    }

    @Test
    public void testFindMax() {
        Assert.assertEquals(8, tree.findMax().intValue());
    }

    @Test
    public void testHeight() {
        Assert.assertEquals(4, tree.height());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(6, tree.size());
    }

    @Test
    public void testRemoveLeaf() {
        tree.remove(3);
        BinaryTreeNode<Integer> root= tree.getRoot();
        Assert.assertEquals(4, root.left.right.data.intValue());

    }

    @Test
    public void testRemoveMiddleNode() {
        tree.remove(2);
        BinaryTreeNode<Integer> root= tree.getRoot();
        Assert.assertEquals(3, root.left.data.intValue());
        Assert.assertEquals(4, root.left.right.data.intValue());
    }

    @Test
    public void testLevelVisit(){
        Assert.assertEquals(Arrays.asList(6, 2, 8, 1, 4, 3), tree.levelVisit());
    }

    @Test
    public void testGetLowestCommonAncestor(){
        Assert.assertEquals(new Integer(6),tree.getLowestCommonAncestor(2,8));
        Assert.assertEquals(new Integer(2),tree.getLowestCommonAncestor(1,4));
    }

    @Test
    public void testGetNodesBetween(){
        Assert.assertEquals(Arrays.asList(1,2,4), tree.getNodesBetween(1,4));
    }

    @Test
    public void testIsValid(){
        Assert.assertTrue(tree.isValid());
        tree.root.left = new BinaryTreeNode<Integer>(12);
        Assert.assertFalse(tree.isValid());
    }
}