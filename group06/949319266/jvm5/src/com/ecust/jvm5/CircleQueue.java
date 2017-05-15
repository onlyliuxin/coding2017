package com.ecust.jvm5;



public class CircleQueue <E> {

	

	private final static int DEFAULT_SIZE = 10;



    private int count = 0;

	

	//用数组来保存循环队列的元素

	private Object[] elementData = new Object[DEFAULT_SIZE] ;

	

	//队头

	private int front = 0;  

	//队尾  

	private int rear = 0;

	

	public boolean isEmpty() {

		return count == 0;

        

    }



    public int size() {

        return count;

    }



    



    public void enQueue(E data) {

        if (count == DEFAULT_SIZE ) {

            throw new RuntimeException();

        }

        elementData[front] = data;

        front = (front + 1) % DEFAULT_SIZE;

        count++;

    }



    public E deQueue() {

        if (count == 0) {

            return null;

        }

        count--;

        E e = (E)elementData[rear];

        rear = (rear + 1) % DEFAULT_SIZE;

        return e;

    }



    public static void main(String[] args) {

        CircleQueue<Integer> queue = new CircleQueue<>();

        queue.enQueue(1);

        queue.enQueue(2);

        queue.enQueue(3);

        queue.enQueue(4);

        queue.enQueue(5);

        queue.enQueue(6);

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());System.out.println(queue.deQueue());System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        queue.enQueue(7);

        queue.enQueue(8);

        queue.enQueue(9);

        queue.enQueue(0);

        queue.enQueue(1);

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());

        System.out.println(queue.deQueue());



    }





}