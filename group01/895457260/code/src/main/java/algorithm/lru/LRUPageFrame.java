package algorithm.lru;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	private static class List {
		int capacity;
		int size;
		Node head;// 链表头
		Node rear;// 链表尾
		static class Node {
			Node pre;
			Node next;
			int pageNum;
			Node() {}
			void clear() {
				pre = null;
				next = null;
			}
		}
		List(int capacity) {
			this.capacity = capacity;
			head = new Node();
			rear = new Node();
			head.next = rear;
			rear.pre = head;
		}
		void put(int pageNum) {
			Node node = findNode(pageNum);
			if (node == null) {
				if (size >= capacity) {
					remove();
				}
				Node n = new Node();
				n.pageNum = pageNum;
				n.next = rear;
				n.pre = rear.pre;
				n.pre.next = n;
				rear.pre = n;
				size++;
			} else {
				pullUp(node);
			}
		}
		Node findNode(int pageNum) {
			for (Node n = head.next; n != null && n != rear; n = n.next) {
				if (n.pageNum == pageNum) {
					return n;
				}
			}
			return null;
		}
		void remove() {
			if (size == 0) {
				return;
			}
			List.Node node = head.next;
			node.next.pre = head;
			head.next = node.next;
			node.clear();
			size--;
		}
		void pullUp(Node node) {
			node.next.pre = node.pre;
			node.pre.next = node.next;
			node.next = rear;
			node.pre = rear.pre;
			rear.pre.next = node;
			rear.pre = node;
		}
		@Override
		public String toString() {
			StringBuilder buffer = new StringBuilder();
			Node node = rear.pre;
			while(node != null && node != head){
				buffer.append(node.pageNum);

				node = node.pre;
				if(node != null){
					buffer.append(",");
				}
			}
			return buffer.length() == 0 ? "" : buffer.substring(0, buffer.length() - 1);
		}
	}

	private List buf;
	private int capacity;

	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
		buf = new List(capacity);
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param pageNum
	 * @return
	 */
	public void access(int pageNum) {
		buf.put(pageNum);
	}

	@Override
	public String toString() {
		return buf.toString();
	}
}
