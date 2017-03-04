package com.coding.basic;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coding.basic.binaryTree.BinaryTreeNode;
import com.coding.basic.binaryTree.BinaryTreeOperation;
import com.coding.basic.binaryTree.BinaryTreeOperationImpl;

public class BinaryTreeNodeOperationImplTest {

	private BinaryTreeOperation<BinaryTreeNode> op = new BinaryTreeOperationImpl();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testBinaryTreeNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetData() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLeft() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLeft() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRight() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRight() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRoot() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRoot() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		BinaryTreeNode node = new BinaryTreeNode("100");
		op.insert(node);
		
		BinaryTreeNode node1 = new BinaryTreeNode("90");
		op.insert(node1);
		BinaryTreeNode node2 = new BinaryTreeNode("110");
		op.insert(node2);
		BinaryTreeNode node3 = new BinaryTreeNode("120");
		op.insert(node3);
		
		BinaryTreeNode node4 = new BinaryTreeNode("80");
		op.insert(node4);

		BinaryTreeNode node5 = new BinaryTreeNode("99");
		op.insert(node5);


		
		System.out.println("getLeft === "+node.getLeft());
		System.out.println("getRight === "+node.getRight());
	}

	@Test
	public void testCompareTo() {
		BinaryTreeNode node = new BinaryTreeNode("1");
		BinaryTreeNode node1 = new BinaryTreeNode("0");
		System.out.println(node.compareTo(node1));
	}

}
