

public class LinkedList {
	
	private Node head = new Node();
	private int size = 0;
	
	public void add(Object o){
		Node tem = new Node();
		tem.data = o;
		tem.next = null;
		Node temp = head;
		int i = 0;
		while(i<size){
			temp = temp.next;
			++i;
		}
		temp.next = tem;
		size++;
		
	}
	public void add(int index , Object o){
		if(index <=0 || index >size+1)
			throw new IndexOutOfBoundsException();
		Node tem = new Node();
		tem.data = o;
		tem.next = null;
		++size;
		Node temp = head;
		int i = 0;
		while(i<index-1){
			temp = temp.next;
			++i;
		}
		tem.next=temp.next;
		temp.next = tem;
		
	}
	public Object get(int index){
		Node temp = head;
		int i = 0;
		while(i<index){
			temp = temp.next;
			++i;
		}
		return temp.data;
	}
	public Object remove(int index){
		if(index <=0 || index >size+1)
			throw new IndexOutOfBoundsException();
		--size;
		Node temp = head;
		int i = 0;
		while(i<index-1){
			temp = temp.next;
			++i;
		}
		Object t = temp.next.data;
		temp.next=temp.next.next;
		return t;
		
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node tem = new Node();
		tem.data = o;
		tem.next = null;
		head.next = tem;
		size++;
		
	}
	public void addLast(Object o){
		Node tem = new Node();
		tem.data = o;
		tem.next = null;
		Node temp = head;
		int i = 0;
		while(i<size){
			temp = temp.next;
			++i;
		}
		temp.next = tem;
		size++;
		
	}
	public Object removeFirst(){
		Object t = head.next.data;
		head.next=head.next.next;
		size--;
		return t;
	}
	public Object removeLast(){
		Node temp = head;
		int i = 0;
		while(i<size-1){
			temp = temp.next;
			++i;
		}
		Object t = temp.next.data;
		temp.next = null;
		size--;
		return t;
		
	}
	public static void main(String []args){
		LinkedList l = new LinkedList();
		l.addFirst("c");
		l.add("a");
		l.add(1,"b");//这里的1是第一个，而不是序号
		l.add("123");
		System.out.println(l.removeFirst());
		System.out.println(l.remove(2));
		System.out.println(l.removeLast());
	}

	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
}
