package week8.jvm.queue;

import java.util.NoSuchElementException;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	
	//用数组来保存循环队列的元素
	private Object[] elementData ;
	private int size=0;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public CircleQueue(int capacity){	
		elementData=new Object[capacity];
	}
	
	/**
	 * 不能只用 front == rear 进行判断
	 * @return
	 */
	public boolean isEmpty() {
		return (front == rear) && size == 0;       
    }

	public boolean isFull(){
		return size == elementData.length;
	}
	
    public int size() {
       return size;
    }


    public void enQueue(E data) {
  
    	if(isFull()){
    		throw new RuntimeException("Circle queue is full");
    	}
    	
    	elementData[rear]=data;
    	
    	rear=(rear+1)%elementData.length;
    	size++;
    	
    }

    public E deQueue() {
    	if(isEmpty()){
    		throw new NoSuchElementException("Circle queue is empty");
    	}
    	E element=(E) elementData[front];
    	elementData[front] = null;
    	
    	front=(front+1)%elementData.length;
    	size--;
    	
    	return element;
    }
  
}