package tree;


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
		Assert.assertEquals(4, tree.height());
		
		{//height2()方法测试
			Assert.assertEquals(4, tree.height2());
		}
	}

	@Test
	public void testSize() {
		Assert.assertEquals(6, tree.size());
		
		{// size2()方法测试
			Assert.assertEquals(6, tree.size2());
		}
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
	public void testLevelVisit(){
		List<Integer> result = tree.levelVisit();
		Assert.assertEquals("[6, 2, 8, 1, 4, 3]", result.toString());
	}
	
	@Test
	public void testIsValid(){
		Assert.assertEquals(true, tree.isValid());
		
		tree.root.right.setLeft(new BinaryTreeNode<Integer>(5));
		Assert.assertEquals(false, tree.isValid());
	}
	
	@Test
	public void testGetAncestor(){
		Assert.assertEquals("[6, 2, 4, 3]", tree.getAncestor(3).toString());
	}
	
	@Test
	public void testGetLowestCommonAncestor(){
		int result = tree.getLowestCommonAncestor(1, 3);
		Assert.assertEquals(2, result);
	}
	
	@Test
	public void testGetNodesBetween(){
		List<Integer> result = tree.getNodesBetween(1, 7);
		Assert.assertEquals("[6, 2, 4, 3]", result.toString());
	}
}
