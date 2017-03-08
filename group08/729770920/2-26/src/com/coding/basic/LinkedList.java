package com.coding.basic;

public class LinkedList<E> implements List<E> {
    private int size = 0;
	private Node<E> head = new Node<>();
    private Node<E> tail = new Node<>();
	
    public LinkedList() {
        head.next = tail;
        tail.prev = head;
    }

	public void add(E e) {
        addLast(e);
	}

	public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> cursor;
        if (index < size/2) {
            cursor = head;
            for (int i = 0, num = index; i < num; ++i) {
                cursor = cursor.next;
            }
        } else {
            cursor = tail.prev;
            for (int i = 0, num = size-index; i < num; ++i) {
                cursor = cursor.prev;
            }
        }
        cursor.next = cursor.next.prev = new Node<E>(e, cursor, cursor.next);
        ++size;
	}

	public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> cursor;
        if (index < size/2) {
            cursor = head.next;
            for (int i = 0; i < index; ++i) {
                cursor = cursor.next;
            }
        } else {
            cursor = tail.prev;
            for (int i = 0, num = size-index-1; i < num; ++i) {
                cursor = cursor.prev;
            }
        }
        return cursor.data;
	}

	public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> cursor;
        if (index < size/2) {
            cursor = head.next;
            for (int i = 0; i < index; ++i) {
                cursor = cursor.next;
            }
        } else {
            cursor = tail.prev;
            for (int i = 0, num = size-index-1; i < num; ++i) {
                cursor = cursor.prev;
            }
        }
        cursor.prev.next = cursor.next;
        cursor.next.prev = cursor.prev;
        --size;
        return cursor.data;
	}
	
	public int size() {
		return size;
	}

    public boolean isEmpty() {
        return size == 0;
    }
	
	public void addFirst(E e) {
        add(0, e);
	}

	public void addLast(E e) {
        add(size, e);
	}

	public E removeFirst() {
        return remove(0);
	}

	public E removeLast() {
        return remove(size-1);
	}

    public void clear() {
        while (!isEmpty()) {
            removeFirst();
        }
    }

    public boolean contains(E e) {
        Iterator it = this.iterator();
        while (it.hasNext()) {
            if (it.next() == e) {
                return true;
            }
        }
        return false;
    }

	public Iterator iterator() {
		return new LinkedListIterator();
	}
	
	private static class Node<E> {
		E data = null;
        Node<E> prev = null;
		Node<E> next = null;

        public Node() {
        }

        public Node(E e, Node p, Node n) {
            data = e;
            prev = p;
            next = n;
        }
	}

    private class LinkedListIterator implements Iterator<E> {
        Node<E> currentNode = head.next;

        public boolean hasNext() {
            return currentNode != tail;
        }

        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }

        public void remove() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Node<E> nextNode = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            currentNode.prev.next = currentNode.next;
            currentNode = nextNode;
            --size;
        }
    }
}
