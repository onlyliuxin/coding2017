package data_Structures;


public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	LinkedList()
	{
		size = 0;
	}
	
	public void add(Object o){
		
		Node newNode = new Node(o);
		
		if(head == null)
		{
			head = newNode;
		}
		else
		{
			Node tempNode = head;
			while(tempNode.next != null)
			{
				tempNode = tempNode.next;
			}
			tempNode.next = newNode;
			
		}
		
		size++;
	}
	
	public void add(int index , Object o){
		
		Node newNode = new Node(o, getNode(index));
		getNode(index-1).next = newNode;
		
		size++;

	}
	
	public Node getNode(int index)
	{
		Node tempNode = head;
		int i = 0;
		while(i < index)
		{
			tempNode = tempNode.next;
			i++;
		}
		
		return tempNode;
	}
	

	
	public Object get(int index){
		return getNode(index).data;
	}
	public Object remove(int index){
		
		Node tempNode = getNode(index);
		getNode(index - 1).next = getNode(index+1);
		tempNode.next = null;
		tempNode.data = null;
		size--;
		return getNode(index).data;
		
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		
		Node tempNode = head;
		head = new Node(o);
		head.next = tempNode;
		
		size++;
	}
	public void addLast(Object o){
		add(o);
		
	}
	public Object removeFirst(){
		
		Node tempNode = head;
		head = getNode(1);
		size--;
		
		return tempNode.data;
	}
	public Object removeLast(){
		getNode(size-1).next = null;
		size--;
		return getNode(size).data;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static class Node{
		Object data;
		Node next;
		
		Node(Object data, Node next )
		{
			this.data = data;
			this.next = next;
		}
		
		Node(Object data)
		{
			this.data = data;
			this.next = null;
		}
		
	}
}
