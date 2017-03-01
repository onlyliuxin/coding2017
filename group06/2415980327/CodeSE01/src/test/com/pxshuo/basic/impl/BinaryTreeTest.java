package test.com.pxshuo.basic.impl;

import org.junit.Assert;
import org.junit.Test;

import com.pxshuo.basic.TreeData;
import com.pxshuo.basic.impl.BinaryTree;

public class BinaryTreeTest {
	BinaryTree object = new BinaryTree();
	
	@Test
	public void binaryTest() {
		BinaryTree binaryTree = new BinaryTree();
		binaryTree.add(new TreeData(5));
		binaryTree.add(new TreeData(2));
		binaryTree.add(new TreeData(7));
		binaryTree.add(new TreeData(1));
		binaryTree.add(new TreeData(6));
		binaryTree.add(new TreeData(4));
		binaryTree.add(new TreeData(8));
		
		Assert.assertEquals("4", binaryTree.get(5).toString());
		Assert.assertEquals("8", binaryTree.get(7).toString());
	}
}
