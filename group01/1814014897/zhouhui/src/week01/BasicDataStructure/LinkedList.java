package week01.BasicDataStructure;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		if(head == null){
			head = new Node(o);
		}else{
			Node pos = head;
			while(pos.next != null){
				pos = pos.next;
			}
			pos.next = new Node(o);
		}
		size++;
	}
	
	public void add(int index , Object o){
		if(index < 0 || index >size ) throw new IndexOutOfBoundsException("Index:"+index+",Size"+size);
		if(index == 0) {
			Node node = new Node(o);
			node.next = head;
			head = node;
		}
		else{
		Node pos = head;
		for(int i = 0;i < index-1;i++){
			pos = pos.next;
		}
		Node node = new Node(o);
		node.next = pos.next;
		pos.next = node;
		}
		size++;
	}
	
	public Object get(int index){
		if(index < 0 || index >=size ) throw new IndexOutOfBoundsException("Index:"+index+",Size"+size);
		Node pos = head;
		for(int i = 0;i < index;i++){
			pos = pos.next;
		}
		return pos.data;	
	}
	
	public Object remove(int index){
		if(index < 0 || index >=size ) throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
		Node element = head;
		if(index == 0){
			head = head.next;
		}else{
			Node pos = head;
		for(int i = 0;i < index - 1;i++){
				pos = pos.next;
			}
		element = pos.next;
		pos.next = pos.next.next;
		}
		size--;
		return element.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0,o);
	}
	public void addLast(Object o){
		add(size,o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	class LinkedListIterator implements Iterator{

		private Node node = head;
		private int pos = 0;
		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public Object next() {
			pos++;
			if(pos != 1){
				node = node.next;
			}
			return node.data;	
		}	
	}
		
	private static  class Node{
		Object data;
		Node next;
		public Node(Object data){
			this.data = data;
			next = null;
		}
	}
}
