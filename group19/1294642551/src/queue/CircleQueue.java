package queue;


/**
 * 用数组实现循环队列
 * front 和 rear 之间有一个空位来区分队列空和满
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	
	//用数组来保存循环队列的元素
	private Object[] elementData ;
	//队列实际元素个数
	private int size = 0;
	
	//队头 指向第一个元素的前一个
	private int front = -1;  
	//队尾  指向实际的最后一个元素的位置
	private int rear = -1;
	
	public boolean isEmpty() {
		return front == rear;
        
    }
	
	public boolean isFull(){
		return (rear + 1) % elementData.length == front;
	}

    public int size() {
        return size;
    }

    public CircleQueue(int capacity){
    	elementData = new Object[capacity];
    }

    public void enQueue(E data) {
    	if(isFull()){
    		throw new IndexOutOfBoundsException("队列满");
    	}
    	rear = (rear+1) % elementData.length;
    	elementData[rear] = data;
    	size++;
    }

    @SuppressWarnings("unchecked")
	public E deQueue() {
    	if(isEmpty()){
    		throw new IndexOutOfBoundsException("队列空");
    	}
    	front = (front + 1) % elementData.length;
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
    	int i = (front + 1) % elementData.length;
    	while(i != (rear + 1) % elementData.length){
    		sb.append(elementData[i]);
    		if(i != rear){
    			sb.append(", ");
    		}
    		i = (i + 1) % elementData.length;
    	}
    	sb.append("]");
    	return sb.toString();
    }
}
