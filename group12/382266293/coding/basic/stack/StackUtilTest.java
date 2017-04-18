package stack;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class StackUtilTest {

	Stack<Integer> s;
	@Before
	public void setUp() throws Exception {
		s = new Stack<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		s = null;
	}

	@Test
	public void testReverse() {
		for(int i = 5; i >= 1; i--) {
			s.push(i);
		}
		
		StackUtil.reverse(s);
		assertEquals(s.toString(),"[1, 2, 3, 4, 5]");

	}

	@Test
	public void testRemove() {
		for(int i = 5; i >= 1; i--) {
			s.push(i);
		}
		
		StackUtil.remove(s,2);
		assertEquals(s.toString(),"[5, 4, 3, 1]");
		
		StackUtil.remove(s,5);
		assertEquals(s.toString(),"[4, 3, 1]");
		
		StackUtil.remove(s,1);
		assertEquals(s.toString(),"[4, 3]");
		
		s = new Stack<Integer>();
		assertEquals(s.toString(),"[]");
	}

	@Test
	public void testGetTop() {
		for(int i = 5; i >= 1; i--) {
			s.push(i);
		}
		
		Object[] o = StackUtil.getTop(s,2);
		
		assertEquals(s.toString(),"[5, 4, 3, 2, 1]");
		assertEquals(Arrays.toString(o),"[1, 2]");
		
		o = StackUtil.getTop(s,6);
		assertEquals(Arrays.toString(o),"[1, 2, 3, 4, 5]");
		
		o = StackUtil.getTop(s,0);
		assertEquals(Arrays.toString(o),"[]");
	}

	@Test
	public void testIsValidPairs() {

		String s1 = "([e{d}f])";
		assertEquals(true,StackUtil.isValidPairs(s1));
		
		s1 = "([b{x]y})";
		assertEquals(false,StackUtil.isValidPairs(s1));

	}

}
