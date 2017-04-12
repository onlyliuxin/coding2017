package test.com.coding.basic.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.stack.Stack;
import com.coding.basic.stack.StackUtil;

public class TestStackUtil {
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testReverse(){
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		StackUtil.reverse(s);
		Assert.assertEquals("5,4,3,2,1", s.toString());
	}
	
	@Test
	public void testRemoveObj(){
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		StackUtil.remove(s, 3);
		Assert.assertEquals("1,2,4,5", s.toString());
		StackUtil.remove(s, 6);
		Assert.assertEquals("1,2,4,5", s.toString());
	}
	
	@Test
	public void testGetTop(){
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		Object[] objs = StackUtil.getTop(s, 2);
		Assert.assertEquals("1,2,3,4,5", s.toString());
		Assert.assertEquals(2, objs.length);
		Assert.assertEquals(5, objs[0]);
		Assert.assertEquals(4, objs[1]);
 	}
	
	@Test
	public void testIsValidPairs(){
		Assert.assertEquals(true, StackUtil.isValidPairs("([e{d}{f}])"));
		Assert.assertEquals(false, StackUtil.isValidPairs("([b{x[]y})"));
	}
}
