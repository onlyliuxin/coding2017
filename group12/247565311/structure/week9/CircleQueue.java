package structure.week9;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = -1;  
	//队尾  
	private int rear = -1;
	
	public boolean isEmpty() {
		return front==0&&rear==0;
    }

    public int size() {
        return rear>=front?rear-front:rear-front+DEFAULT_SIZE;
    }

    

    public void enQueue(E data) {
    	if(rear>=front){
    		rear += 1;
    		rear %= DEFAULT_SIZE;
    		if(rear==front) front += 1;
    		elementData[rear] = data;
    	}else{
    		rear += 1;
    		rear %= DEFAULT_SIZE;
    		if(rear == front) 
    	}
        rear += 1;
        rear %= DEFAULT_SIZE;
        if(front<=rear){
        	front += 1;
        	front %= DEFAULT_SIZE;
        }
        elementData[rear] = data;
    }

    public E deQueue() {
    	if(rear!=front) return null;
    	E res = (E)elementData[front];
    	front += 1;
        return res;
    }
}