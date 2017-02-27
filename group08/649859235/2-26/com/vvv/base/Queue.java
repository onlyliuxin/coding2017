package com.vvv.base;

public class Queue {

    QueueNode head; 
    QueueNode tail;  
    private int size;
    
    public Queue() {  
        this.head = null;  
        this.tail = null;  
    }   
    
	public void enQueue(Object o) {
        if (head == null && tail == null) {  
        	tail = new QueueNode(o);  
            head = tail;  
        } else {  
            QueueNode node = new QueueNode(o);  
            tail.next = node;  
            tail = tail.next;  
        }  
        size++;
	}

	public Object deQueue() {
		if (head == null) {  
            return null;  
        }  
		
        if (head == tail && tail != null) {  
            QueueNode node = head;  
            tail = null;  
            head = null;  
            size--;
            return node.data;  
        }  
        
        Object obj = head.data;  
        head = head.next;
        size--;
        return obj;  
	}

	public boolean isEmpty() {
		if(head==null && tail == null)
			return true;
		return false;
	}

	public int size() {
		return size;
	}
	
}

class QueueNode {  
    Object data;  
    QueueNode next; 
 
    public QueueNode() {  
        this(null, null);  
    }  
 
    public QueueNode(Object data) {  
        this(data, null);  
    }  
 
    public QueueNode(Object data, QueueNode next) {  
        this.data = data;  
        this.next = next;  
    }  
}  
