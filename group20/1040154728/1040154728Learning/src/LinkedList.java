public class LinkedList<T> implements List<T> {
	
	private Node head;
	private Node tail;
	private int size;
	
	public void add(T o){
        Node node = new Node(o);
        if(head == null)
        {
            head = node;
        }
        else
        {
            tail.next = node;
        }
        tail = node;
        tail.next = null;
        size++;

	}
	public void add(int index , T o){
		Node node = new Node(o);
		Node temp = head;
		Node tempTemp = null;
		for(int i = 0; i <= index; i++)
        {
            temp = temp.next;
        }
        tempTemp = temp.next;
		temp.next = node;
		node.next = tempTemp;
		size++;
	}
	public T get(int index){
	    Node temp = head;
	    for(int i = 0; i <= index; i++)
        {
            temp = temp.next;
        }
		return (T)temp.data;
	}
	public T remove(int index){
		if(index == 0){
		    T o = (T) removeFirst();
		    return o;
        }
        Node temp = head;
		Node tempTemp = null;
		for(int i = 0; i <= index; i++)
        {
            temp = temp.next;
        }
        T o = (T) temp.next.data;
		tempTemp = temp.next.next;
		temp.next = null;
		temp.next = tempTemp;
		size--;
		return o;
	}
	
	public int size(){
		return size;
	}

    @Override
    public boolean isEmpty() {
        return false;
    }

    public void addFirst(T o){
		Node node = new Node(o);
		node.next = head;
		head = node;
		size++;
	}
	public void addLast(T o){
		this.add(o);
	}
	public T removeFirst(){
		T o = (T) head.data;
		head = head.next;
		size--;
		return o;
	}
	public Object removeLast(){
		Node temp = head;
		for(int i = 0; i <= size; i++)
        {
            temp = temp.next;
        }
        temp.next = null;
		T o = (T) tail.data;
		tail = temp;
		size--;
		return o;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private class Node{
		T data;
		Node next;

        public Node(T o) {
        }
    }
}
