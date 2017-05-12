package jvm_LRU_170402.coding.basic.linklist;

/**
 * 用双向链表实现LRU算法
 * 
 * @author liuxin
 *
 */
public class LRUPageFrame {

	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node() {
		}
	}

	private int capacity;

	private Node first;// 链表头
	private Node last;// 链表尾

	public LRUPageFrame(int capacity) {

		this.capacity = capacity;
		first = new Node();
		last = new Node();
		first.next = last;
		last.prev = first;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		Node curNode = findNode(pageNum);
		if (curNode != null) {
			if (pageNum == first.next.pageNum) {
				return;
			}
			popNode(curNode);
			setFirst(curNode);
			return;
		}
		Node newNode = new Node();
		newNode.pageNum = pageNum;
		if (capacity > 0) {
			setFirst(newNode);
			capacity--;
		} else {
			setFirst(newNode);
			popNode(last.prev);			
		}

	}

	private void popNode(Node target) {
		target.prev.next = target.next;
		target.next.prev = target.prev;
	}

	private void setFirst(Node target) {
		target.prev = first;
		target.next = first.next;
		first.next.prev = target;
		first.next = target;
	}

	public Node findNode(int pageNum) {
		Node node = first;
		while (node.next != last) {
			node = node.next;
			if (pageNum == node.pageNum) {
				return node;
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		Node node = first.next;
		while (node != last) {
			buffer.append(node.pageNum);

			node = node.next;
			if (node != last) {
				buffer.append(",");
			}
		}
		return buffer.toString();
	}

}
