package liuxincourse;


public class LinkedList implements List{
	
	private Node head;
	
	private int size=0;
	
	
	public void add(Object o){
		if (size==0) {
			head=new Node();
			head.data=o;
			size++;
			return;
		}
		Node last=head;
		for (int i = 0; i < size-1; i++) {
			last=last.next;
		}
		Node added=new Node();
		last.next=added;
		added.data=o;
		size++;
	}
	
	public void add(int index,Object o){
		if (index<0||index>size) {
			throw new IndexOutOfBoundsException();
		}
		Node pre=getNode(index-1);
		Node next=getNode(index);
		Node addedNode=new Node();
		addedNode.data=o;
		pre.next=addedNode;
		addedNode.next=next;
		size++;
	}
	
	private Node getNode(int index){
		Node node=head;
		
		for (int i = 0; i < index; i++) {
			node=node.next;
		}
		
		return node;
	}
	
	public Object get(int index){
		if (index<0||index>size-1) {
			throw new IndexOutOfBoundsException();
		}
		if (index==0&&head==null) {
			return null;
		}
		return getNode(index).data;

	}
	
	public Object remove(int index) {
		if (index<0||index>size-1) {
			throw new IndexOutOfBoundsException();
		}
		Node pre=getNode(index-1);
		Node next=getNode(index+1);
		pre.next=next;
		return getNode(index);
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		if (head==null) {
			head=new Node();
			head.data=o;
			size++;
			return;
		}
		Node addNode=new Node();
		addNode.data=o;
		addNode.next=head;
		head=addNode;
		size++;
	}
	
	public void addLast(Object o){
		Node preLast=getNode(size-1);
		Node addNode=new Node();
		addNode.data=o;
		preLast.next=addNode;
		size++;
	}
	
	public Object removeFirst(){
		Node preHead=head;
		head=head.next;
		size--;
		return preHead.data;
	}
	
	public Object removeLast(){
		Node preLast=getNode(size-1);
		Node last=getNode(size-2);
		last.next=null;
		size--;
		return preLast.data;
	}
	
	private static class Node{
		
			Object data;
			Node next;
			
	}

}
