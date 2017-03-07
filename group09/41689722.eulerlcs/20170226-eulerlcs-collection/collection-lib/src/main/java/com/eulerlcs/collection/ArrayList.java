/**
 * 90% or more copy from jdk
 */
package com.eulerlcs.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.function.Consumer;

public class ArrayList<E> implements List<E>, RandomAccess {
	private static final int MAXÅQARRAYÅQSIZE = 1 << 10;
	private transient Object[] elementData = new Object[0];
	private int size;
	private transient int modCount = 0;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null) {
			for (Object obi : elementData) {
				if (obi == null) {
					return true;
				}
			}
		} else {
			for (Object obj : elementData) {
				if (o.equals(obj)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object e : c)
			if (!contains(e))
				return false;
		return true;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size, elementData.getClass());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			return (T[]) Arrays.copyOf(elementData, size, a.getClass());
		} else {
			System.arraycopy(elementData, 0, a, 0, size);
			if (a.length > size)
				a[size] = null;
			return a;
		}
	}

	@Override
	public boolean add(E e) {
		ensureExplicitCapacity(size + 1); // Increments modCount!!
		elementData[size] = e;
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);
		ensureExplicitCapacity(size + 1); // Increments modCount!!
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = element;
		size++;
	}

	@Override
	public E remove(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);

		modCount++;
		@SuppressWarnings("unchecked")
		E oldValue = (E) elementData[index];
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[--size] = null;// clear to let GC do its work

		return oldValue;
	}

	@Override
	public boolean remove(Object o) {
		int index = -1;

		if (o == null) {
			for (int i = 0; i < size; i++)
				if (elementData[i] == null) {
					index = i;
					break;
				}
		} else {
			for (int i = 0; i < size; i++)
				if (o.equals(elementData[i])) {
					index = i;
					break;
				}
		}

		if (index > 0) {
			modCount++;
			int numMoved = size - index - 1;
			if (numMoved > 0)
				System.arraycopy(elementData, index + 1, elementData, index, numMoved);
			elementData[--size] = null;// clear to let GC do its work

			return true;
		}

		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean modified = false;
		for (Object obj : c) {
			modified |= remove(obj);
		}

		return modified;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		Object[] a = c.toArray();
		int numNew = a.length;
		ensureExplicitCapacity(size + numNew);// Increments modCount
		System.arraycopy(a, 0, elementData, size, numNew);
		size += numNew;
		return numNew != 0;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);

		Object[] a = c.toArray();
		int numNew = a.length;
		ensureExplicitCapacity(size + numNew);// Increments modCount

		int numMoved = size - index;
		if (numMoved > 0)
			System.arraycopy(elementData, index, elementData, index + numNew, numMoved);

		System.arraycopy(a, 0, elementData, index, numNew);
		size += numNew;
		return numNew != 0;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		final Object[] elementData = this.elementData;
		int r = 0, w = 0;
		boolean modified = false;
		for (; r < size; r++)
			if (c.contains(elementData[r]))
				elementData[w++] = elementData[r];

		if (w != size) {
			// clear to let GC do its work
			for (int i = w; i < size; i++)
				elementData[i] = null;
			modCount += size - w;
			size = w;
			modified = true;
		}

		return modified;
	}

	@Override
	public void clear() {
		modCount++;
		for (int i = 0; i < size; i++)
			elementData[i] = null;

		size = 0;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);

		return (E) elementData[index];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {
		if (index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);

		E oldValue = (E) elementData[index];
		elementData[index] = element;
		return oldValue;
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++)
				if (elementData[i] == null)
					return i;
		} else {
			for (int i = 0; i < size; i++)
				if (o.equals(elementData[i]))
					return i;
		}

		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size - 1; i >= 0; i--)
				if (elementData[i] == null)
					return i;
		} else {
			for (int i = size - 1; i >= 0; i--)
				if (o.equals(elementData[i]))
					return i;
		}

		return -1;
	}

	private void ensureExplicitCapacity(int minCapacity) {
		modCount++;

		if (elementData.length > minCapacity) {
			return;
		} else if (minCapacity > MAXÅQARRAYÅQSIZE) {
			throw new OutOfMemoryError();
		}

		int oldCapacity = elementData.length;

		int newCapacity = oldCapacity == 0 ? 10 : (oldCapacity + (oldCapacity >> 1));
		if (newCapacity > MAXÅQARRAYÅQSIZE) {
			newCapacity = MAXÅQARRAYÅQSIZE;
		}

		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ListItr(0);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index: " + index);
		return new ListItr(index);
	}

	/**
	 * fully copy from jdk ArrayList.Itr
	 */
	private class Itr implements Iterator<E> {
		int cursor; // index of next element to return
		int lastRet = -1; // index of last element returned; -1 if no such
		int expectedModCount = modCount;

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		@SuppressWarnings("unchecked")
		public E next() {
			checkForComodification();
			int i = cursor;
			if (i >= size)
				throw new NoSuchElementException();
			Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i + 1;
			return (E) elementData[lastRet = i];
		}

		@Override
		public void remove() {
			if (lastRet < 0)
				throw new IllegalStateException();
			checkForComodification();

			try {
				ArrayList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		@SuppressWarnings("unchecked")
		public void forEachRemaining(Consumer<? super E> consumer) {
			Objects.requireNonNull(consumer);
			final int size = ArrayList.this.size;
			int i = cursor;
			if (i >= size) {
				return;
			}
			final Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length) {
				throw new ConcurrentModificationException();
			}
			while (i != size && modCount == expectedModCount) {
				consumer.accept((E) elementData[i++]);
			}
			// update once at end of iteration to reduce heap write traffic
			cursor = i;
			lastRet = i - 1;
			checkForComodification();
		}

		final void checkForComodification() {
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
		}
	}

	/**
	 * fully copy from jdk ArrayList.ListItr
	 */
	private class ListItr extends Itr implements ListIterator<E> {
		ListItr(int index) {
			super();
			cursor = index;
		}

		@Override
		public boolean hasPrevious() {
			return cursor != 0;
		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}

		@Override
		@SuppressWarnings("unchecked")
		public E previous() {
			checkForComodification();
			int i = cursor - 1;
			if (i < 0)
				throw new NoSuchElementException();
			Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i;
			return (E) elementData[lastRet = i];
		}

		@Override
		public void set(E e) {
			if (lastRet < 0)
				throw new IllegalStateException();
			checkForComodification();

			try {
				ArrayList.this.set(lastRet, e);
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public void add(E e) {
			checkForComodification();

			try {
				int i = cursor;
				ArrayList.this.add(i, e);
				cursor = i + 1;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}
	}
}
