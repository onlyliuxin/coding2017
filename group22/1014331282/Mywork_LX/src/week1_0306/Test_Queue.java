package week1_0306;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_Queue {

	@Test
	public void test() {
		Queue queue=new Queue();
		ScoreRecord s1=new ScoreRecord(8,"Tom",98,99,88);
		ScoreRecord s2=new ScoreRecord(2,"Jack",90,99,80);
		ScoreRecord s3=new ScoreRecord(5,"Merry",93,90,90);
		ScoreRecord s4=new ScoreRecord(9,"Mike",88,95,80);
		ScoreRecord s5=new ScoreRecord(3,"Jerry",92,80,78);
		ScoreRecord s6=new ScoreRecord(7,"Jason",91,87,83);
		
		queue.enQueue(s1);
		queue.enQueue(s2);
		queue.enQueue(s3);
		queue.enQueue(s4);
		queue.enQueue(s5);
		
		System.out.println("一共有"+queue.size()+"个元素");
		
		Test_Queue t = new Test_Queue();
		t.printQueue((ScoreRecord)queue.deQueue());
		t.printQueue((ScoreRecord)queue.deQueue());
		
		queue.enQueue(s6);
			
		
		System.out.println("一共有"+queue.size()+"个元素");

	}
	
	public void printQueue(ScoreRecord s)
	{
		System.out.println("弹出的元素有："+((ScoreRecord) s).getId()+"<<<"+((ScoreRecord)s).getName());
	
	}


}
