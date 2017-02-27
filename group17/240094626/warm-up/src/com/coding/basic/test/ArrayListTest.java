package com.coding.basic.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.coding.basic.impl.ArrayList;

/**
 * ArrayList 简单测试
 * @author 240094626
 *
 */
public class ArrayListTest {

	
	@Test
	public void test() {
		ArrayList list = new ArrayList();
		
		System.out.println("******测试add(Object o ):添加第一个元素0******");
		list.add(0);
		System.out.println("ArrayList print:"+list.toString());
		
		System.out.println("******测试add(int index,Object o):添加第二个元素1******");
		list.add(1, 1);
		System.out.println("ArrayList print:"+list.toString());
		
		System.out.println("******测试remove(int index):删除第1个元素:0******");
		list.remove(0);
		System.out.println("ArrayList print:"+list.toString());
		
		System.out.println("******测试add(int Object o):添加第三个元素2******");
		list.add(2);
		System.out.println("ArrayList print:"+list.toString());
		
		
		System.out.println("ArrayList size:"+list.size());
		
		System.out.println("******测试get(int index):判断第1个元素是否为1******");
		assertEquals(1, list.get(0));
	}
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(ArrayListTest.class);
		for(Failure failure : result.getFailures()){
			System.out.println(failure.toString());
		}
		System.out.println("test success!:"+result.wasSuccessful());
	}

}
