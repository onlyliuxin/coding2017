package com.test;


import org.junit.Test;

import com.sx.structures.BinaryTree;

public class BinaryTreeTest {
	
	private BinaryTree bt;

	@Test
	public void test() {
		bt  = new BinaryTree(55);
		bt.insert(23);
		bt.insert(44);
		bt.insert(16);
		bt.insert(78);
		bt.insert(99);
		//先序
		System.out.println("先序遍历：");
		bt.preOrder(bt.getRoot());
		//中序遍历
		System.out.println("\n中序遍历：");
		bt.inOrder(bt.getRoot());
		//后序
		System.out.println("\n后序遍历：");
		bt.postOrder(bt.getRoot());
	}

}
