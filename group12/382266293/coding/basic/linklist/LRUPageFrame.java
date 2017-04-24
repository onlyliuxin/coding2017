package linklist;

/**
 * 用双向链表实现LRU算法
 *
 *
 */
public class LRUPageFrame {

	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node(int pageNum) {
			this.pageNum = pageNum;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + pageNum;
			return result;
		}

		@Override
		public String toString() {
			return "Node [pageNum=" + pageNum + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (pageNum != other.pageNum)
				return false;
			return true;
		}

	}

	private int capacity;

	private Node first;// 链表头
	private Node last;// 链表尾

	public LRUPageFrame(int capacity) {

		this.capacity = capacity;

	}

	private int size;

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		if (size < this.capacity) {
			addNode(pageNum);
			return;
		}

		Node p = findNode(pageNum);
		if (null == p) {
			p = new Node(pageNum);
			p.next = first;
			first.prev = p;
			first = p;
			moveLastPoint();
			return;
		}

		if (p == first) {
			return;
		}

		if (p == last) {
			p.next = first;
			first.prev = p;
			first = p;
			moveLastPoint();
			return;
		}

		movePtoFirst(p);

	}

	private void moveLastPoint() {
		last = last.prev;
		last.next = null;
	}

	private void movePtoFirst(Node p) {
		p.prev.next = p.next;
		p.next.prev = p.prev;
		first.prev = p;
		p.next = first;
		first = p;
	}

	private void addNode(int pageNum) {
		Node node = new Node(pageNum);
		if (null == first) {
			first = node;
			size++;
			return;
		}

		node.next = first;
		first.prev = node;
		first = node;
		size++;

		if (null == last) {
			last = node.next;
			return;
		}

	}

	private Node findNode(int pageNum) {
		Node node = first;
		while (null != node) {
			if (node.pageNum != pageNum) {
				node = node.next;
			} else {
				return node;
			}
		}
		return null;
	}

	public static void main(String[] args) {

	}

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
