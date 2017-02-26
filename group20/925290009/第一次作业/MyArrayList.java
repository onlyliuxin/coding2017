package BasicData;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 实现基本的数据结构——ArrayList
 * 
 * @author Ralf
 * 
 * @param <T>
 */
public class MyArrayList<T> implements MyList<T> {

	private static final int DEFAULT_CAPACITY = 10;
	private static int msize;
	private T[] elements;

	public MyArrayList() {
		msize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	private void ensureCapacity(int newCapacity) {
		// TODO Auto-generated method stub
		if (msize > newCapacity) {
			return;
		}
		T[] oldElements = elements;
		elements = (T[]) new Object[newCapacity];
		for (int i = 0; i < size(); i++) {
			elements[i] = oldElements[i];
		}

	}
	public void trimSize(){
		if (msize < elements.length) {
			ensureCapacity(msize);
		}
	}

	@Override
	public boolean add(T t) {
		// TODO Auto-generated method stub

		if (elements.length == size()) {
			ensureCapacity(2 * size() + 1);
		}
		elements[msize++] = t;
		return true;
	}

	@Override
	public void add(int index, T t) {

		if (msize == elements.length) {
			ensureCapacity(2 * msize + 1);
		}
		for (int i = size(); i >= index; i--) {
			elements[i + 1] = elements[i];
		}
		elements[index] = t;
		msize++;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return msize;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		T old = elements[index];
		for (int i = index; i < msize; i++) {
			elements[i] = elements[i + 1];
		}
		elements[msize--] = null;
		return old;

	}

	@Override
	public boolean set(int index, T t) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		elements[index] = t;
		return true;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > msize) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return elements[index];
	}

	@Override
	public String toString() {
		return Arrays.toString(elements);
	}

	public MyIterator<T> iterator() {
		return new MyArrayListIterator();
	}

	private class MyArrayListIterator implements MyIterator<T> {

		private int current = 0;// 迭代器的指针

		public boolean hasNext() {
			// TODO Auto-generated method stub

			return current < size();
		}

		public T Next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return elements[current++];
		}

	}

}
