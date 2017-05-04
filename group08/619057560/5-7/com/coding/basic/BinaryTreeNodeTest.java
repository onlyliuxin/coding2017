package com.coding.basic;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeNodeTest {

	BinaryTreeNode<Integer> root;
	
	@Before
	public void setUp() throws Exception {
		root = new BinaryTreeNode<>();
		root.insert(4);
		root.insert(2);
		root.insert(3);
		root.insert(5);
		root.insert(6);
		root.insert(1);
		root.insert(7);
		root.insert(5);
		root.insert(8);
		System.out.println("original tree:");
		System.out.println(root.sortedList(null));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRemove() {
		Assert.assertEquals(Arrays.asList(new Integer[]{1,2,3,4,5,5,6,7,8}), root.sortedList(null));
		root.remove(4);
		root.remove(1);
		System.out.println("modified tree:");
		System.out.println(root.sortedList(null));
		Assert.assertEquals(Arrays.asList(new Integer[]{2,3,5,5,6,7,8}), root.sortedList(null));
	}

	@Test
	public void testFindMin() {
		Assert.assertEquals(1, root.findMinNode().getData().intValue());
	}
}
