package com.danny.hw1.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.danny.hw1.BinaryTreeNode;
import com.danny.hw1.LinkedList;

public class BinaryTreeNodeTest {

	static Object[] Data = new Object[]{5,10,3,2,15,12,56,8};
	BinaryTreeNode test;
	@Before
	public void setUp() throws Exception{
		test = new BinaryTreeNode();
		for(Object data: Data){
			test.insert(data);
			
		}
	}
	@Test
	public void testPrintWholeTree(){
		test.printWholeTree(test, 1);
		int layer = 1;
		int printNum = 0;
		System.out.println("var {5,10,3,2,15,12,56,8} insert tree\n"+"Print binary tree:");
		while(true){
			if(printNum == test.getTeMap().size()) break;
			for (Object key : test.getTeMap().keySet()) {
				Integer value = test.getTeMap().get(key);

				if (value.intValue() == layer){
					System.out.print(key.toString()+"   ");
					printNum++;
				}
				
			}
			System.out.println("");
			layer++;
		}
		
		
	}
	

}
