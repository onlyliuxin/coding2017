package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.BinaryTreeNode;

public class BinaryTreeNodeTest {

	@Test
	public void testInsert() {
		BinaryTreeNode node = new BinaryTreeNode(5, null,null);
		node.insert(2);
		node.insert(7);
		node.insert(7);
		node.insert(1);
		node.insert(6);
		node.insert(4);
		node.insert(8);
		assertEquals(8, node.getRight().getRight().getData());
		assertEquals(4, node.getLeft().getRight().getData());
	}

}
