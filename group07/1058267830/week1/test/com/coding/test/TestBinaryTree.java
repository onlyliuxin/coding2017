package com.coding.test;

import org.junit.Test;

import com.coding.basic.BinaryTree;

public class TestBinaryTree {

	@Test
	public void test() {
		BinaryTree bt = new BinaryTree();
		bt.insert(10);
		bt.insert(5);
		bt.insert(6);
		bt.insert(20);
		bt.insert(15);
		bt.insert(8);
		bt.middlePrint();
		System.out.println();
		
		System.out.println("----------------");
		bt.prePrint();
		
		System.out.println();
		System.out.println("-----------------");
		bt.postPrint();
	}

}
