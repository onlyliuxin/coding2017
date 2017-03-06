package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.BinaryTreeNode;

public class BinaryTreeNodeTest {
	private BinaryTreeNode btn;
	@Before
	public void setUp() throws Exception {
		btn=new BinaryTreeNode();
		
	}

	@Test
	public void test() {
		btn.insert(3);
		btn.insert(5);
		btn.insert(2);
		btn.insert(10);
		btn.insert(4);
		
	}

}
