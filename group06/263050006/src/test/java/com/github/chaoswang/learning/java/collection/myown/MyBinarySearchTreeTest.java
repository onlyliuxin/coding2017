package com.github.chaoswang.learning.java.collection.myown;

import org.junit.Test;

public class MyBinarySearchTreeTest {
	@Test
	public void testInsert(){
		MyBinarySearchTree tree = new MyBinarySearchTree(12);
		tree.insert(5);
		tree.insert(18);
		tree.insert(2);
		tree.insert(9);
		tree.insert(15);
		tree.insert(19);
		System.out.println(tree);
	}
}
