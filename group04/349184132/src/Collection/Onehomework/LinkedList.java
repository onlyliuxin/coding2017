package Collection.Onehomework;

public class LinkedList implements List {
	
	private Node head;
	private Node now ;
	private int size = 0;
	public void add(Object o){
		if(head == null) {
			head = new Node(o, null);
			now = head;
		}
		else{

			Node node = new Node(o,null);
			now.next = node;
			now = node;
		}
		size++;

	}
	private void rangeCheck(int index) {
		if (index < 0 || index > size - 1)
			throw new IllegalArgumentException();
	}
	public void add(int index , Object o){
		rangeCheck(index);

		if(index==0){
			addFirst(o);
		}else {
			Node node = new Node(o,null);
			Node now = head;
			Node next = head;
			for (int i = 1; i <= index; i++) {
				next = next.next;
				if (i == index) {
					node.next = next;
					now.next = node;
				}
				now = now.next;
			}
			size++;
		}
	}

	public Object get(int index){
		Node indexNode = head;
			if(index==0)
				return indexNode.data;
			else {

				for (int i = 1; i <= index; i++) {
					indexNode = indexNode.next;
					if (i == index)
					return indexNode.data;
				}
			}
		return null;
	}
	public Object remove(int index){
		rangeCheck(index);

		if(index == 0){
			return removeFirst();
		}else {
			Node pre = head;
			Node now = head;
			for (int i = 1; i <= index; i++) {
				now = now.next;
				if (i == index) {
					pre.next = now.next;
				}
				pre = pre.next;
			}
			size--;
			return now.data;
		}
	}
	
	public int size(){
		return size ;
	}

	public boolean isEmpty(){ return size==0; }

	public void addFirst(Object o){
		Node oldhead = head;
		Node newhead = new Node(o,oldhead);
		head = newhead;
		size++;
	}
	public void addLast(Object o){
		Node node = head;
		while(node !=null){
			node = node.next;
			if(node==null){
				Node lastnode = new Node(o,null);
				node.next = lastnode;
			}
		}
		size++;
	}
	public Object removeFirst(){
		Node oldhead = head;
		Node newhead = head.next;
		oldhead.next = null;
		head = newhead;
		size--;
		return oldhead.data;
	}
	public Object removeLast(){
		Node node = head;
		Node prev = head;
		while(node !=null){
			node = node.next;
			if(node==null){
				prev.next = null;
			}
			prev = prev.next;
		}
		size--;
		return node.data;
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator{
		int pos = 0;
		@Override
		public boolean hasNext() {
			return pos<size;
		}

		@Override
		public Object next() {
			if(pos>size)
				throw new IllegalArgumentException();
			return get(pos++);
		}
	}
	private static  class Node{
		Object data;
		Node next;
		private Node(Object data,Node next){
			this.data = data;
			this.next = next;

		}

		
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0,15);
		list.add(1,14);
		list.removeLast();
		list.addFirst(1);
		list.removeFirst();
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}
