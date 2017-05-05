package com.datastructures.basic;

public interface Iterator<E> {
	boolean hasNext();

	E next();

    void remove();
}
