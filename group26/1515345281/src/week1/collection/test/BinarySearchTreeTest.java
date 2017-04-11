package week1.collection.test;

import static org.junit.Assert.*;

import org.junit.Test;

import week1.collection.BinaryTreeNode;

public class BinarySearchTreeTest {

	private BinaryTreeNode root=new BinaryTreeNode(5);
	
	@Test
	public void testInsert(){
		root.insert(2);
		root.insert(2);
		root.insert(7);
		root.insert(1);
		root.insert(4);
		root.insert(3);
		assertEquals(3,root.getLeft().getRight().getLeft().getData());
	}
}
