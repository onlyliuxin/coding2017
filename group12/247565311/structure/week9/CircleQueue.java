package structure.week9;

/**
 * 用数组实现循环队列
 * @author liuxin
 * @param <E>
 */
public class CircleQueue <E> {
	private final static int DEFAULT_SIZE = 10;
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	boolean isFull = false;
	
	private int front = 0;  //队头
	private int rear = 0;    //队尾 
	public boolean isEmpty() {
		if(isFull) return false;
		return (rear-front+DEFAULT_SIZE)%DEFAULT_SIZE==0;
    }
    public int size() {
    	if(isFull) return DEFAULT_SIZE;
    	else{
        	if(rear<front)return rear-front+DEFAULT_SIZE;
    		else return rear-front;
    	}
    }
    public void enQueue(E data) {
    	if(isFull){
    		elementData[rear] = data;
    		rear += 1;
    		rear %= DEFAULT_SIZE;
    		front = rear;
    	}else{
    		elementData[rear] = data;
    		rear += 1;
    		rear %= DEFAULT_SIZE;
    		if(rear == front) isFull = true;
    	}
    }
    public E deQueue() {
    	E res = (E) elementData[front];
    	front += 1;
    	front %=DEFAULT_SIZE;
    	isFull = false;
    	return res;
    }
}