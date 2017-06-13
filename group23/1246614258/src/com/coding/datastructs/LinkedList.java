package com.coding.datastructs;

public class LinkedList implements List{

	private Node head;
	private int size ;

	public LinkedList() {
		size = 0;
		head=new Node();
	}

	public void add(Object o) {
		Node pnew = new Node();
		pnew.setData(o);
		pnew.next = null;
		if(null==head.getNext()){
			head.setNext(pnew);
			return;
		}
		Node ptr = head.getNext();
		Node ptr1 = new Node();
		
		while (ptr != null) {
			ptr1 = ptr;
			ptr = ptr.getNext();
		}
		ptr1.next = pnew;

	}

	public void add(int index, Object o) {
		size();
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		Node ptr;
		Node ptemp = new Node();
		;
		Node pnew;// 实例化新节点
		ptr = head.getNext();
		int i = 0;
		if (ptr == null && size == 0) {
			System.out.println("插入数据失败！");
		}
		while (ptr != null) {
			ptemp = ptr;
			if (index == 0) {
				pnew = new Node();
				pnew.setData(o);
				pnew.setNext(ptr);
				head.setNext(pnew);
				break;
			}
			if (index == i && index > 0) {
				ptemp = getNode(i-1);
				pnew = new Node();
				pnew.setData(o);
				pnew.setNext(ptr);
				ptemp.setNext(pnew);//
				System.out.println("插入数据" + o + "成功！");
				break;
			}
			ptr = ptr.getNext();
			i++;
		}
		if (ptr == null && size > 0) {
			pnew = new Node();
			pnew.setData(o);
			pnew.setNext(null);
			ptemp.setNext(pnew);//
			System.out.println("插入数据" + o + "成功！");
		}

	}

	public Object get(int index) {
		size();
		if (index > size || index < 0 || index == size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		Node ptr = head.getNext();
		Object tempData = null;
		int i = 0;
		while (ptr != null) {
			if (index == i) {
				tempData = ptr.getData();
			}
			ptr = ptr.getNext();
			i++;
		}

		return tempData;
	}

	public Node getNode(int index) {
		size();
		if (index > size || index < 0 || index == size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		Node ptr = head.getNext();
		Node tempData = null;
		int i = 0;
		while (ptr != null) {
			if (index == i) {
				tempData = ptr;
			}
			ptr = ptr.getNext();
			i++;
		}

		return tempData;
	}

	public Object remove(int index) {
		size();
		if(size()==0){
			throw new NullPointerException("list内部为空，不能删除数据");
		}
		if (index > size || index < 0 || index == size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		Node ptr = head.getNext();
		Node ptrNext = new Node();
		Object tempData = null;
		int i = 0;
		while (ptr != null) {
			if (index == 0) {
				ptrNext = ptr.getNext();
				head.setNext(ptrNext);
				break;
			}
			if (index == i && index > 0) {
				Node ptrprevious = getNode(i - 1);
				tempData = ptr.getData();
				ptrprevious.setNext(ptr.getNext());
				break;
			}
			ptr = ptr.getNext();
			i++;
		}
		return tempData;
	}

	public int size() {
		int i = 0;
		Node ptr = head.getNext();
		while (ptr != null) {
			ptr = ptr.getNext();
			i++;
		}
		size = i;
		return size;
	}

	public void addFirst(Object o) {
		add(0,o);

	}

	public void addLast(Object o) {
		/*Node ptr = head.getNext();
		Node pnew = new Node();
		pnew.setData(o);
		Node ptemp = new Node();
		if (ptr == null) {
			head.setNext(pnew);
		} else {
			while (ptr != null) {
				ptemp = ptr;
				ptr = ptr.getNext();
			}
			if (ptr == null) {
				ptemp.setNext(pnew);
			}
		}*/
		add(size(),o);

	}

	public Object removeFirst() {
		Node ptr = head.getNext();
		Object temp = null;
		if (ptr == null) {
			throw new NullPointerException("LinkedList里面无数据，不能进行删除操作");
		} else {
			temp = ptr.getData();
			remove(0);
		}

		return temp;
	}

	public Object removeLast() {
		size();
		Node ptr = head.getNext();
		Object temp = null;
		if (ptr == null) {
			throw new NullPointerException("LinkedList里面无数据，不能进行删除操作");
		} else {
			temp = remove(size - 1);
		}

		return temp;
	}

	private static class Node {
		Object data;
		Node next;

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

}
