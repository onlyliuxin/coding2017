package week01.BasicDataStructureTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.BasicDataStructure.BinaryTreeNode;


public class BinaryTreeNodeTest {
	
	private BinaryTreeNode root = new BinaryTreeNode(5);

	@Before
	public void setUp() throws Exception {
		root.insert(2);
		root.insert(7);
		root.insert(1);
		root.insert(6);
	}

	@Test
	public void testGetData() {
		Assert.assertEquals(root.getData(), 5);
		Assert.assertEquals(root.getLeft().getData(), 2);
		Assert.assertEquals(root.getRight().getData(), 7);
		Assert.assertEquals(root.getLeft().getLeft().getData(), 1);
		Assert.assertEquals(root.getRight().getLeft().getData(), 6);
	}

	@Test
	public void testSetData() {
		root.setData(8);
		Assert.assertEquals(root.getData(),8);
		root.getLeft().setData(88);
		Assert.assertEquals(root.getLeft().getData(),88);
		root.getRight().setData(888);
		Assert.assertEquals(root.getRight().getData(),888);
	}

	@Test
	public void testGetLeft() {
		BinaryTreeNode node_left = root.getLeft();
		Assert.assertEquals(node_left.getData(), 2);
		BinaryTreeNode node_left_left = root.getLeft().getLeft();
		Assert.assertEquals(node_left_left.getData(), 1);
	}

	@Test
	public void testSetLeft() {
		BinaryTreeNode node = new BinaryTreeNode(100);
		root.setLeft(node);
		Assert.assertEquals(root.getLeft().getData(), 100);
	}

	@Test
	public void testGetRight() {
		BinaryTreeNode node_right = root.getRight();
		Assert.assertEquals(node_right.getData(), 7);
		root.insert(8);
		BinaryTreeNode node_right_right = root.getRight().getRight();
		Assert.assertEquals(node_right_right.getData(), 8);
	}

	@Test
	public void testSetRight() {
		BinaryTreeNode node = new BinaryTreeNode(100);
		root.setRight(node);
		Assert.assertEquals(root.getRight().getData(), 100);
	}

	@Test
	public void testInsert() {
		root.insert(4);
		root.insert(8);
		Assert.assertEquals(root.getLeft().getRight().getData(), 4);
		Assert.assertEquals(root.getRight().getRight().getData(), 8);
	}

}
