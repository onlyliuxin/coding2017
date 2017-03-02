package coding;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	private Node head;
	private int size;

	public void add(Object o) {
		// �ж�ͷ�Ƿ�������
		if (head == null) {
			head = new Node(o, null);
		} else {
			Node newNode = head;
			while (newNode.next != null) {
				newNode = newNode.next;
			}
			newNode.next = new Node(o, null);
		}
		size++;
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
		Node node = head;
		if (index != 0) {
			// ���ǵ�һ������ֵ���ҵ�����ֵ��ǰ��һ���ڵ�
			for (int i = 1; i < index; i++) {
				node = node.next;
			}
			Node newNode = new Node(o, node.next);
			node.next = newNode;
			size++;
		} else {
			// ��һ������ֵ�ͽ�ͷ�ڵ�ָ����
			Node newNode = new Node(o, head);
			head = newNode;
			size++;
		}
	}

	public Object get(int index) {
		indexCheck(index);
		Node node = head;
		for (int i = 1; i <= index; i++) {
			node = node.next;
		}
		return node.data;
	}

	public Object remove(int index) {
		indexCheck(index);
		
		Node node = head;
		Node removeNode;
		if (index == 0) {
			//ɾ����һ���ڵ�Ͱ�ͷ�ڵ�ָ��ԭ���ĵڶ����ڵ�
			removeNode = head;
			head = head.next;
		} else {
			//�ҵ�����ֵ��ǰһ���ڵ�
			for (int i = 1; i < index; i++) {
				node = node.next;
			}
			removeNode = node.next;
			//ǰһ���ڵ�ָ�룬ָ��ɾ���ڵ���ָ��Ľڵ�
			node.next = removeNode.next;
		}
		size--;
		return removeNode.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newNode = new Node(o, head.next);
		head.next = newNode;
		size++;
	}

	public void addLast(Object o) {
		add(o);
	}

	public Object removeFirst() {
		//û��Ԫ�ؾ����쳣
		if (size <= 0) {
			throw new IndexOutOfBoundsException("size:" + size);
		}
		Object val = head.data;
		head = head.next;
		size--;
		return val;
	}

	public Object removeLast() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException("size:" + size);
		}
		Node node = head;
		while (node.next != null) {
			node = node.next;
		}
		Object val = node.data;
		node = null;
		size--;
		return val;
	}

	public Iterator iterator() {
		return new MyIterator(this);
	}

	private class MyIterator implements Iterator{
		private int poi = -1;
		private LinkedList list ;
		private MyIterator(LinkedList list) {
			this.list= list;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (poi + 1) < list.size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			poi++;
			if (poi >= list.size) {
				poi--;
				throw new IndexOutOfBoundsException();
			}

			return list.get(poi);
		}

		@Override
		public Object remove() {
			// TODO Auto-generated method stub
			if (poi < 0) {
				throw new NoSuchElementException();
			}
			Object val = list.removeLast();
			poi--;
			return val;
		}
		
	}
	
	private void indexCheck(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}
