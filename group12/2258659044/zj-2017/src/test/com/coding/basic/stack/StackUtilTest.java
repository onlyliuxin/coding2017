package test.com.coding.basic.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.stack.Stack;
import com.coding.basic.stack.StackUtil;

public class StackUtilTest {
	
	Stack<Object> s ;
    
    @Before
    public void setup() {
    	//初始化栈元素：5,4,3,2,1
		s = new Stack<>();
		for (int i = 1; i <= 5; i++) {
			s.push(i);
		}
    }
    	
	@Test
	public void testReverse() {
		
		StackUtil.reverse(s);
		Assert.assertEquals(s.size(), 5);		
		Assert.assertEquals("[1,2,3,4,5]", s.toString());
	}

	@Test
	public void testRemove() {
		
		StackUtil.remove(s, 5);
		Assert.assertEquals("[4,3,2,1]", s.toString());
		
		StackUtil.remove(s, 1);
		Assert.assertEquals("[4,3,2]", s.toString());
		
		StackUtil.remove(s, 4);
		Assert.assertEquals("[3,2]", s.toString());
	}

	@Test
	public void testGetTop() {
		
		Object[] obj = StackUtil.getTop(s, 6);
		Assert.assertEquals(5, obj.length);
		Assert.assertEquals(s.toString(),arrayToString(obj));
		
		Object[] obj1 = StackUtil.getTop(s, 2);
		Assert.assertEquals(2, obj1.length);		
		Assert.assertEquals("[5,4]",arrayToString(obj1));
		
	}

	@Test
	public void testIsValidPairs() {
		
		String s0 = "([e{d}f])";				
		Assert.assertEquals(true,StackUtil.isValidPairs(s0));
		
		String s1 = "(]e{d}f[)";				
		Assert.assertEquals(false,StackUtil.isValidPairs(s1));
		
		String s2 = "([b{x]y})";
		Assert.assertEquals(false,StackUtil.isValidPairs(s2));
		
		String s3 = "()((()()))()";
		Assert.assertEquals(true,StackUtil.isValidPairs(s3));
		
		String s4 = "(fs{fs[fs(";
		Assert.assertEquals(false,StackUtil.isValidPairs(s4));
		
		String s5 = "gshsg54fs";
		Assert.assertEquals(false,StackUtil.isValidPairs(s5));
		
		
	}
	
	private static String arrayToString(Object[] objs){
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("[");
		
		for (int i = 0; i < objs.length; i++) {
			if(i == objs.length-1){
				sBuffer.append(objs[i]+"]");
			}else{
				sBuffer.append(objs[i]+",");
			}			
		}
		return sBuffer.toString();
	}
}
