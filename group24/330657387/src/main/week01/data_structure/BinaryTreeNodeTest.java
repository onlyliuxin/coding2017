package main.week01.data_structure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeNodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsert() {
		BinaryTreeNode head=new BinaryTreeNode();
		
		head.setData(5);
		head.insert(2);
		head.insert(7);
		head.insert(1);
		head.insert(4);
		head.insert(3);
		assertEquals(3,head.getLeft().getRight().getLeft().getData());
	}

}
