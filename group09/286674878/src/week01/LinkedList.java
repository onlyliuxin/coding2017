package week01;



public class LinkedList implements List{
	/**
	 * Class Node is an inner class
	 */
	 class Node{
		private Object data;
		private Node next = null;
		private Node prev = null;
		public Node(Object data){
			this.data = data;
		}
		}	
	private Node head;
	private Node tail;
	private int size = 0;
	
 /**
  * Add an element in the LinkedList
  */
	public boolean add(Object data){
		if(head.next==null){
			addFirst(data);
		}else{
			addLast(data);
		}
		return true;
	}
	/**
	 * Add an element in the LinkedList that with specified location
	 */
	public boolean add(int index , Object data){
		if(index<0 || index>this.size){
			throw new IllegalArgumentException("IllegalArgument"+index);
		}else{
			Node indexnodehere = findIndex(index);
			Node newadd = new Node(data);
			newadd.next = indexnodehere.next;
			newadd.prev = indexnodehere;
			indexnodehere.next.prev = newadd;
			indexnodehere.next = newadd;
			size++;
			}
		return true;
	}
	/**
	 * Find node according index
	 * @param index
	 * @return
	 */
	public Node findIndex(int index){
		Node indexnode = this.head;
		if(index<0 || index>this.size){
			throw new IllegalArgumentException("IllegalArgument"+index);
		}else{
			for(int i=1;i<index;i++){
				indexnode=indexnode.next;
			}
		}
		return indexnode;
	}
	public Object get(int index){
		Node nodeget =findIndex(index);
	    return nodeget.data;
	}
	
	public boolean remove(int index){
		if(index<0 || index>this.size){
			throw new IllegalArgumentException("IllegalArgument"+index);
		}else{
			Node indexnodehere2 = findIndex(index);
			if(indexnodehere2.prev == this.head){
				removeFirst();
			}else if(indexnodehere2.next == this.tail){
				removeLast();
			}else{
				indexnodehere2.prev.next = indexnodehere2.next;
				indexnodehere2.next.prev = indexnodehere2.prev;
				size--;					
			}
			
		}
		return true;
	}
	
	public int size(){
		return this.size;
	}
	/**
	 * Add an new element in the beginning of an LinledList(just after head)
	 * @param data
	 */
	public void addFirst(Object data){
		Node newfirstincrease = new Node(data);
		newfirstincrease.next = head.next;
		head.next = newfirstincrease;
		newfirstincrease.prev = head;
		newfirstincrease.next = tail;
		tail.prev = newfirstincrease;
		size++;
	}
	/**
	 * Add an new element in the tail of an LinkedList
	 * @param data
	 */
	public void addLast(Object data){
		Node newincrease = new Node(data);
		newincrease.next = tail;
		newincrease.prev = tail.prev;
		tail.prev.next = newincrease;
		tail.prev = newincrease;
		size++;
	}
	/**
	 * Delete the first element(node) which just after the head
	 * @return
	 */
	public boolean removeFirst(){
		head.next = head.next.next;
		head.next.next.prev = head;
		size--;
		return true;
	}
	/**
	 * Delete the last element(node) which just before the tail 
	 *  @return
	 */
	public boolean removeLast(){
		tail.prev = tail.prev.prev;
		tail.prev.prev.next= tail;	
		size--;
		return true;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	

}

