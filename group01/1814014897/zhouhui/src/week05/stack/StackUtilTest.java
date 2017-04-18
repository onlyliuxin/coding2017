package week05.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StackUtilTest {

	Stack s = new Stack();

	@Before
	public void setUp() throws Exception {
		for(int i=1;i<=5;i++){
			s.push(i);
		}
	}
	
	@Test
	public void testReverse() {
		StackUtil.reverse(s);
		
		for(int i=1;i<=5;i++){
			Assert.assertEquals(i,s.pop());
		}
	}

	@Test
	public void testRemove() {
		StackUtil.remove(s, 3);
		
		Assert.assertEquals(4,s.size());
		
		Assert.assertEquals(5,s.pop());
		Assert.assertEquals(4,s.pop());
		Assert.assertEquals(2,s.pop());
		Assert.assertEquals(1,s.pop());
		
		Assert.assertEquals(0,s.size());
	}

	@Test
	public void testGetTop() {
		Object[] result = StackUtil.getTop(s, 3);
		
		Assert.assertArrayEquals(new Object[]{5,4,3},result);
		
		for(int i=5;i>=1;i--){
			Assert.assertEquals(i, s.pop());
		}
	}

	@Test
	public void testIsValidPairs() {
		String s1 = "([e{d}f])";
		String s2 = "([b{x]y})";
		
		Assert.assertTrue(StackUtil.isValidPairs(s1));
		Assert.assertFalse(StackUtil.isValidPairs(s2));
	}

}
