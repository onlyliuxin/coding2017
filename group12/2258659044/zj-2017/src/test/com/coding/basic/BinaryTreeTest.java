package test.com.coding.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.BinaryTree;
import com.coding.basic.BinaryTreeNode;
import com.coding.basic.List;

public class BinaryTreeTest {

	BinaryTree tree ;
	
	@Before
    public void setup() {
		
		tree = new BinaryTree();
		Assert.assertEquals(tree.getRoot(), null);
		tree.insert(5);
		tree.insert(2);
		tree.insert(7);
		tree.insert(1);
		tree.insert(6);
    }
	@Test
	public void insert(){
		
		BinaryTreeNode  node = tree.insert(4);
		Assert.assertEquals(node.getParent().getData(), 2);
		Assert.assertEquals(node.getParent().getLeft().getData(), 1);
		
		BinaryTreeNode node2 = tree.insert(8);
		Assert.assertEquals(node2.getParent().getData(), 7);
		Assert.assertEquals(node2.getParent().getLeft().getData(), 6);
	}
	
	@Test
	public void traversal(){
		
		insert();
		//以根节点为起点先序遍历
		List  treeList = tree.traversalBefore(tree.getRoot());
		//expected value
		int[] exValue = {5,2,1,4,7,6,8};
		for (int i = 0; i < exValue.length; i++) {
			Assert.assertEquals(treeList.get(i),exValue[i]);
		}
		
		//以数据2位起点先序遍历
		List  treeList2 = tree.traversalBefore(tree.getRoot().getLeft());
		//expected value
		int[] exValue2 = {2,1,4};
		for (int i = 0; i < exValue2.length; i++) {
			Assert.assertEquals(treeList2.get(i),exValue2[i]);
		}
	}
}
