package LRU;

/**
 * 使用双向链表实现LRU算法
 * @author 12946
 *
 */

public class LRUPageFrame {
	
	private static class Node {
		
		Node prev = null;
		Node next = null;
		int pageNum;

		Node(Object data) {
			if(data != null){
				this.pageNum = (Integer)data;
			}
		}
	}

	private int capacity;//链表总元素个数
	private int currentSize;//链表当前元素个数
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		first = new Node(null);//栈底，元素最先放入的地方
		last = new Node(null);//栈顶
		
		this.currentSize = 0;
		this.capacity = capacity;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		Node newNode = new Node(pageNum);
		
		if(currentSize == capacity){
			Node tempNode = null;
			if(getIndex(pageNum) == -1){//不包含
				tempNode = first.next;
			}else{//包含
				tempNode = getNode(pageNum);
			}
			(tempNode.prev).next = tempNode.next;
			(tempNode.next).prev = tempNode.prev;
			
			
			newNode.next = last;
			newNode.prev = last.prev;
			last.prev = newNode;
			newNode.prev.next = newNode;
		}
		
		if(currentSize < capacity){
			Node point = first;
			for(int i = 0; i < currentSize; i++){
				point = point.next;
			}
			point.next = newNode;
			newNode.prev = point;
			
			newNode.next = last;
			last.prev = newNode;
			
			currentSize += 1;
		}
		

	}
	
	public Node getNode(int data){
		
		Node point = first.next;
		for(int i = 0; i < capacity; i++){
			if(point.pageNum == data){
				return point;
			}
			point = point.next;
		}
		
		return null;
		
	}
	
	
	public int getIndex(int data){
		Node point = first.next;
		for(int i = 0; i < capacity; i++){
			if(point.pageNum == data){
				return i;
			}
			point = point.next;
		}
		
		return -1;
	}
	
//  原toString	
//	public String toString(){
//		StringBuilder buffer = new StringBuilder();
//		Node node = first;
//		while(node != null){
//			buffer.append(node.pageNum);			
//			
//			node = node.next;
//			if(node != null){
//				buffer.append(",");
//			}
//		}
//		return buffer.toString();
//	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Node point = last.prev;
		while(point != first){
			sb.append(point.pageNum);
			if(point.prev != first){
				sb.append(",");
			}
			point = point.prev;
		}
		
		return sb.toString();
		
	}
	
	

}
