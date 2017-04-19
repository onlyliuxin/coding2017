package cn.xl.basic.stack;

import org.junit.Test;

import junit.framework.Assert;

public class StackUtilTest {


	@Test
	public void reverse(){
		MyStack s = new MyStack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		s.push(7);
		s.push(8);
		s.push(9);
		s.push(10);
		s.push(11);
		/*MyStack my = StackUtil.reverse(s);
		while(!my.isEmpty()){
			System.out.println(my.pop());
		}*/
	}
	
	
	@Test
	public void getTop(){
		MyStack s = new MyStack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		s.push(7);
		s.push(8);
		s.push(9);
		s.push(10);
		s.push(11);
		
		Object[] o = StackUtil.getTop(s,5);
		
		Assert.assertEquals(5, o.length);
		
		/*while(!s.isEmpty()){
			System.out.println(s.pop());
		}*/
	}
	
	@Test
	public void remove(){
		
		MyStack s = new MyStack();
		s.push(1);
		s.push(2);
		s.push(4);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
	
		
		StackUtil.remove(s, 4);
		while(!s.isEmpty()){
			System.out.println(s.pop());
		}
		
	}
	
	
	@Test
	public void isValidPairs(){
		
		Assert.assertEquals(true, StackUtil.isValidPairs("([e[{{}}df]])"));
		
		
	}

}
