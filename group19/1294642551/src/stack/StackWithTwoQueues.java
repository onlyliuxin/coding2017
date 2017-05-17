package stack;


import queue.Queue;

/**
 * 用两个队列来实现栈
 * @author 12946
 *
 */
public class StackWithTwoQueues<E> {

	private Queue<E> queue1;// 用于临时存放数据，当新的元素需要入栈时，保存原来在queue2中的元素
	private Queue<E> queue2;// 用于倒序存放元素，排在出队第一位的是最后进来的元素
	
	public StackWithTwoQueues(){
		queue1 = new Queue<E>();
		queue2 = new Queue<E>();
	}

    public void push(E data) {       
    	if(queue2.isEmpty()){
    		queue2.enQueue(data);
    		return;
    	}
    	while(!queue2.isEmpty()){
    		queue1.enQueue(queue2.deQueue());
    	}
    	queue2.enQueue(data);
    	while(!queue1.isEmpty()){
    		queue2.enQueue(queue1.deQueue());
    	}
    }

    public E pop() {
    	if(queue2.isEmpty()){
    		throw new IndexOutOfBoundsException("栈为空");
    	}
    	return queue2.deQueue();
    }
    
    public int size(){
    	return queue2.size();
    }
    
    public boolean isEmpty(){
    	return queue2.isEmpty();
    }

    
}
