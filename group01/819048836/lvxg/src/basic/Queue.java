package basic;

public class Queue {
	// 头节点
	private Node first;
	// 尾节点
	private Node last;
	// 元素个数
	private int size = 0;

	// 入列
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
	private Object deQueue() {
		final Node l = last;
		final Node p = l.prev;
		last = p;
		p.next = null;
		return l;
	}

	private static class Node {
		Object data; // 数据域
		Node next;// 后继
		Node prev;// 前驱

		public Node(Object o, Node n, Node p) {
			this.data = o;
			this.prev = p;
			this.next = n;
		}
	}

}
