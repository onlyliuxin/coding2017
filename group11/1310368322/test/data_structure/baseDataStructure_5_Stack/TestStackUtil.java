package DataStructure_5_Stack;

import java.util.Stack;

import org.junit.Assert;

import org.junit.Test;

public class TestStackUtil {

	@Test
	public void test_reverse_1() {
		Stack s = new Stack();
		for(int i = 0; i < 5; i++){
			s.push(i+1);
		}
		System.out.println(s.toString());
		StackUtil.reverse(s);
		System.out.println(s.toString());
	}
	
	@Test
	public void test_reverse_2() {
		Stack s = new Stack();
		System.out.println(s.isEmpty());
		System.out.println(s.toString());
		StackUtil.reverse(s);
		System.out.println(s.toString());
	}
	
	@Test
	public void testRemove_1(){
		Stack actual = new Stack();
		Stack expected = new Stack();
		for(int i = 0; i < 5; i++){
			actual.push(i+1);
			if(i != 2){  expected.push(i+1);  }
		}
		StackUtil.remove(actual, 3);
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testRemove_2(){
		Stack actual = new Stack();
		Stack expected = new Stack();
		StackUtil.remove(actual, 3);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testGetTop(){
		Stack s = new Stack();
		for(int i = 0; i < 5; i++){
			s.push(i+1);
		}
		Object expected[] = {5,4,3};
		Object[] actual = StackUtil.getTop(s, 3);
		Assert.assertArrayEquals(expected, actual);
		
	}
	
	
	@Test
	public void testIsValidPairs_1(){
		boolean actual = StackUtil.isValidPairs("([e{d}f])");
		boolean expected = true;
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testValidPairs_2(){
		boolean actual = StackUtil.isValidPairs("([b{x]})");
		boolean expected = false;
		Assert.assertEquals(expected, actual);
	}

}
