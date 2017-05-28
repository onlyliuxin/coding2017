package tree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author jiaxun
 */
public class TestBinarySearchTree {

    BinarySearchTree<Integer> tree = null;

    @Before
    public void setUp() throws Exception {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(6);
        root.setLeft(new BinaryTreeNode<>(2));
        root.setRight(new BinaryTreeNode<>(8));
        root.getLeft().setLeft(new BinaryTreeNode<>(1));
        root.getLeft().setRight(new BinaryTreeNode<>(4));
        root.getLeft().getRight().setLeft(new BinaryTreeNode<>(3));
        root.getLeft().getRight().setRight(new BinaryTreeNode<>(5));
        tree = new BinarySearchTree<>(root);
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
        Assert.assertEquals(7, tree.size());
    }

    @Test
    public void testRemoveLeaf() {
        tree.remove(3);
        BinaryTreeNode<Integer> root= tree.getRoot();
        Assert.assertEquals(4, root.getLeft().getRight().getData().intValue());
    }

    @Test
    public void testRemoveMiddleNode1() {
        tree.remove(4);
        BinaryTreeNode<Integer> root= tree.getRoot();
        Assert.assertEquals(5, root.getLeft().getRight().getData().intValue());
        Assert.assertEquals(3, root.getLeft().getRight().getLeft().getData().intValue());
    }

    @Test
    public void testRemoveMiddleNode2() {
        tree.remove(2);
        BinaryTreeNode<Integer> root= tree.getRoot();
        Assert.assertEquals(3, root.getLeft().getData().intValue());
        Assert.assertEquals(4, root.getLeft().getRight().getData().intValue());
    }

    @Test
    public void testLevelVisit() {
        List<Integer> values = tree.levelVisit();
        Assert.assertEquals("[6, 2, 8, 1, 4, 3, 5]", values.toString());

    }
    @Test
    public void testLCA(){
        Assert.assertEquals(2,tree.getLowestCommonAncestor(1, 5).intValue());
        Assert.assertEquals(2,tree.getLowestCommonAncestor(1, 4).intValue());
        Assert.assertEquals(6,tree.getLowestCommonAncestor(3, 8).intValue());
    }
    @Test
    public void testIsValid() {

        Assert.assertTrue(tree.isValid());

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(6);
        root.setLeft(new BinaryTreeNode<>(2));
        root.setRight(new BinaryTreeNode<>(8));
        root.getLeft().setLeft(new BinaryTreeNode<>(4));
        root.getLeft().setRight(new BinaryTreeNode<>(1));
        root.getLeft().getRight().setLeft(new BinaryTreeNode<>(3));
        tree = new BinarySearchTree<>(root);

        Assert.assertFalse(tree.isValid());
    }

    @Test
    public void testGetNodesBetween(){
        List<Integer> numbers = this.tree.getNodesBetween(3,  8);
        System.out.println(numbers.toString());
    }
}
