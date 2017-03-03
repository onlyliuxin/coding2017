package test.com.coding.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Stack;

public class StackTest {

	Stack st ;
	
	@Before
    public void setup() {
		st = new Stack();
		for (int i = 0; i < 10; i++) {
			st.push(i);
		}		
    }

	@Test
	public void push(){
		
		Assert.assertEquals(st.size(), 10);
		st.push(10);
		st.push('a');
		Assert.assertEquals(st.size(), 12);
	}
	
	@Test//(expected = IndexOutOfBoundsException.class)
	public void pop(){
		
		Assert.assertEquals(st.size(), 10);
		for (int i = 9; i >= 0; i--) {
			Assert.assertEquals(st.pop(), i);
		}
		//打开下列语句抛出期望异常
		//st.pop();
	}
	
	@Test
	public void peek(){
		
		Assert.assertEquals(st.size(), 10);
		Assert.assertEquals(st.peek(), 9);
		Assert.assertEquals(st.size(), 10);
	}
	
	@Test
	public void isEmpty(){
		
		Assert.assertEquals(st.isEmpty(), false);
		for (int i = 0; i < 10; i++) {
			st.pop();
		}
		Assert.assertEquals(st.isEmpty(), true);
		Stack st1 = new Stack();
		Assert.assertEquals(st1.isEmpty(), true);
	}
	
    public void size(){
		
		Assert.assertEquals(st.size(),10);
		st.push("lk");
		st.push('h');
		Assert.assertEquals(st.size(),12);
		for (int i = 0; i < 12; i++) {
			st.pop();
		}
		Assert.assertEquals(st.size(),0);
		st.peek();
		Assert.assertEquals(st.size(),0);
		Stack st1 = new Stack();
		Assert.assertEquals(st1.size(), 0);
	}
}
