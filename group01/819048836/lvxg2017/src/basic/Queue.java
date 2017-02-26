package basic;

public class Queue {
	private Node first;
	private Node last;
	private int size = 0;

	//入列
	@SuppressWarnings("unused")
	private void enQueue(Object o) {
		final Node f = first;
		final Node newNode = new Node(o, f, null);
		f.prev = newNode;
	}
	public int size(){
		return size;
	}
public boolean isEmpty(){
	return size>=0;
}
	// 出列
	@SuppressWarnings("unused")
	private Object deQueue() {
		final Node l = last;
		final Node p = l.prev;
		last = p;
		p.next = null;
		return l;
	}

	private static class Node {
		Object data; 
		Node next;
		Node prev;

		public Node(Object o, Node n, Node p) {
			this.data = o;
			this.prev = p;
			this.next = n;
		}
	}

}
