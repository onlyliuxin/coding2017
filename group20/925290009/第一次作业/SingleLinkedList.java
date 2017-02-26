package BasicData;

import java.util.NoSuchElementException;

import javax.lang.model.element.Element;

public class SingleLinkedList<T> implements MyList<T> {

	private int msize;// 记录元素的个数
	Note<T> head;// 指向第一个元素
	Note<T> last;// 指向最后一个元素

	private static class Note<T> {
		private T item = null;
		Note<T> next = null;

		public Note(T t) {
			this.item = t;
		}
	}

	public SingleLinkedList() {

	}

	@Override
	public boolean add(T t) {
		// TODO Auto-generated method stub
		final Note<T> l = last;
		final Note<T> newNote = new Note<T>(t);
		last = newNote;
		if (l == null) {
			head = newNote;// 只允许有一个null元素
		} else {
			l.next = last;
		}
		msize++;
		return true;
	}

	@Override
	public void add(int index, T t) {
		// TODO Auto-generated method stub
		if (index < 0 || index > msize) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			addFirst(t);
		}
		if (index == msize) {
			add(t);
		}
		Note<T> current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		final Note<T> newNote = new Note<T>(t);
		newNote.next = current.next;
		current.next = newNote;
		msize++;

	}

	public boolean addFirst(T t) {
		final Note<T> newNote = new Note<T>(t);
		newNote.next = head;
		head = newNote;
		msize++;
		return true;
	}

	public boolean addLast(T t) {
		final Note<T> newNote = new Note<T>(t);
		newNote.next = null;
		last.next = newNote;
		last = newNote;
		msize++;
		return true;
	}

	public T removeLast() throws Exception {
		if (head == null) {
			throw new Exception("LinkedList is Empty!");
		}
		Note<T> current = head;
		if (head.next == null) {
			head = null;
			last = null;
		} else {
			while (current.next != null) {
				if (current.next == last) {
					last = current;
					last.next = null;
					break;
				}
				current = current.next;
			}

		}
		msize--;
		return current.item;

	}

	public T removeFirst() throws Exception {
		if (head == null) {
			throw new Exception("LinkedList is Empty!");
		}
		Note<T> element = head;
		head = head.next;
		msize--;
		return element.item;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return msize;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > msize) {
			throw new IndexOutOfBoundsException();
		}
		Note<T> element;
		if (index == 0) {
			element = head;
			head = head.next;
			msize--;
			return element.item;
		}
		Note<T> current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		element = current.next;
		if (index == msize) {
			current.next = null;
			last = current;
		} else {
			current.next = current.next.next;
		}

		msize--;
		return element.item;
	}

	@Override
	public boolean set(int index, T t) {
		// TODO Auto-generated method stub
		if (index < 0 || index > msize) {
			throw new IndexOutOfBoundsException();
		}
		Note<T> current = head;
		for (int i = 0; i < index; i++) {
			current = head.next;
		}
		current.item = t;
		return true;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > msize) {
			throw new IndexOutOfBoundsException();
		}
		Note<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.item;
	}

	public MyIterator<T> iterator() {
		return new MyLinkedListIterator();
	}

	private class MyLinkedListIterator implements MyIterator<T> {

		private int current = 0;
		private Note<T> nextNote = head;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < msize;
		}

		@Override
		public T Next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				current++;
				Note<T> eleNote = nextNote;
				if (last == nextNote) {
					nextNote = null;
				} else {
					nextNote = nextNote.next;
				}
				return eleNote.item;

			}

		}
	}

}
