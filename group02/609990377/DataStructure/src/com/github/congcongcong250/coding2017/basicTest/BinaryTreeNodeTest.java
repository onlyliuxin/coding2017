package com.github.congcongcong250.coding2017.basicTest;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.congcongcong250.coding2017.basic.BinaryTreeNode;

public class BinaryTreeNodeTest implements testCase {

	BinaryTreeNode node = new BinaryTreeNode();
	
	@Override
	@Before
	public void setUp() {
		node.insert(10);

	}

	@Override
	@After
	public void tearDown() {
		node.destroy();
	}

	@Override
	@Test
	public void testAdd() {
		assertEquals(10,node.getData());
		node.insert(5);
		assertEquals(5,node.getLeft().getData());
		node.insert(1);
		node.insert(2);
		node.insert(6);
		node.insert(19);
		node.insert(18);
		/*
		 *                  10
		 *          5                19
		 *    1          6      18  
		 *        2
		 * 
		 * */
		assertEquals(1,node.getLeft().getLeft().getData());
		assertEquals(2,node.getLeft().getLeft().getRight().getData());
		assertEquals(6,node.getLeft().getRight().getData());
		assertEquals(19,node.getRight().getData());
		assertEquals(18,node.getRight().getLeft().getData());
	}

	@Override
	@Test
	public void testRemove() {
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testFunctional() {
		// TODO Auto-generated method stub

	}

}
