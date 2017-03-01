package com.nitasty.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.nitasty.util.BinaryTree;

public class BinaryTreeTest {
	
	BinaryTree<Integer> tree;
	
	@Before
	public void init(){
		tree=new BinaryTree<Integer>();
		tree.insert(5);
		tree.insert(3);
		tree.insert(8);
		tree.insert(2);
		tree.insert(7);
		tree.insert(9);
		tree.insert(1);
		tree.insert(4);
		tree.insert(10);
		tree.insert(6);
	}

	@Test
	public void testMakeEmpty() {
		tree.makeEmpty();
		Assert.assertEquals(true, tree.isEmpty());
	}

	@Test
	public void testGetHeight() {
		Assert.assertEquals(3, tree.getHeight());
	}

	@Test
	public void testContains() {
		for (int i = 1; i < 11; i++) {
			Assert.assertEquals(true, tree.contains(i));
		}
	}

	@Test
	public void testFindMin() {
		Assert.assertEquals(1, tree.findMin());
	}

	@Test
	public void testFindMax() {
		Assert.assertEquals(10, tree.findMax());
	}


	@Test
	public void testRemove() {
		tree.remove(3);
		Assert.assertEquals(false, tree.contains(3));
	}
	
	
}
