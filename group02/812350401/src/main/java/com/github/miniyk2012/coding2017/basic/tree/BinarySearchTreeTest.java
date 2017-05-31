package com.github.miniyk2012.coding2017.basic.tree;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertTrue;


public class BinarySearchTreeTest {
	
	BinarySearchTree<Integer> tree = null;
	BinarySearchTree<Integer> tree2 = null;
	BinarySearchTree<Integer> tree3 = null;

	@Before
	public void setUp() throws Exception {
	    /**
	          6
             / \
	        2   8
           / \
          1   4
             /
            3

         */
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(1);
		root.left.right = new BinaryTreeNode<Integer>(4);
		root.left.right.left = new BinaryTreeNode<Integer>(3);
		tree = new BinarySearchTree<Integer>(root);

		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(5);
        tree2 = new BinarySearchTree<>(root2);

        BinaryTreeNode<Integer> root3 = new BinaryTreeNode<>(5);
        root3.left = new BinaryTreeNode<Integer>(6);
        tree3 = new BinarySearchTree<>(root3);

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
		tree.remove(4);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.right.data.intValue());
		
	}
	@Test
	public void testRemoveMiddleNode() {
		tree.remove(2);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.data.intValue());
		Assert.assertEquals(4, root.left.right.data.intValue());
	}

	@Test
	public void testRemoveRoot() {
		tree.remove(6);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(8, root.data.intValue());
		Assert.assertEquals(2, root.left.data.intValue());
		Assert.assertNull(root.right);
		Assert.assertEquals(3, root.left.right.left.data.intValue());

	}

	@Test
	public void testIsValid() {
        assertTrue(tree.isValid());
        assertTrue(tree2.isValid());
        Assert.assertFalse(tree3.isValid());

        BinarySearchTree<Integer> tree4 = new BinarySearchTree<>(tree.root.left);
        assertTrue(tree4.isValid());

        tree.root.right.right = new BinaryTreeNode<>(7);
        Assert.assertFalse(tree.isValid());
        assertTrue(tree4.isValid());

    }

	@Test
	public void testLevelVisit() {
        List<Integer> list = tree.levelVisit();
        Assert.assertEquals("[6, 2, 8, 1, 4, 3]", Arrays.toString(list.toArray()));
        List<Integer> list2 = tree2.levelVisit();
        Assert.assertEquals("[5]", Arrays.toString(list2.toArray()));
        BinarySearchTree<Integer> nullTree = new BinarySearchTree<Integer>(null);
        List<Integer> nullList = nullTree.levelVisit();
        Assert.assertEquals("[]", Arrays.toString(nullList.toArray()));
    }

    @Test
    public void testGetLowestCommonAncestor() {
	    int ancestor = tree.getLowestCommonAncestor(1, 3);
        Assert.assertEquals(2, ancestor);
        ancestor = tree.getLowestCommonAncestor(2, 3);
        Assert.assertEquals(2, ancestor);
        ancestor = tree.getLowestCommonAncestor(2, 6);
        Assert.assertEquals(6, ancestor);
        ancestor = tree.getLowestCommonAncestor(1, 8);
        Assert.assertEquals(6, ancestor);
        ancestor = tree.getLowestCommonAncestor(3, 4);
        Assert.assertEquals(4, ancestor);
    }

    @Test
    public void testGetNodesBetween() {
	    List<Integer> list = tree.getNodesBetween(1, 4);
	    List <Integer> expectedList = Arrays.asList(2, 3);
        assertTrue(list.containsAll(expectedList) && expectedList.containsAll(list));

        list = tree.getNodesBetween(6, 8);
        expectedList = Arrays.asList();
        assertTrue(list.containsAll(expectedList) && expectedList.containsAll(list));

        list = tree.getNodesBetween(4, 6);
        expectedList = Arrays.asList();
        assertTrue(list.containsAll(expectedList) && expectedList.containsAll(list));

        list = tree.getNodesBetween(1, 6);
        expectedList = Arrays.asList(2, 4, 3);
        assertTrue(list.containsAll(expectedList) && expectedList.containsAll(list));

        list = tree.getNodesBetween(1, 8);
        expectedList = Arrays.asList(2, 6, 4, 3);
        assertTrue(list.containsAll(expectedList) && expectedList.containsAll(list));


        // 当n1, n2不一定为某节点值时
        list = tree.getNodesBetween(5, 8);
        expectedList = Arrays.asList(6);
        assertTrue(list.containsAll(expectedList) && expectedList.containsAll(list));

        list = tree.getNodesBetween(-1, 9);
        expectedList = Arrays.asList(1, 2, 6, 4, 3, 8);
        assertTrue(list.containsAll(expectedList) && expectedList.containsAll(list));

    }

}
