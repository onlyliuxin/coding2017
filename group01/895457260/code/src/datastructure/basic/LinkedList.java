package datastructure.basic;

import datastructure.exception.EmptyListException;

public class LinkedList implements List {
	
	private Node head;
	private int size;

	public LinkedList() {
	    head = new Node();
    }

    @Override
	public void add(Object o) {
        addLast(o);
	}

	@Override
	public void add(int index , Object o) {
	    Node pre = findNode(index - 1);
	    Node node = new Node();
	    node.data = o;
	    addNode(node, pre);
	}

	@Override
	public Object get(int index) {
	    checkIndex(index);
		return findNode(index).data;
	}

	@Override
	public Object remove(int index) {
	    checkIndex(index);
	    Node pre = findNode(index - 1);
	    Node removed = pre.next;
	    removeNode(removed, pre);
		return removed.data;
	}

	@Override
	public int size() {
		return size;
	}
	
	public void addFirst(Object o) {
		Node node = new Node();
		node.data = o;
		addNode(node, head);
	}

	public void addLast(Object o) {
        Node node = new Node();
        node.data = o;
        Node pre = findNode(size() - 1);
        addNode(node, pre);
	}

	public Object removeFirst() {
		if (size() == 0) {
			throw new EmptyListException();
		}
	    Node removed = head.next;
		removeNode(head.next, head);
		return removed.data;
	}

	public Object removeLast() {
		if (size() == 0) {
			throw new EmptyListException();
		}
	    return remove(size() - 1);
	}

	@Override
	public Iterator iterator() {
		return new Iterator() {
			Node node = head;
			@Override
			public boolean hasNext() {
				return node.next != null;
			}

			@Override
			public Object next() {
				node = node.next;
				return node.data;
			}
		};
	}

	private static class Node{
		Object data;
		Node next;
	}

	private Node findNode(int index) {
	    if (index == -1) {
	        return head;
        } else {
	        checkIndex(index);
        }
        Node node = head.next;
        for (int i = 0; i < index; ++i) {
	        node = node.next;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException(indexOutOfBoundMessage(index));
        }
    }

    private String indexOutOfBoundMessage(int index) {
        return "index: " + index + ", size: " + size();
    }

    private void addNode(Node node, Node pre) {
	    node.next = pre.next;
	    pre.next = node;
	    size++;
    }

    private void removeNode(Node node, Node pre) {
	    pre.next = node.next;
	    node.next = null;
	    size--;
    }
}
