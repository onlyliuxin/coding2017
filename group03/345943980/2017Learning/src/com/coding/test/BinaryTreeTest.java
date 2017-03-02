package com.coding.test;

import org.junit.Test;

import com.coding.basic.BinaryTree;
import com.coding.basic.LinkedList;

public class BinaryTreeTest {

	@Test
	public void test01(){
		BinaryTree binaryTree = new BinaryTree();
		binaryTree.insert(5);
		binaryTree.insert(2);
		binaryTree.insert(7);
		binaryTree.insert(1);
		binaryTree.insert(4);
		binaryTree.insert(6);
        binaryTree.insert(8);
        LinkedList linkedList=binaryTree.inOrder();
        for(int i=0;i<linkedList.size();i++){
        	System.out.println(linkedList.get(i));        	
        }
	}
}
