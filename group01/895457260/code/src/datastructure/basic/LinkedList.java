package datastructure.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;

	public LinkedList() {
	    head = new Node();
    }
	
	public void add(Object o){
        addLast(o);
	}

	public void add(int index , Object o){
	    Node pre = findNode(index - 1);
	    Node node = new Node();
	    node.data = o;
	    addNode(node, pre);
	}

	public Object get(int index){
	    checkIndex(index);
		return findNode(index);
	}
	public Object remove(int index){
	    checkIndex(index);
	    Node pre = findNode(index - 1);
	    Node removed = pre.next;
	    removeNode(removed, pre);
		return removed.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node();
		node.data = o;
		addNode(node, head);
	}
	public void addLast(Object o){
        Node node = new Node();
        node.data = o;
        Node pre = findNode(size());
        addNode(node, pre);
	}
	public Object removeFirst(){
	    Node removed = head.next;
		removeNode(head.next, head);
		return removed.data;
	}
	public Object removeLast(){
	    return remove(size() - 1);
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static class Node{
		Object data;
		Node next;
	}

	private Node findNode(int index) {
	    if (index == -1) {
	        return head;
        } else {
	        checkIndex(index);
        }
        Node node = head.next;
        for (int i = 0; i < index; ++i) {
	        node = node.next;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException(indexOutOfBoundMessage(index));
        }
    }

    private String indexOutOfBoundMessage(int index) {
        return "index: " + index + ", size: " + size();
    }

    private void addNode(Node node, Node pre) {
	    node.next = pre.next;
	    pre.next = node;
	    size++;
    }

    private void removeNode(Node node, Node pre) {
	    pre.next = node.next;
	    node.next = null;
	    size--;
    }
}
