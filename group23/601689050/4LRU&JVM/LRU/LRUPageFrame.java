/**
 * 用双向链表实现LRU算法
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
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param
	 * @return
	 */
	int size = 0;
	public void access(int pageNum) {

		if(size == 0){
			Node node = new Node();
			node.pageNum = pageNum;
			node.prev = node.next = null;
			first = last = node;
			size++;
		}else{
			if(first.pageNum == pageNum){
				return;
			}
			if(size == 1){
				addToFirst(pageNum);
			}else {
				if (last.pageNum == pageNum) {
					moveLastToFirst();
					return;
				}
				Node iteratorNode = first;
				while (iteratorNode.next != null && iteratorNode.pageNum != pageNum) {
					iteratorNode = iteratorNode.next;
				}
				if (iteratorNode == last) {
					addToFirst(pageNum);
					if (size > capacity) {
						last = last.prev;
						last.next = null;
					}
				} else {
				moveToFirst(iteratorNode);
			}
			}
		}
	
	}

	private void moveLastToFirst() {
		last.next = first;
		first.prev = last;
		first = last;
		last = last.prev;
		last.next = null;
		first.prev = null;
	}
	private void moveToFirst(Node iteratorNode){
		iteratorNode.prev.next = iteratorNode.next;
		iteratorNode.next.prev = iteratorNode.prev;
		iteratorNode.prev = null;
		iteratorNode.next = first;
		first.prev = iteratorNode;
		first = iteratorNode;
	}

	private void addToFirst(int pageNum) {
		Node node = new Node();
		node.pageNum = pageNum;
		node.prev = null;
		node.next = first;
		first.prev = node;
		first = node;
		size++;
	}


	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node != null){
			buffer.append(node.pageNum);			
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
}
