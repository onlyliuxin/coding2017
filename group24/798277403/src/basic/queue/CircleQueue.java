package basic.queue;

/**
 * 用数组实现循环队列
 * @author liuxin
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;

    private int size = 0;
	
	//用数组来保存循环队列的元素
	private Object[] elementData;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;

    public CircleQueue(){
        elementData = new Object[DEFAULT_SIZE] ;
    }

    public CircleQueue(int size){
        elementData = new Object[size];
    }

	public boolean isEmpty() {
		return size==0;
    }

    public int size() {
        return size;
    }

    public boolean isFull(){
        return  size == elementData.length;
    }

    public void enQueue(E data) {
        if(isFull()){
            throw new RuntimeException("The queue is full");
        }
        elementData[rear] = data;
        rear = (rear+1)%elementData.length;
        size++;
    }

    public E deQueue() {
        if(isEmpty()){
            throw new RuntimeException("The queue is empty");
        }
        E data = (E)elementData[front];
        elementData[front] = null;
        front  = (front+1) % elementData.length;
        size--;
        return data;
    }
}
