package testcase;

import org.junit.Test;

import com.basic.BinaryTreeNode;

public class TestBinaryTreeNode
{
	@Test
	public void testBinaryTree() {
		BinaryTreeNode binNode = new BinaryTreeNode(5);
		binNode.insert(1);
		binNode.insert(10);
		binNode.insert(4);
		binNode.insert(6);
		binNode.insert(2);
		binNode.insert(15);
		binNode.insert(8);

		// 1 2 4 5 6 8 10 15
		binNode.preOrderInterator();
	}

}
