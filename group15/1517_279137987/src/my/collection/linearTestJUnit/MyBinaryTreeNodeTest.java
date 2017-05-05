package my.collection.linearTestJUnit;

import static org.junit.Assert.*;
import junit.framework.Assert;
import my.collection.linear.MyBinaryTreeNode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyBinaryTreeNodeTest {

	MyBinaryTreeNode tree = new MyBinaryTreeNode();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		Assert.assertEquals(null, tree.getData());
		Assert.assertEquals(null, tree.getLeft());
		Assert.assertEquals(null, tree.getRight());
		
		tree.insert(new Integer(8));
		
		Assert.assertEquals(8, tree.getData());
		Assert.assertEquals(null, tree.getLeft());
		Assert.assertEquals(null, tree.getRight());
		
		tree.insert(new Integer(12));
		
		Assert.assertEquals(8, tree.getData());
		Assert.assertEquals(null, tree.getLeft());
		Assert.assertEquals("12", tree.getRight().getData().toString());
		
		tree.insert(new Integer(6));
		
		Assert.assertEquals(8, tree.getData());
		Assert.assertEquals("6", tree.getLeft().getData().toString());
		Assert.assertEquals("12", tree.getRight().getData().toString());
		
		tree.insert(new Integer(3));
		
		Assert.assertEquals(6, tree.getData());
		Assert.assertEquals("3", tree.getLeft().getData().toString());
		Assert.assertEquals(null, tree.getRight());
		
		tree.insert(new Integer(7));
		
		Assert.assertEquals(6, tree.getData());
		Assert.assertEquals("3", tree.getLeft().getData().toString());
		Assert.assertEquals("7", tree.getRight().getData().toString());
		
	}

}
