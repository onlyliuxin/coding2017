package test08;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue<E> {
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	private int size=0;
	public boolean isEmpty() {
		return size()==0;        
    }

    public int size() {
        return size;
    }

    

    public void enQueue(E data) throws Exception {
    	if (size()<=DEFAULT_SIZE) {
        	elementData[rear]=data;
        	if (rear==DEFAULT_SIZE-1) {
        		rear=rear+1-DEFAULT_SIZE;
			}else{
	        	rear++;			
			}
        	size++;
		} else {
			throw new Exception("队列已满！");
		}
    }

    public E deQueue() {
    	if (size()>0) {
        	E data=(E) elementData[front];
        	elementData[front]=null;
        	if (front==DEFAULT_SIZE-1) {
        		front=front+1-DEFAULT_SIZE;
    		}else{
    			front++;			
    		}
        	size--;
            return data;
		} else {
			return null;
		}
    }
}
