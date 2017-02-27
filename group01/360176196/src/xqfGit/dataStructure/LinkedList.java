package xqfGit.dataStructure;

public class LinkedList implements List {
	
	private Node first;
	private Node last;
	private int  size;
	
	public void add(Object o){
		Node l = new Node(o);
		l = last.next;
		size++;
	}
	
	public void add(int index , Object o){
		Node l = new Node(o);
		Node n = first;
		if(size == index){
			l = last.next;
			l = last;
			size++;
		}else{
			Node m = first;
			for(int i =0;i<index-1;i++){
				m = m.next;
			}
			for(int i =0;i<index;i++){
				n = n.next;
			}
			l = m.next;
			n = l.next;
			size++;
		}
	}
	public Object get(int index){
		Node n = first;
		for(int i =0;i<index;i++){
			n = n.next;
		}
		return n;
	}
	public Object remove(int index){
		Node n = first;
		for(int i =0;i<index-1;i++){
			n = n.next;
		}
		Node m = first;
		for(int i =0;i<index;i++){
			m = m.next;
		}
		Node l = first;
		for(int i =0;i<index+1;i++){
			l = l.next;
		}
		l = n.next;
		size--;
		return m;
	}
	
	public int size(){
	  Node n = first;
	  int i=0;
		for(i =0;i<size;i++){
			n = n.next;
		}
		return i;
	}
	
	public void addFirst(Object o){
		Node n = new Node(o);
		first = n.next;	
		size++;
	}
	public void addLast(Object o){
		Node n = first;
		for(int i =0;i<size;i++){
			n = n.next;
		}
		n = new Node(o);
		last = n;
		size++;
	}
	public Object removeFirst(){
		Node n = first.next;
		Node m = first;
		first = n;
		size--;
		return m;
	}
	public Object removeLast(){
	  Node n = first;
	  for(int i =0;i<size-1;i++){
		  n = n.next;
	  }
	  last = n;
	  size--;
	  return n.next;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
	  
	  public Node(){
		  
	  }
	  public Node(Object o ){
			this.data = o;
		}
	}
}
