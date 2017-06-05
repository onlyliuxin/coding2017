package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.BinaryTree.BinaryTreeNode;

public class BinaryTreeTest {

	@Test
	public void testBinaryTree() {
		BinaryTree binaryTree=new BinaryTree();
	}

	@Test
	public void testInsert() {
		BinaryTree<Integer> binaryTree=new BinaryTree<Integer>();
		BinaryTree.BinaryTreeNode<Integer> newNode=new BinaryTreeNode<Integer>();
		newNode.setData(80);
		binaryTree.Insert(newNode);
		BinaryTree.BinaryTreeNode<Integer> newNode1=new BinaryTreeNode<Integer>();
		newNode1.setData(100);
		binaryTree.Insert(newNode1);
		BinaryTree.BinaryTreeNode<Integer> newNode2=new BinaryTreeNode<Integer>();
		newNode2.setData(50);
		binaryTree.Insert(newNode2);
		BinaryTree.BinaryTreeNode<Integer> newNode3=new BinaryTreeNode<Integer>();
		newNode3.setData(12);
		binaryTree.Insert(newNode3);
		BinaryTree.BinaryTreeNode<Integer> newNode4=new BinaryTreeNode<Integer>();
		newNode4.setData(25);
		binaryTree.Insert(newNode4);
		System.out.print("œ»–Ú±È¿˙À≥–Ú£∫");
		binaryTree.preOrder(binaryTree.getRoot());
		System.out.println();
		System.out.print("÷––Ú±È¿˙À≥–Ú£∫");
		binaryTree.inOrder(binaryTree.getRoot());
		System.out.println();
		System.out.print("∫Û–Ú±È¿˙À≥–Ú£∫");
		binaryTree.postOrder(binaryTree.getRoot());
	}

}
