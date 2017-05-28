package stack;

import queue.Queue;

public class StackWithTwoQueues <E> {
	
    private Queue<E> queue1;
    private Queue<E> queue2;
    public StackWithTwoQueues()
    {
    	queue1=new Queue<E>();
    	queue2=new Queue<E>();
    }
    public boolean isEmpty()
    {
    	
    	return queue1.isEmpty()&&queue2.isEmpty();
    }
    public int size()
    {
    	return queue1.size()+queue2.size();
    }
    
    public void push(E data) 
    {       
       queue1.enQueue(data);
    }

    public E pop() {
    	if(this.isEmpty())
    	{
    		throw new RuntimeException("栈为空");
    	}
    	if(!queue1.isEmpty())
    	 {
    	   if(queue1.size()==1)
        	 {
        	   return queue1.deQueue();
        	 }else{
        		 //System.out.println(queue1.size());
        	     moveElementFormQueue1ToQueue2();
                 return queue1.deQueue();
        	 }  	
    	 }
    	else{
    		moveElementFromQueue2ToQueue1();
    		return queue2.deQueue();
    	}
      }
    //将队列2中的元素移到队列1中
	private void moveElementFromQueue2ToQueue1() {
		int size=queue2.size();
		for(int i=0;i<size-1;i++)
		{
			queue1.enQueue(queue2.deQueue());
		}
		
	}
	//将队列1中的元素移到队列2中
	private void moveElementFormQueue1ToQueue2() {
		// TODO Auto-generated method stub
		int size=queue1.size();
		for(int i=0;i<size-1;i++)
		{
			queue2.enQueue(queue1.deQueue());
		}
	}

    
}
