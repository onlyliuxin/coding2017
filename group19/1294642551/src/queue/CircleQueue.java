package queue;

//import java.util.NoSuchElementException;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	//队列实际元素个数
	private int size = 0;
	
	//队头 指向第一个元素的前一个
	private int front = -1;  
	//队尾  指向实际的最后一个元素的位置
	private int rear = -1;
	
	public boolean isEmpty() {
		return front == rear;
        
    }

    public int size() {
        return size;
    }

    public CircleQueue(int length){
    	DEFAULT_SIZE = length;
    	elementData = new Object[length];
    }

    public void enQueue(E data) {
    	if((rear+1) % DEFAULT_SIZE == front){
    		throw new IndexOutOfBoundsException("队列满");
    	}
    	rear = (rear+1) % DEFAULT_SIZE;
    	elementData[rear] = data;
    	size++;
    }

    @SuppressWarnings("unchecked")
	public E deQueue() {
    	if(isEmpty()){
    		throw new IndexOutOfBoundsException("队列空");
    	}
    	front = (front + 1) % DEFAULT_SIZE;
    	size--;
    	return (E) elementData[front];
    }
    
    @SuppressWarnings("unchecked")
	public E get(int index){
    	return (E) elementData[index];
    }
    
    public void set(int index, E data){
    	elementData[index] = data;
    }
    
    public String toString(){
    	StringBuilder sb = new StringBuilder("[");
    	int i = (front + 1) % DEFAULT_SIZE;
    	while(i != (rear + 1) % DEFAULT_SIZE){
    		sb.append(elementData[i]);
    		if(i != rear){
    			sb.append(", ");
    		}
    		i = (i + 1) % DEFAULT_SIZE;
    	}
    	sb.append("]");
    	return sb.toString();
    }
}
