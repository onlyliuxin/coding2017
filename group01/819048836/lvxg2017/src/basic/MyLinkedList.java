package basic;

import java.util.LinkedList;
import java.util.Queue;

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
	// 元素个数
	private int size = 0;

	public boolean add(Object o) {
		linklast(o);
		return true;
	}
   /**
    * 根据索引插入节点
    * @param index
    * @param o
    */
	public void add(int index, Object o) {
		// 判断index是否越界
		checkPositionIndex(index);
		if (index == size) {
			linklast(o);
		} else {
			final Node succ = node(index);
			final Node pred = succ.prev;
			Node newNode = new Node(o, pred, succ);
			// 如果前驱为空说明是头节点
			if (pred == null) {
				first = newNode;
			} else {
                   pred.next = newNode;
			}
			size++;
		}
	}
	//根据索引删除节点
	@SuppressWarnings("unused")
	private Object remove(int index){
		Object x= unllink(node(index));
		return x;
	}
	//删除节点，无参
	@SuppressWarnings("unused")
	private Object remove(){
		Object x = unllink(node(size));
		return x;
	}
  
	// 添加节点到链表末尾
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

	// 删除节点方法
	private Object unllink(Node x) {
		final Object element = x.data;
		final Node next = x.next;
		final Node prev = x.prev;
		// 前驱为空，表示删除的是头节点
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			x.next = null;
			x.prev = null;
		}
		// 后继为空，表示删除的是尾节点
		if (next == null) {
			last = prev;
			x.next = null;
		}
		size--;
		x.data = null;
		return element;
	}

	// 根据索引得到节点
	private Node node(int index) {
		if (index < (size >> 1)) { // 如果插入位置在前半段？？？
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
		Object data; // 数据域
		Node next;// 后继
		Node prev;// 前驱

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
