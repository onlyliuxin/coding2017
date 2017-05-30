package week09.basic;

import java.util.EmptyStackException;

/**
 * 用两个队列实现栈
 * 
 * @author gallenzhang
 *
 */
public class StackWithTwoQueues {
	private Queue<Integer> firstQueue = new Queue<>();
	private Queue<Integer> secondQueue = new Queue<>();
	
	public void push(int data) {
		
		if(!firstQueue.isEmpty()){
			firstQueue.enQueue(data);
			return ;
		}
		
		if(!secondQueue.isEmpty()){
			secondQueue.enQueue(data);
			return ;
		}

		firstQueue.enQueue(data);
	}

	public int pop() {
		if(firstQueue.isEmpty() && secondQueue.isEmpty()){
			throw new EmptyStackException();
		}
		
		int item = -1;
		int queueSize = 0;
		
		if(!firstQueue.isEmpty()){
			queueSize = firstQueue.size();
			for(int i = 0 ; i < queueSize; i++){
				if(i == queueSize - 1){
					item = firstQueue.deQueue();
					return item;
				}
				secondQueue.enQueue(firstQueue.deQueue());
			}
			
		}
		
		if(!secondQueue.isEmpty()){
			queueSize = secondQueue.size();
			for(int i = 0; i < queueSize; i++){
				if(i == queueSize -1){
					item = secondQueue.deQueue();
					return item;
				}
				firstQueue.enQueue(secondQueue.deQueue());
			}
		}
		return item;
	}
	
	public int size(){
		return Math.max(firstQueue.size(), secondQueue.size());
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public int peek(){
		int item = -1;
		int queueSize = 0;
		if(firstQueue.isEmpty() && secondQueue.isEmpty()){
			throw new EmptyStackException();
		}
		
		if(!firstQueue.isEmpty()){
			queueSize = firstQueue.size();
			for(int i = 0; i < queueSize; i++){
				if(i == queueSize - 1){
					item = firstQueue.deQueue();
					break;
				}
				firstQueue.enQueue(firstQueue.deQueue());
			}
			firstQueue.enQueue(item);
			return item;
		}
		
		if(!secondQueue.isEmpty()){
			queueSize = secondQueue.size();
			for(int i = 0; i < queueSize; i++){
				if(i == queueSize - 1){
					item = secondQueue.deQueue();
					break;
				}
				secondQueue.enQueue(secondQueue.deQueue());
			}
			secondQueue.enQueue(item);
			return item;
		}
		return item;
	}
	
	@Override
	public String toString(){
		return firstQueue.isEmpty() ? secondQueue.toString() : firstQueue.toString();
	}

}
