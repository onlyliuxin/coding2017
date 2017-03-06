<<<<<<< HEAD
package com.coding.basic;

public class ArrayList<E> implements List<E> {
	
	private int size;
	
	private E[] data;
	
	public void add(E e){
	    add(size, e);	
	}

    public ArrayList() {
        clear();
    }

    public ArrayList(int capacity) {
        clear();
        ensureCapacity(capacity);
    }

	public void add(int index, E e){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
		if (data.length == size) {
            ensureCapacity(size + size >> 1 + 1);
        }
        for (int i = size++; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = e;
	}
	
	public E get(int index){
		return data[index];
	}
	
	public E remove(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        E copy = data[index];
        --size;
        for (int i = index; i < size; ++i) {
            data[i] = data[i + 1];
        }
        return copy;
	}

    public boolean contains(E e) {
        for (int i = 0; i < size; ++i) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        size = 0;
        data = (E[]) new Object[0];
    }
	
	public int size(){
		return size;
	}

    public boolean isEmpty() {
        return size == 0;
    }
	
	public Iterator<E> iterator(){
		return new ArrayListIterator();
	}

    public void ensureCapacity(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void trimToSize() {
        E[] newData = (E[]) new Object[size];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private class ArrayListIterator implements Iterator<E> {
        int current = 0;

        public boolean hasNext() {
            return current < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return data[current++];
        }

        public void remove() {
            ArrayList.this.remove(current);
        }
    }
}
=======
package com.coding.basic;

public class ArrayList<E> implements List<E> {
	
	private int size;
	
	private E[] data;
	
	public void add(E e){
	    add(size, e);	
	}

    public ArrayList() {
        clear();
    }

    public ArrayList(int capacity) {
        clear();
        ensureCapacity(capacity);
    }

	public void add(int index, E e){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
		if (data.length == size) {
            ensureCapacity(size * 2 + 1);
        }
        for (int i = size++; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = e;
	}
	
	public E get(int index){
		return data[index];
	}
	
	public E remove(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        E copy = data[index];
        --size;
        for (int i = index; i < size; ++i) {
            data[i] = data[i + 1];
        }
        return copy;
	}

    public boolean contains(E e) {
        for (int i = 0; i < size; ++i) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        size = 0;
        data = (E[]) new Object[0];
    }
	
	public int size(){
		return size;
	}

    public boolean isEmpty() {
        return size == 0;
    }
	
	public Iterator<E> iterator(){
		return new ArrayListIterator();
	}

    public void ensureCapacity(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void trimToSize() {
        E[] newData = (E[]) new Object[size];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private class ArrayListIterator implements Iterator<E> {
        int current = 0;

        public boolean hasNext() {
            return current < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return data[current++];
        }

        public void remove() {
            ArrayList.this.remove(current);
        }
    }
}
>>>>>>> master
