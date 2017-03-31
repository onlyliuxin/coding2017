package week1_0306;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_Stack {

	@Test
	public void test() {
		Stack stack=new Stack();
		ScoreRecord s1=new ScoreRecord(8,"Tom",98,99,88);
		ScoreRecord s2=new ScoreRecord(2,"Jack",90,99,80);
		ScoreRecord s3=new ScoreRecord(5,"Merry",93,90,90);
		ScoreRecord s4=new ScoreRecord(9,"Mike",88,95,80);
		ScoreRecord s5=new ScoreRecord(3,"Jerry",92,80,78);
		ScoreRecord s6=new ScoreRecord(7,"Jason",91,87,83);
		
		stack.push(s1);
		stack.push(s2);
		stack.push(s3);
		stack.push(s4);
		stack.push(s5);
		
		System.out.println("一共有"+stack.size()+"个元素");
		
		Test_Stack t = new Test_Stack();
		t.printStack((ScoreRecord)stack.pop());
		t.printStack((ScoreRecord)stack.pop());
		
		stack.push(s6);
		ScoreRecord g = (ScoreRecord)stack.peek();
		System.out.println(g.getId()+"<<<"+g.getName());	
		
		System.out.println("一共有"+stack.size()+"个元素");

				
		
	}
	
	public void printStack(ScoreRecord s)
	{
		System.out.println("弹出的元素有："+((ScoreRecord) s).getId()+"<<<"+((ScoreRecord)s).getName());
	
	}


}
