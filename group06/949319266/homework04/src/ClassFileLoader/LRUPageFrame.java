package ClassFileLoader;

public class LRUPageFrame { 

	private static class Node { 
		Node prev; 
		Node next; 
		int pageNum; 
		Node() { 
		} 
		Node(int pageNum){ 
			this.pageNum = pageNum; 
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
	 */ 
	public void access(int pageNum) { 
		if(first == null){ //缓存第一个数据 
			first = new Node(pageNum); 
			first.next = first.prev = null; 
			last = first; 
		}else if(size() < capacity && first != null){ //缓存页未满时 
			Node node = new Node(pageNum); 
			if(last.prev == null){ 
				last.prev = node; 
				node.next = last; 
			}else{ 
				node.next = first; 
				first.prev = node; 
				node.prev = null; 
			} 
			first = node; 
		}else if(size() >= capacity){ //缓存页满了 
			Node node = getNode(pageNum); 
			LRU(node, pageNum); 
		} 
	} 
	/** 
	 * lru算法 
	 */ 
	private void LRU(Node node, int pageNum){ 
		if(node != null){ 
			if(last.pageNum == node.pageNum){ //缓存是last 
				last = node.prev; 
				last.next = null; 
				node.next = first; 
				first.prev = node; 
				node.prev = null; 
				first = node; 
			}else if(last.pageNum != node.pageNum && first.pageNum != node.pageNum){  
				//缓存在first和last的中间范围 
				node.prev.next = node.next; 
				node.next.prev = node.prev; 
				node.prev = null; 
				node.next = first; 
				first = node; 
			}	 
		}else{ 
			//新缓存 
			last = last.prev; 
			last.next = null; 
			node = new Node(pageNum); 
			node.next = first; 
			first.prev = node; 
			first = node; 
		} 
	} 
	/** 
	 * 根据数据在缓存中获取节点 
	 */ 
	private Node getNode(int pageNum){ 
		Node node = first; 
		while(node != null){ 
			if(node.pageNum == pageNum){ 
				return node; 
			} 
			node = node.next; 
		} 
		return null; 
	} 
	public int size(){ 
		int num = 0; 
		Node node = first; 
		while(node != null){ 
			num++; 
			node = node.next; 
		} 
		return num; 
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
