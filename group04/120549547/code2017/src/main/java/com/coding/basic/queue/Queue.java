package com.coding.basic.queue;

public interface Queue<T> {

	boolean add(T t);

	boolean offer(T t);

	T remove();

	T poll();

	boolean isEmpty();

	T peek();
}
