package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.coding.basic.BinaryTreeNode;

public class BinaryTreeNodeTest {

	@Test
	public void test() {
		BinaryTreeNode rootNode = new BinaryTreeNode();
		rootNode.setData(6); 
		rootNode.insert(5);
		rootNode.insert(9);
		rootNode.insert(7);
		
		Assert.assertEquals("Shoule be the same", 5, rootNode.getLeft().getData());
		Assert.assertEquals("Shoule be the same", 9, rootNode.getRight().getData());
		Assert.assertEquals("Shoule be the same", 7, rootNode.getRight().getLeft().getData());
	}

}
