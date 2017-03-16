package Collection;

import com.coding.basic.List;
import com.coding.basic.Iterator;

public class LinkedList implements List {

	public  Node head;
	public  int size;

	public LinkedList() {
		head = new Node();
		size = 0;
	}

	public LinkedList(Object o) {
		head = new Node(o);
		size = 1;
	}

	public void add(Object o) {
		if (size == 0) {
			addfirst(o);
			return;
		}
		addlast(o);
	}

	public void add(int index, Object o) {
		this.checkCapacity(index);
		if (index == 0) {
			addfirst(o);
			return;
		}
		if (index == size) {
			addlast(o);
			return;
		}
		addmid(index, o);
	}

	public void checkCapacity(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	public void addfirst(Object o) {
		Node tmp = new Node(head);
		head.data = o;
		head.next = tmp;
		size++;
	}

	public void addlast(Object o) {
		Node tmp = new Node(head);
		//Node last = new Node(o);
		//last.data=o;
		for (int i = 0; i < size-1; i++) {
			tmp = tmp.next;
		}

		tmp.next = new Node(o);
		size++;

	}

	public void addmid(int index, Object o) {
		Node tmp = new Node(head);
		Node add = new Node(o);
		for (int i = 0; i < index - 1; i++) {
			tmp = tmp.next;
		}
		add.next = tmp.next;
		tmp.next = add;
		size++;
	}

	public Object get(int index) {
		checkCapacity(index);
		Node tmp = new Node(head);
		if (index == 0) {
			return head;
		}
		for (int i = 0; i < index - 1; i++) {
			tmp = tmp.next;
		}
		return tmp.next;
	}

	public Object remove(int index) {
		checkCapacity(index);
		Node tmp = new Node(head);
		if (index == 0) {
			return removeFirst();
		}
		for (int i = 0; i < index - 1; i++) {
			tmp = tmp.next;
		}
		Node result = new Node(tmp.next);
		tmp.next = result.next;
		return result;
	}

	public int size() {
		return this.size;
	}

	public Object removeFirst() {
		Node tmp = new Node(head);
		head = head.next;
		return tmp;
	}

	public Object removeLast() {
		if (size == 0) {
			return null;
		}
		if (size == 1) {
			Node tmp = new Node(head);
			head = null;
			return tmp;
		}
		Node tmp = new Node(head);
		for (int i = 0; i < size - 2; i++) {
			tmp = tmp.next;
		}
		Node result = new Node(tmp.next);
		tmp.next = result.next;
		return result;
	}

	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append("[");
		Node tmp=new Node(head);
		for(int i=0;i<size;i++){
			Object o =tmp.data;
			sb.append(o.toString());
			sb.append(",");
			tmp=tmp.next;
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
	
	public Iterator iterator() {
		LinkedListIterator lli = new LinkedListIterator(this);
		return lli;
	}

	private class LinkedListIterator implements Iterator {

		private Node pos = new Node();

		public LinkedListIterator(LinkedList ll) {
			pos = ll.head;
		}

		@Override
		public boolean hasNext() {
			if (pos.next != null) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if (hasNext()) {
				pos = pos.next;
				return pos;
			}
			return null;
		}

	}

	private static class Node {
		 Object data;
		 Node next;

		public Node() {
			this.data = null;
			this.next = null;
		}

		public Node(Node n) {
			this.data = n.data;
			this.next = n.next;
		}

		public Node(Object o) {
			this.data = o;
			this.next = null;
		}

		public int hashCode() {
			int result = 17;
			int c = this.data == null ? 0 : this.data.hashCode();
			return result = result * 37 + c;
		}

		public boolean equals(Object obj) {

			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Node)) {
				return false;
			} else {
				Node node = (Node) obj;
				if (node.data.equals(data)) {
					return true;
				}
			}

			return false;
		}
		public String toString(){
			return "data:"+data+"   next:"+next;
		}

	}

}
