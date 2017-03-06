package test_data_structure;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import data_structure.MyBinaryTree;


public class TestMyBinaryTree {
	MyBinaryTree bt;

	@Before
	public void setUp() throws Exception {
		System.out.println("开始测试");
		bt=new MyBinaryTree(); 
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("结束测试");
	}

	@Test
	public void testAdd() {
		bt.add(1, "c");
	
	}

	@Test
	public void testGet() {
		bt.add(1, 1);
		bt.add(2, 2);
		bt.add(3, 3);
		Assert.assertEquals(2, bt.get(2));
	}

	@Test
	public void testRemove() {
		bt.add(1, 1);
		bt.add(2, 2);
		bt.add(3, 3);
		Assert.assertEquals(2, bt.remove(2));
	
		Assert.assertEquals(2, bt.size());
	}

	@Test
	public void testSize() {
		bt.add(1, 1);
		bt.add(2, 2);
		bt.add(3, 3);
		Assert.assertEquals(3, bt.size());
	}

}
