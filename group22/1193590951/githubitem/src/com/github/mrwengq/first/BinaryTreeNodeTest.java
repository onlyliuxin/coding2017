package com.github.mrwengq.first;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeNodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		BinaryTreeNode btnt = new BinaryTreeNode();
		btnt.insert(15);
		btnt.insert(16);
		btnt.insert(23);
		btnt.insert(10);
		btnt.insert(18);
		btnt.insert(9);
		
		assertEquals(btnt.getData(), 15);
		assertEquals(btnt.getLeft().getData(),10);
		assertEquals(btnt.getRight().getData(),16);
		assertEquals(btnt.findNode(16).getRight().getData(),23);
		assertEquals(btnt.findNode(23).getLeft().getData(),18);
		assertEquals(btnt.findNode(10).getLeft().getData(),9);
	}

}
