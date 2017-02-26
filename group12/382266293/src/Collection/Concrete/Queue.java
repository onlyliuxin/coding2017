package Collection.Concrete;
import java.util.NoSuchElementException;

import Collection.AbstractList;
import Collection.Iterator;

public class Queue<E> extends AbstractList<E> {
	
	private LinkedList<E> myList;

	public Queue() {
		this.myList = new LinkedList<E>();
	}
	
	public void enQueue(E e){
		myList.addLast(e);
	}
	
	public E deQueue(){
		if (0 == size())
			return null;
		return myList.removeFirst();
	}

	@Override
	public void add(E e) {
		enQueue(e);
	}
	
	@Override
	public E get(int index) {
		if (0 == size())
			return null;
		return myList.get(index);
	}
	
	public E element() {
		if (0 == size())
			return null;
		return myList.getFirst();
	}


	@Override
	public int size() {
		return myList.size();
	}
	
	@Override
	protected Iterator<E> iterator() {
		return myList.iterator();
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
		Queue other = (Queue) obj;
		if (myList == null) {
			if (other.myList != null)
				return false;
		} else if (!myList.equals(other.myList))
			return false;
		return true;
	}




	

}
