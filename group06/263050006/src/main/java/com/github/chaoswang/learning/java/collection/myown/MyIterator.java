package com.github.chaoswang.learning.java.collection.myown;

public interface MyIterator {
	/*在arraylist里实现一个迭代器*/
	boolean hasNext();
	Object next();
	void remove();
}
