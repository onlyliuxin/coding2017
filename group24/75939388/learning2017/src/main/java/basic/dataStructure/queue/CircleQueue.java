package basic.dataStructure.queue;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;

	private int capacity;
	
	//用数组来保存循环队列的元素
	private Object[] elementData;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;

    public CircleQueue(){
        this.capacity = DEFAULT_SIZE;
        elementData = new Object[DEFAULT_SIZE];
    }

	public CircleQueue(int capacity){
        this.capacity = capacity;
        this.elementData = new Object[this.capacity];
    }

	public boolean isEmpty() {
	    boolean flag = true;
	    for(int i = 0; i < this.capacity; i++) {
            if(elementData[i] != null){
                flag = false;
            }
        }
        return flag;
    }

    public boolean isFull(){
	    return this.size() == this.capacity;
    }

    public int size() {
	    int size = 0;
	    for(Object obj : elementData){
	        if(obj == null){
	            break;
            }
            size ++;
        }

        return size;
    }

    

    public void enQueue(E data) {
        if(data == null){
            throw new RuntimeException("cannot put null object");
        }

        if(this.size() < this.capacity){
            elementData[this.rear] = data;
            this.rear++;
        }else{
            throw new RuntimeException("circle queue full");
        }
    }

    public E deQueue() {
	    E data = (E)elementData[front];

	    Object[] arr = new Object[this.capacity];
        System.arraycopy(elementData, 1, arr, 0, this.size() - 1);
        this.elementData = arr;
        return data;
    }
}
