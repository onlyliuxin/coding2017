package org.coding.one;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BinaryTreeNodeTest {
	
	private BinaryTreeNode target;

	@Before
	public void setUp() throws Exception {
		target = new BinaryTreeNode(100, null, null);
	}

	@After
	public void tearDown() throws Exception {
		target = null;
	}

	@Test
	public void testInsert() {
		target.insert(70);
		target.insert(60);
		target.insert(80);
		
		target.insert(120);
		target.insert(110);
		target.insert(130);
		
		BinaryTreeNode left = target.getLeft();
		Assert.assertEquals(70, left.getData());
		Assert.assertEquals(60, left.getLeft().getData());
		Assert.assertEquals(80, left.getRight().getData());
		
		BinaryTreeNode right = target.getRight();
		Assert.assertEquals(120, right.getData());
		Assert.assertEquals(110, right.getLeft().getData());
		Assert.assertEquals(130, right.getRight().getData());
		
	}

}
