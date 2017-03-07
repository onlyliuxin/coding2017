package com.xusheng.tree;

public class TestMain {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(8);
		bst.insert(3);
		bst.insert(7);
		bst.insert(1);
		bst.insert(10);
		System.out.println(bst.contains(3));
		bst.remove(4);
		System.out.println(bst.contains(3));
//		BinarySearchTree<String> bst = new BinarySearchTree<String>();
//		bst.insert("haha1");
//		bst.insert("haha2");
//		bst.insert("haha3");
//		System.out.println(bst.contains("haha3"));
	}
}
