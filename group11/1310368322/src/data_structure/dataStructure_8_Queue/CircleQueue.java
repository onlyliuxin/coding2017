package dataStructure_8_Queue;

public class CircleQueue <E>{

	private final static int DEFAULT_SIZE = 5;
	
	// 用数组来保存循环队列的元素
	private Object [] elementData = new Object[DEFAULT_SIZE];
	
	// 队头
	private int front = 0;
	// 队尾
	private int rear = 0;
	
	
	public Object getElementData(int index){
		return elementData[index];
	}
	public boolean isEmpty(){
		return front==rear;
	}
	
	public int size(){
		return rear-front;
	}
	
	public void enQueue(E data){
		if((rear+1)%DEFAULT_SIZE == front){
			return;
		}else{
			rear = (++rear)%DEFAULT_SIZE;
			elementData[rear] = data;
		}
	}
	
	public E deQueue(){
		front = (++front)%DEFAULT_SIZE;
		return (E) elementData[front];
	}
}
