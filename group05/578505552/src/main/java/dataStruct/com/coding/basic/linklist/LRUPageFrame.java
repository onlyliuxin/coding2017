package dataStruct.com.coding.basic.linklist;

/**
 * 用双向链表实现LRU算法
 * @author songbao.yang
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
	private int size;
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		this.size = 0;
		first = null;
		last = null;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param pageNum
	 * @return
	 */
	public void access(int pageNum) {

		Node newNode = new Node();
		newNode.pageNum = pageNum;

		//往链表头部加入元素
		if (size == 0){
			first = newNode;
			last = newNode;
		} else {
			newNode.next = first;
			if (first == null){
				System.out.println("fuck");
			}
			first.prev = newNode;
			first = newNode;
		}
		size++;

		//去重
		Node node = first.next;
		while (node != null){
			if (node.pageNum == pageNum){
				node.prev.next = node.next;

				if (node == last){
					last = node.prev;
				} else {
					node.next.prev = node.prev;
				}

				Node tmp = node;
				node = node.next;
				tmp.next = null;
				tmp.prev = null;
				size--;
			} else {
				node = node.next;
			}
		}

		//调整容量
		if (size > capacity){
			last = last.prev;
			last.next.prev = null;
			last.next = null;
			size--;
		}
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
