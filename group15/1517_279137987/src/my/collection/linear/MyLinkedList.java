package my.collection.linear;

public class MyLinkedList implements MyList {
	
	private Node head;
	
	private int size = 0;

	public void add(Object obj) {
		add(this.size, obj);
	}

	public void add(int index, Object obj) {
		Node curNode = head;
		Node addNode = new Node(obj);
		if(index == 0){
			addNode.next = head;
			head = addNode;
		}else{
			for(int i=0; i<index-1; i++){
				curNode = curNode.next;
			}
			addNode.next = curNode.next;
			curNode.next = addNode;
		}
		size++;
	}

	public Object remove(int index) {
		Node curNode = head;
		Node remNode = new Node();
		if(index == 0){
			remNode = head;
			head = head.next;
		}else{
			for(int i=0; i<index-1; i++){
				curNode = curNode.next;
			}
			curNode.next = curNode.next.next;
		}
		size--;
		return remNode;
	}

	public int size() {
		return this.size;
	}
	
	public Object get(int index) {
		Node curNode = head;
		for(int i=0; i<index; i++){
			curNode = curNode.next;
		}
		return curNode.data;
	}
	
	public void addFirst(Object obj){
		add(0,obj);
	}
	
	public void addLast(Object obj){
		add(size,obj);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size-1);
	}
	
	public String toString(){
		Node curNode = head;
		String str = "\n" + "toString():";
		while(curNode != null){
			str += curNode.data + "\t";
			curNode = curNode.next;
		}
		return str;
	}
	
	private static class Node{
		Object data;
		Node next;
		
		private Node(){
		}
		
		private Node(Object obj){
			this.data = obj;
			this.next = null;
		}
	}
}
