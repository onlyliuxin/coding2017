package task0430.coding.basic.queue;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	private int size;
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public boolean isEmpty() {
		return size == 0;
        
    }
	
	public boolean isFull() {
		return size==elementData.length;
	}

    public int size() {
        return size;
    }

    

    public boolean enQueue(E data) {
        if(isFull()){
        	return false;
        }
        elementData[rear] = data;
        size++;
        if(rear+1 == elementData.length){
        	rear = 0;
        }else{
        	rear++;
        }
    	return true;
    }

    public E deQueue() {
    	if(isEmpty()){
    		throw new RuntimeException("the queue is empty!");
    	}
		E result = (E) elementData[front];
    	size--;
    	if(front+1 == elementData.length){
    		front = 0;
        }else{
        	front++;
        }
        return result;
    }
}
