package com.basic;


public interface Iterator<E> {

	boolean hasNext();

	E next();

	void remove();
}
