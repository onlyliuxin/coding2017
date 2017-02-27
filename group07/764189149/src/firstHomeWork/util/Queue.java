package firstHomeWork.util;

/**
 * @Description: 循环队列
 * @author: leijing
 * @date: 2017年2月24日 下午9:00:30
 * @param <E>
 */
public class Queue<E> {
	private Object[] queue;
	private int capacity;
	private static int INITIAL_CAPACITY = 10;
	private int front; //队头
	private int rear; //队尾 
	
	public Queue(int capacity){
		this.capacity = capacity;
		this.front = 0;
		this.rear = 0;
		queue = new Object[capacity];
	}
	public Queue(){
		this(INITIAL_CAPACITY);
	}

	public boolean enQueue(E e) throws Exception{	
		if(isFull()){
			return false;
		}
		if(rear == capacity - 1){//循环利用
			rear = 0;
		}
		queue[rear++] = e;
		return true;
	}

	public E deQueue(){
		if(isEmpty()){
			return null;
		}
		if(front == capacity - 1){
			front = 0;
		}
		
		return (E) queue[front++];
	}

	public boolean isEmpty(){
		return front == rear;
	}

	public int size(){
		return Math.abs(rear - front) + 1;
	}
	
	public boolean isFull(){
		return (rear + 1) % capacity == front;
	}
}
