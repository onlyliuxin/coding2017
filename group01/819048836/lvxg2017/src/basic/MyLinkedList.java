package basic;



/**
 * 链表
 * 
 * @author lvxg
 *
 */
public class MyLinkedList {
	// 头节点
	private Node first;
	// 尾节点
	private Node last;
	// 链表长度
	private int size = 0;

	public boolean add(Object o) {
		linklast(o);
		return true;
	}
   /**
    * 根据索引添加节点
    * @param index
    * @param o
    */
	public void add(int index, Object o) {
		checkPositionIndex(index);
		if (index == size) {
			linklast(o);
		} else {
			final Node succ = node(index);
			final Node pred = succ.prev;
			Node newNode = new Node(o, pred, succ);
			if (pred == null) {
				first = newNode;
			} else {
                   pred.next = newNode;
			}
			size++;
		}
	}
	//根据索引删除节点
	private Object remove(int index){
		Object x= unllink(node(index));
		return x;
	}
	private Object remove(){
		Object x = unllink(node(size));
		return x;
	}
	@SuppressWarnings("unused")
	private Object get(int index)
	{
		Node f = first;
		for (int i = 0; i < index; i++) {
			f = f.next;
		}
		return f.data;
	}
	@SuppressWarnings("unused")
	private int size(){
		return size;
	}
	@SuppressWarnings("unused")
	private void addFirst(Object o){
		Node f= first;
		Node newNode = new Node(o, f, null);
		f.prev = newNode;
		first = newNode;
	}
	@SuppressWarnings("unused")
	private void addLast(Object o){
		Node l  = last;
		Node  newNode = new Node(o, null, l);
		l.next = newNode;
		last = newNode;
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
	}
  
	private void linklast(Object e) {
		final Node l = last;
		final Node newNode = new Node(e, null, l);
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		size++;

	}

	private Object unllink(Node x) {
		final Object element = x.data;
		final Node next = x.next;
		final Node prev = x.prev;
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			x.next = null;
			x.prev = null;
		}
		if (next == null) {
			last = prev;
			x.next = null;
		}
		size--;
		x.data = null;
		return element;
	}

	private Node node(int index) {
		if (index < (size >> 1)) { 
			Node x = first;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		} else {
			Node x = last;
			for (int i = size - 1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	}

	private static class Node {
		Object data; // 节点值
		Node next;// 后继节点
		Node prev;// 前驱节点

		public Node(Object o, Node n, Node p) {
			this.data = o;
			this.prev = p;
			this.next = n;
		}
	}

	private void checkPositionIndex(int index) {
		if (!isPositionIndex(index)) {
			throw new IndexOutOfBoundsException();
		}
	}

	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size;
	}

	public static void main(String[] args) {
		MyLinkedList l = new  MyLinkedList();
		l.add("1");
		l.add("2");
		l.add("3");
		l.add(2, "4");    
		l.remove();
		l.remove(1);
	}

}
