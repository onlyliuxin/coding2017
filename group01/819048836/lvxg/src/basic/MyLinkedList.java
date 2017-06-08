package basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ����
 * 
 * @author lvxg
 *
 */
public class MyLinkedList {
	// ͷ�ڵ�
	private Node first;
	// β�ڵ�
	private Node last;
	// Ԫ�ظ���
	private int size = 0;

	public boolean add(Object o) {
		linklast(o);
		return true;
	}
   /**
    * ������������ڵ�
    * @param index
    * @param o
    */
	public void add(int index, Object o) {
		// �ж�index�Ƿ�Խ��
		checkPositionIndex(index);
		if (index == size) {
			linklast(o);
		} else {
			final Node succ = node(index);
			final Node pred = succ.prev;
			Node newNode = new Node(o, pred, succ);
			// ���ǰ��Ϊ��˵����ͷ�ڵ�
			if (pred == null) {
				first = newNode;
			} else {
                   pred.next = newNode;
			}
			size++;
		}
	}
	//��������ɾ���ڵ�
	@SuppressWarnings("unused")
	private Object remove(int index){
		Object x= unllink(node(index));
		return x;
	}
	//ɾ���ڵ㣬�޲�
	@SuppressWarnings("unused")
	private Object remove(){
		Object x = unllink(node(size));
		return x;
	}
  
	// ��ӽڵ㵽����ĩβ
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

	// ɾ���ڵ㷽��
	private Object unllink(Node x) {
		final Object element = x.data;
		final Node next = x.next;
		final Node prev = x.prev;
		// ǰ��Ϊ�գ���ʾɾ������ͷ�ڵ�
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			x.next = null;
			x.prev = null;
		}
		// ���Ϊ�գ���ʾɾ������β�ڵ�
		if (next == null) {
			last = prev;
			x.next = null;
		}
		size--;
		x.data = null;
		return element;
	}

	// ���������õ��ڵ�
	private Node node(int index) {
		if (index < (size >> 1)) { // �������λ����ǰ��Σ�����
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
		Object data; // ������
		Node next;// ���
		Node prev;// ǰ��

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
