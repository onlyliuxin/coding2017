package week1_0306;

public class Queue {
	
	private LinkedList queue = new LinkedList();
	
	public void enQueue(Object o)//进队列
	{
		queue.addFirst(o);
	}
	
	public Object deQueue()//出队列
	{
		return queue.removeLast();
	}
	
	public boolean isEmpty()
	{
		if(queue.get(0)==null)
			return true;
		else return false;
	}
	
	public int size()
	{
		return queue.size();
	}
	
	public Queue() {
		// TODO Auto-generated constructor stub
	}
	


}
