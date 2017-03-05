package Collection.Concrete;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import Collection.AbstractList;
import Collection.Iterator;

public class Stack<E> extends AbstractList<E> {
	
	private ArrayList<E> myList;

	public Stack() {
		this.myList = new ArrayList<E>();
	}

	public void push(E e){
		myList.addLast(e);
	}
	
	public E pop(){
		checkEmpty();
		return myList.removeLast();
	}
	
	private void checkEmpty() {
		if (0 == size())
			throw new EmptyStackException();
	}

	public E peek(){
		checkEmpty();
		return myList.getLast();
	}

	public int size(){
		return myList.size();
	}

	@Override
	public void add(E e) {
		push(e);
	}

	@Override
	public E get(int index) {
		checkEmpty();
		return myList.get(size() - index - 1);
	}
	
	@Override
	protected Iterator<E> iterator() {
		return new StackIterator(myList);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myList == null) ? 0 : myList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stack other = (Stack) obj;
		if (myList == null) {
			if (other.myList != null)
				return false;
		} else if (!myList.equals(other.myList))
			return false;
		return true;
	}
	
	private class StackIterator<E> implements Iterator<E> {

		private ArrayList<E> myArrayList;
		private int pos;
		
		public StackIterator(ArrayList<E> myList) {
			myArrayList = myList;
			pos = 0;
		}

		@Override
		public boolean hasNext() {
			return pos < size();
		}

		@Override
		public E next() {
			if (hasNext())
				return (E) get(pos);
			throw new NoSuchElementException();
		}
	}

	
	
}
