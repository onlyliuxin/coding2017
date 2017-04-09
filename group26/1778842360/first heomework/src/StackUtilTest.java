package first;

import org.junit.Assert;
import org.junit.Test;


public class StackUtilTest {
   
	@Test
	public void testReverse() {
		Stack s=new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		StackUtil.reverse(s);
		String actual=StackUtil.toString(s);
		String expected="[1,2,3,4,5]";
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRemove() {
		Stack s=new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		StackUtil.remove(s, 3);
		String actual=StackUtil.toString(s);
		String expected="[5,4,2,1]";
		Assert.assertEquals(expected, actual);		
	}

	@Test
	public void testGetTop() {
		Stack s=new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		Object[] actual=StackUtil.getTop(s, 3);
		Object[] expected={5,4,3};
		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void testIsValidPairs() {
		String s="jdslafds[({})]()";
		 boolean actual=StackUtil.isValidPairs(s);
		 Assert.assertEquals(true, actual);
		
	}

}
