package tree;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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
		System.out.println(tree.height());
		Assert.assertEquals(4, tree.height());

	}

	@Test
	public void testSize() {
		Assert.assertEquals(6, tree.size());
	}

//	@Test
//	public void testRemoveLeaf() {
//		tree.remove(4);
//		BinaryTreeNode<Integer> root= tree.getRoot();
//		Assert.assertEquals(3, root.left.right.data.intValue());
//		
//	}

//	@Test
//	public void testRemoveMiddleNode() {
//		tree.remove(2);
//		BinaryTreeNode<Integer> root= tree.getRoot();
//		Assert.assertEquals(3, root.left.data.intValue());
//		Assert.assertEquals(4, root.left.right.data.intValue());
//	}
	
	
	
	@Test
	public void testLevelVisit() {
		List<Integer> result = tree.levelVisit();
		//List<Integer> expected = new ArrayList<>();
		Integer[] e1 = {6,2,8,1,4,3};
		List<Integer> expected = Arrays.asList(e1);
		Assert.assertEquals(expected, result);
		
	}
	
	@Test
	public void testIsValidNode() {
		
		Assert.assertEquals(true, tree.isValid());
		tree = null;
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(3);
		root.left.right = new BinaryTreeNode<Integer>(4);
		root.left.right.left = new BinaryTreeNode<Integer>(3);
		tree = new BinarySearchTree<Integer>(root);
		
		Assert.assertEquals(false, tree.isValidBST(root));
		tree = null;
		
	}
	
	@Test
	public void testGetLowestCommonAncestor() {
	
		Assert.assertEquals(new Integer(2), tree.getLowestCommonAncestor(new Integer(1),new Integer(3)));
		Assert.assertEquals(new Integer(6), tree.getLowestCommonAncestor(new Integer(8),new Integer(1)));
		
	}
	@Test
	public void testGetNodesBetween() {
	
		Integer[] e1 = {6,2,4,3};
		List<Integer> expected = Arrays.asList(e1);
		Assert.assertEquals(expected,tree.getNodesBetween(new Integer(8), new Integer(1)));
		
		Integer[] e2 = {6,4};
		List<Integer> expected1 = Arrays.asList(e2);
		Assert.assertEquals(expected1, tree.getNodesBetween(new Integer(3),new Integer(8)));
		
		Integer[] e3 = {2};
		List<Integer> expected3 = Arrays.asList(e3);
		Assert.assertEquals(expected3, tree.getNodesBetween(new Integer(1),new Integer(3)));
		
	}

}
