package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
	}

	@Test
	public void testContains() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		System.out.println(bst.contains(8));
	}
	
	@Test
	public void testPreOrder(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		bst.preOrder(bst.getRoot());
	}
	
	@Test
	public void testMidOrder(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		bst.midOrder(bst.getRoot());
	}
	
	@Test
	public void testPostOrder(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		bst.postOrder(bst.getRoot());
	}

}
