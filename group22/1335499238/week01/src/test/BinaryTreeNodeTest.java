package test;

import org.junit.Test;

import basic.BinaryTreeNode;
import basic.Iterator;
import basic.LinkedList;

public class BinaryTreeNodeTest {
	
	@Test
	public void test01(){
		BinaryTreeNode<Integer> binaryTreeNode = new BinaryTreeNode<Integer>(20);
		binaryTreeNode.insert(5);
		binaryTreeNode.insert(40);
		binaryTreeNode.insert(30);
		binaryTreeNode.insert(10);
		binaryTreeNode.insert(15);
		LinkedList prevOrder = binaryTreeNode.prevOrder(binaryTreeNode);
		Iterator iterator = prevOrder.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next()+"   ");
		}
	}

}
