package cn.mark.work0219;
/**
 * 自定义实现LinkedList数据结构
 * @author hilih
 *
 */
public class MyLinkedList implements MyList{
	
	private Node head;
	private Node last;
	private int size;//集合的长度
	
	public MyLinkedList(){
		this.head = new Node(null);
	}

	/**
	 * 添加元素
	 */
	@Override
	public boolean add(Object o) {
		if (this.last == null){
			this.last = new Node(o);
			this.last.pre = this.head;
			this.last.next = this.last;
		} else {
			Node oldLast = this.last;
			this.last = new Node(o);
		}
		
		return false;
	}

	@Override
	public boolean add(int index, Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}
	
	private static class Node{
		Object data;
		Node pre;
		Node next;
		
		
		Node(Object data){
			this.data = data;
		}
	}


	public static void main(String[] args) {
		

	}

}
