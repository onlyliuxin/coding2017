package week04.lru;

/**
 * 用双向链表实现LRU算法
 * @author Hui Zhou
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
	}

	private int capacity;
	private int size;
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
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
		Node node = new Node(pageNum);
		
		if(first == null){
			init(node);
		}
		else if(size == capacity){
			if(node.pageNum == first.pageNum){
				return;
			}
			else if(node.pageNum == first.next.pageNum){
				insertOfMidEqual(node);
			}
			else if(node.pageNum == last.pageNum){
				insert(node);
			}
			else{
				insert(node);
			}
		}
		else{
			addNode(node);
		}
	}

	private void init(Node node) {
		first = node;
		last = first;
		size++;
	}
	
	private void addNode(Node node) {
		node.next = first;
		first.prev = node;
		first = node;
		size++;
	}
	
	private void insert(Node node) {
		last = first.next;
		last.next = null;

		node.next = first;
		first.prev = node;
		first = node;
	}

	private void insertOfMidEqual(Node node) {
		first.next = last;
		last.prev = first;
		
		node.next = first;
		first.prev = node;
		first = node;
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
