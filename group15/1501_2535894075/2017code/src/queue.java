package firstweek;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

public class queue<E> extends AbstractList<E> implements List<E> ,java.io.Serializable {

	private static final long serialVersionUID = 6203363761107460505L;
	
	// 指向队头
	private transient Entry<E> front;
	
	private transient int size ;
	// 指向队尾
	private transient Entry<E> rear;
	
	public Iterator<E> singleListIterator() {
		return new QueueIterator();
	}

	public queue() {
		this.front = this.rear = null;
	}
	
	@Override
	public boolean add(E e) {
		Entry<E> newData = new Entry<E>(e, null);
		if (this.rear == null) {
			this.rear = newData;
			this.front = this.rear;
		} else {
			Entry<E> preElement = this.front;
			while (preElement.next != null) {
				preElement = preElement.next;
			}
			preElement.next = newData;
		}
		size ++;
		return true;
	}
	
	/**
	 * 队尾加入元素
	 * @param e
	 * @return
	 */
	public boolean append(E e) {
		return add(e);
	}
	
	/**
	 * 取队头数据
	 */
	@Override
	public E get(int index) {
		return this.front.element;
	}
	
	public E getFrist() {
		return get(0);
	}
	
	/**
	 * 出队列
	 * @return
	 */
	public E delete() {
		E result = null;
		Entry<E> entry = this.front;
		if (entry != null) {
			result = entry.element;
			this.front = entry.next;
			entry = null;
		}
		size --;
		return result;
	}
	
	/**
	 * 队长度
	 */
	@Override
	public int size() {
		return size;
	}

	private static class Entry<E> {
		E element;
		Entry<E> next;
		public Entry(E element, Entry<E> next) {
			this.element = element;
			this.next = next;
		}
	}
	
	private class QueueIterator implements Iterator<E> {
		private Entry<E> itFront = front;
		@Override
		public boolean hasNext() {
			return itFront != null;
		}
	
		@Override
		public E next() {
			E result = itFront.element ;
			itFront = itFront.next;
			return result;
		}
	
		@Override
		public void remove() {
			throw new UnsupportedOperationException("can not remove");
		}
	}
}