package com.coding.week1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBiranyTreeNode {

	@Test
	public void test() {
		BinaryTreeNode node = new BinaryTreeNode(5);
		node.insert(2);
		node.insert(7);
		node.insert(1);
		node.insert(6);
		System.out.println("      "+node.getData());
		System.out.println("   "+node.getLeft().getData()+"    "+node.getRight().getData());
		System.out.println(node.getLeft().getLeft().getData()+" null "+node.getRight().getLeft().getData()+" null");
	}

}
