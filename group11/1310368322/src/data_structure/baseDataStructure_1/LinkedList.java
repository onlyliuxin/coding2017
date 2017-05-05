package Day_2017_2_26_FirstHomework;

public class LinkedList{
	private Node head;
	static int size = 0;
	public void add(Object o){
		if(null == head){
			head = new Node();
			head.data = o;
			head.next = null;
		}else{
			Node p = head;
			while(null != p.next){
				p = p.next;
			}
			Node newNode = new Node();
			newNode.data = o;
			p.next = newNode;
			newNode.next =null;
		}
		size++;
	}
	public int size(){
		return size;
	}
	public void add(int index,Object o){
		if(index < 0){
			throw new RuntimeException("下标不能为负数");
		}
		if(index == 0){
			addFirst(o);
			size++;
			return;
		}
		if(index > size){
			throw new RuntimeException("");
		}
		int i = 0;
		Node p = head;
		Node q = null;

		while(i!=index){
			q = p;
			p = p.next;
			i++;
		}
		Node r = new Node();
		r.data = o;
		r.next =null;
		q.next = r;
		r.next = p;
		size++;
		return;
	}
	
	public Object get(int index){
		int i = 0;
		Node p = head;
		while(i != index){
			p = p.next;
			i++;
		}
		return p.data;
	}
	public Object remove(int index){
		if(index < 0){
			throw new RuntimeException("下标不能为负数");
		}
		if(index == 1){
			size--;
			return head.data;
		}
		int i = 0;
		Node p = head;
		Node q = null;
		while(i != index){
			q = p;
			p = p.next;
			i++;
		}
		q.next = p.next;
		size--;
		return p.data;
	}
	public void addFirst(Object o){
		Node p = new Node();
		p.next = head;
		p.data = o;
		head = p;
		size++;
	}
	public Object removeFirst(){
		head = head.next;
		size--;
		return null;
	}
	public static class Node{
		Object data;
		Node next;
	}

	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.add("a");
		linkedList.add("b");
		linkedList.add("c");
		linkedList.add("d");
		linkedList.add(5, "f");
		System.out.println(linkedList.get(5));
		System.out.println(linkedList.size());		
	}

}