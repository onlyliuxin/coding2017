package test.com.coding.basic.tree;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.tree.BinarySearchTree;
import com.coding.basic.tree.BinaryTreeNode;

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
		
		List<Integer> datas = tree.levelVisit();
		
		Integer[] excpt = {6,2,8,1,4,3};
				
		Assert.assertArrayEquals(excpt, datas.toArray());		
	}
	
	@Test
	public void testIsValid(){
		
		Assert.assertEquals(true, tree.isValid());
		
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(1);
		root.left.right = new BinaryTreeNode<Integer>(4);
		root.left.right.left = new BinaryTreeNode<Integer>(5);
		tree = new BinarySearchTree<Integer>(root);
		
		Assert.assertEquals(false, tree.isValid());
		
	}
	
	@Test
	public void testGetLowestCommonAncestor(){
		
		Assert.assertEquals(6,tree.getLowestCommonAncestor(3, 8).intValue());
		
		Assert.assertEquals(2,tree.getLowestCommonAncestor(1, 3).intValue());
		
		Assert.assertEquals(2,tree.getLowestCommonAncestor(4, 3).intValue());

	}
	
	@Test
	public void testGetNodesBetween(){
		
        List<Integer> datas = tree.getNodesBetween(4, 8);		
		Integer[] excpt = {2,6};		
		Assert.assertArrayEquals(excpt, datas.toArray());
		
		List<Integer> datas1 = tree.getNodesBetween(1, 3);		
		Integer[] excpt1 = {2,4};		
		Assert.assertArrayEquals(excpt1, datas1.toArray());
	}
}
