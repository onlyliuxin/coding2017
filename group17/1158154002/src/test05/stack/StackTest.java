package test05.stack;

import java.util.Arrays;

import org.junit.Test;

public class StackTest {
	@Test
	public void testReverse(){
		Stack<Integer> stack=new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		
		StackUtil.reverse(stack);
		System.out.println(stack.toString());
	}
	
	@Test
	public void testRemove(){
		Stack stack=new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		
		StackUtil.remove(stack,3);
		System.out.println(stack.toString());
	}
	//isValidPairs
	@Test
	public void testGetTop(){
		Stack stack=new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);

		System.out.println(Arrays.toString(StackUtil.getTop(stack,6)));
		System.out.println(stack.toString());
	}
	
	@Test
	public void testIsValidPairs(){
		System.out.println(StackUtil.isValidPairs("([e{d}f])"));
		System.out.println(StackUtil.isValidPairs("([b{x]y})"));
	}
}
