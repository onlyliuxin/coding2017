package cn.task1;

import java.util.Arrays;

public class Queue {

    private static final int CAPACITY = 1024;
    private static int capacity;
    private static int front;
    private static int tail;
    private static Object[] array;
 
    public Queue() {
        this.capacity = CAPACITY;
        array = new Object[capacity];
        front = tail = 0;
    }
 
    public static int getSize() {
        if (isEmpty()){
        	return 0;        	
        }else{
        	return (capacity + tail - front) % capacity;        	
        }
    }
 
   
    public static boolean isEmpty() {
        return (front == tail);
    }
 
    //进栈
    public static void enqueue(Object element) throws ExceptionQueueFull {
        if (getSize() == capacity - 1){
        	throw new ExceptionQueueFull("Queue is full");        	
        }
        array[tail] = element;
        tail = (tail + 1) % capacity;
    }
 
    //出栈
    public static Object dequeue() throws ExceptionQueueEmpty {
        Object element;
        if(isEmpty()){
        	throw new ExceptionQueueEmpty("Queue is empty");        	
        }
        element = array[front];
        front = (front + 1) % capacity;
        return element;
    }
 
   
    public static Object frontElement() throws ExceptionQueueEmpty {
        if(isEmpty()){
        	throw new ExceptionQueueEmpty("Queue is empty");        	
        }
        return array[front];
    }
 
    public static void getAllElements() {
        Object[] arrayList = new Object[getSize()];
        for(int i=front,j=0;j<getSize();i++,j++){
                arrayList[j] = array[i];
        }
        System.out.println("All elements of queue: "+ Arrays.toString(arrayList));
    }
    
    
    public static void main(String[] args) throws ExceptionQueueFull, ExceptionQueueEmpty {
		
    	
    	Queue queue = new Queue();
    	
    	System.out.println(queue.isEmpty());
    	
    	queue.enqueue("1");
    	queue.enqueue("2");
    	queue.enqueue("3");
    	queue.enqueue("4");
    	queue.enqueue("5");
    	
    	System.out.println(queue.getSize());
    	
    	
    	System.out.println(queue.dequeue());
    	System.out.println(queue.getSize());
    	
    	System.out.println(queue.isEmpty());
    	System.out.println(queue.frontElement());
    	
    	queue.getAllElements();
	}
    
}

class ExceptionQueueEmpty extends Exception {
    public ExceptionQueueEmpty() {
 
    }
 
    public ExceptionQueueEmpty(String mag) {
        System.out.println(mag);
    }
}

class ExceptionQueueFull extends Exception {
	 
    public ExceptionQueueFull() {
 
    }
 
    public ExceptionQueueFull(String mag) {
        System.out.println(mag);
    }
}
