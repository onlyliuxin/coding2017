package com.github.qq809203042.coding2017.basic.structurestest;

import com.github.qq809203042.coding2017.basic.structures.MyBinaryTree;

public class MyBinaryTreeTest {

	public static void main(String[] args) {
		MyBinaryTree tree = new MyBinaryTree();
		tree.add(5);
		tree.add(2);
		tree.add(7);
		tree.add(1);
		tree.add(6);
		tree.add(4);
		tree.add(8);
		
		System.out.println(tree.size());
		tree.preOrder();
		tree.midOrder();
		tree.aftOrder();
	}

}
