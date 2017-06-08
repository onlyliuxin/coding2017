package basic;

//ջ
public class Stack {
	// ͷ�ڵ�
	private Node first;
	// β�ڵ�
	private Node last;
	// Ԫ�ظ���
	private int size = 0;

	// ��ջ
	private void pop() {
       removeLast();
	}

	// ��ջ
	private void push(Object o) {
		addLast(o);
	}
	private boolean isEmpty(){
		if(size==0){
			return true;
		}else{
			return false;
		}
	}
	private int size(){
		return size;
	}
	private void removeLast(){
		final Node f = last.prev;
		last = f;
		f.next =null;
	}
	
	
	private void addLast(Object o){
		final Node f= first;
		final Node l = last;
		final Node newNode = new Node(o, last, null);
		if(f==null){
			first =newNode;
		}else{
			l.next = newNode;
		}
		size++;
	}

	private static class Node {
		Object data; // ������
		Node next;// ���
		Node prev;// ǰ��

		public Node(Object o, Node n, Node p) {
			this.data = o;
			this.prev = p;
			this.next = n;
		}
	}
}
