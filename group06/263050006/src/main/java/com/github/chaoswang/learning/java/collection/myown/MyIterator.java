package com.github.chaoswang.learning.java.collection.myown;

public interface MyIterator {
	/*��arraylist��ʵ��һ��������*/
	boolean hasNext();
	Object next();
	void remove();
}
