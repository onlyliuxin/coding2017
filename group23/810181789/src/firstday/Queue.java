package firstday;
import firstday.LinkListt;
public class Queue {
    private LinkListt l=new LinkListt();
    public Object deQueue()
    {
    	return l.removeFirst();
    }
    public void enQueue(Object o)
    {
    	l.add(o);
    }
    public boolean isEmpty()
    {
    	if(l.head==null)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    public int size()
    {
    	return l.size();
    }
    public static void main(String[] args)
    {
    	Queue q=new Queue();
    	for(int i=0;i<4;i++)
    	{
    		q.enQueue(i);
    	}
    	System.out.println(q.size());
    	for(int i=0;i<4;i++)
    	{
    		System.out.println(q.deQueue());
    	}
    	System.out.println(q.size());
    	//System.out.println(q.isEmpty());
    }
}
