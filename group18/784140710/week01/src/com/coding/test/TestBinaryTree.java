package com.coding.test;

import com.coding.basic.BinaryTree;
import com.coding.basic.BinaryTreeNode;

public class TestBinaryTree {

	public static void main(String[] args) {
		
		BinaryTree tree = new BinaryTree();
		tree.insert(87);
		tree.insert(17);
		tree.insert(88);
		tree.insert(36);
		BinaryTreeNode i = tree.find(17);
		BinaryTreeNode i1 = tree.find(12);
		System.out.println();
	}
	
}
