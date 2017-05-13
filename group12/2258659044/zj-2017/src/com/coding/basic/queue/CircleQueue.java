package com.coding.basic.queue;

public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public boolean isEmpty() {
		
        return front == rear;
    }

    public int size() {
    	
    	return rear-front>0?(rear-front):(rear-front+DEFAULT_SIZE);
    }

    

    public void enQueue(E data) {
        
    	rangeCheck(rear);   	
    	elementData[rear] = data;  
    	rear = (rear+1)%DEFAULT_SIZE;
    }

    @SuppressWarnings("unchecked")
	public E deQueue() {
    	
    	if(isEmpty()){
    		return null;
    	}
    	Object obj = elementData[front];   	
    	front = (front+1)%DEFAULT_SIZE;
        return (E) obj;
    }
    
    /**
     * 数组最后一个空间不存放数据<br>
     * 用来区分队列的空或者满
     * @param rear
     */
    private void rangeCheck(int rear) {
		
		if ((rear+1)%DEFAULT_SIZE == front) {
			throw new IndexOutOfBoundsException("队列数据已满！");
		}
	}
    
    @Override
    public String toString() {

    	if(isEmpty()){
    		return "[]";
    	}
    	    	
    	Object[] temp = new Object[DEFAULT_SIZE];
    	System.arraycopy(elementData, 0, temp, 0, DEFAULT_SIZE);
    	
    	int frontTemp = front;
    	int rearTemp = rear;
    	
    	StringBuilder str = new StringBuilder();
    	str.append("[");
    	while(!isEmpty()){    		
    		str.append(this.deQueue().toString()+",");
    	}
    	    	
    	front = frontTemp;
    	rear = rearTemp;
    	return str.toString().substring(0, str.length()-1)+"]";
    }
}
