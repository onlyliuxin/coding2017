package week1.collectiontest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week1.collections.BinaryTreeNode;

public class BinaryTreeNodeTest {
	BinaryTreeNode BTN = null;
	@Before
	public void init(){
		BTN = new BinaryTreeNode();
	}
	
	@Test
	public void test1(){
		Object o = BTN.getData();
		Assert.assertEquals(null, o);
	}
	
	@Test
	public void test2(){
		Object o1 = BTN.insert(5).getData();
		Assert.assertEquals(o1, 5);
		Object o2 = BTN.insert(7).getData();
		Assert.assertEquals(o2, 7);
		Object o3 = BTN.insert(1).getData();
		Assert.assertEquals(o3, 1);
		Assert.assertEquals(5, BTN.getData());
		Assert.assertEquals(7, BTN.getRight().getData());
		Assert.assertEquals(1, BTN.getLeft().getData());
		BTN.insert(6).getData();
		Assert.assertEquals(6, BTN.getRight().getLeft().getData());
	}
}
