package week04.lru;

public class LRUPageFrame {
	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node(Node prev, Node next, int pageNum) {
			this.prev = prev;
			this.next = next;
			this.pageNum = pageNum;
		}
	}

	private int capacity;

	private Node first;// 链表头
	private Node last;// 链表尾
	private int size; // 链表长度

	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		int index = find(pageNum);
		if (size != 0) {
			if(index >= 0){
				remove(index);
			}else if(size == capacity){
				remove(size - 1);
			}
		}
		addToHead(pageNum);
	}

	public void remove(int index) {
		if (index == 0) {
			if(size == 1){
				first = last = null;
			}else{
				first = first.next;
				first.prev = null;
			}
		} else if (index == (size - 1)) {
			if(size == 1){
				first = last = null;
			}else{
				last = last.prev;
				last.next = null;
			}
		} else {
			Node node = first;
			for (int i = 1; i < index; i++) {
				node = node.next;
			}
			
			Node nxt = node.next;
			
			node.next = nxt.next;
			(nxt.next).prev = node;
			nxt = null;
		}
		size--;
	}

	public int find(int pageNum) {
		int index = 0;
		Node cur = first;
		while (cur != null) {
			if (pageNum == cur.pageNum) {
				return index;
			}
			cur = cur.next;
			index++;
		}
		return -1;
	}

	public void addToHead(int pageNum) {
		// 链表为空
		if (first == null) {
			Node node = new Node(null, null, pageNum);
			first = node;
			last = node;
		} else {
			Node node = new Node(null,first,pageNum);
			first.prev = node;
			first = node;
		}
		size ++;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while (node != null) {
			buffer.append(node.pageNum);

			node = node.next;
			if (node != null) {
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
}
