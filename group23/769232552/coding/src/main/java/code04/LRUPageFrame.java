package code04;

/**
 * 用双向链表实现LRU算法
 */
public class LRUPageFrame {
	
	private  class Node {
		
		Node prev;
		Node next;
		int pageNum;
		Node() {
		}
	}

	private int size;
	private int capacity;
	
	private Node first;// 链表头
	private Node last;// 链表尾

	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
		this.size = 0;
	}

	/**
	 * 获取缓存中对象
	 * 1、如果缓存中不存在，则直接加入表头
	 * 2、如果缓存中存在，则把该对象移动到表头
	 * @param pageNum
	 * @return
	 */
	public void access(int pageNum) {
		Node node = hasContains(pageNum);
		if(node != null){
			moveToHead(node);
		}else {
			addToHead(pageNum);
		}
	
	}

	/**
	 * 对象是否存在缓存中，如存在则返回该对象在链表中的位置，否则返回null
	 * @return
     */
	private Node hasContains(int key){
		Node node = this.first;
		while (node != null){
			if(node.pageNum == key){
				return node;
			}
			node = node.next;
		}
		return node;
	}

	/**
	 * 对象加入表头，先先判断缓存是否已经满了
	 * @return
     */
	private void addToHead(int key){
		Node node = new Node();
		node.pageNum = key;
		if(size < capacity){
			addFirst(node);
		}else {
			removeLast();
			addFirst(node);
		}

	}


	/**
	 * 对象移动到表头
	 * @return
     */
	private void moveToHead(Node node){
		if(node == first){
			return;
		}
		if(node == last){
			node.next = first;
			first.prev = node;
			first = node;
			last = node.prev;
			last.next = null;
			node.prev = null;
			return;
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = first;
		node.prev = null;
		first.prev = node;
		first = node;
	}

	/**
	 * 删除表尾
	 * @return
     */
	private void removeLast(){
		if(last != null){
			Node newLast = last.prev;
			last.prev = null;
			last = newLast;
			last.next = null;
			size --;
		}
	}
	/**
	 * 添加元素到表头
	 * @return
     */
	private void addFirst(Node node){
		//0个节点
		if(first == null){
			first = node;
			last = node;
			size ++;
			return;
		}
		//一个节点
		else if(first == last){
			first = node;
			first.next = last;
			last.prev = first;
			size ++;
			return;
		}else {
			node.next = first;
			first.prev = node;
			first = node;
			size ++;
		}
	}
	/**
	 * 当前链表空间
	 * @return
     */
	public int getSize() {
		return size;
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
