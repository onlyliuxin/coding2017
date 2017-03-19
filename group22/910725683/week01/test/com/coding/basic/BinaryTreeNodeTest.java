package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.BinaryTreeNode;

public class BinaryTreeNodeTest {
	
	BinaryTreeNode btn = new BinaryTreeNode();
	
	@Test
	public void testPreOrder() {
		testInsert();
		System.out.print("preOrde : ");
		btn.preOrder(btn);
		System.out.println("");
	}

	@Test
	public void testInOrder() {
		testInsert();
		System.out.print("inOrder : ");
		btn.inOrder(btn);
		System.out.println("");
	}

	@Test
	public void testPostOrder() {
		testInsert();
		System.out.print("postOrder : ");
		btn.postOrder(btn);
		System.out.println("");
	}

	@Test
	public void testInsert() {
		btn.insert(45);
		btn.insert(24);
		btn.insert(53);
		btn.insert(12);
		btn.insert(37);
		btn.insert(93);
	}

}
