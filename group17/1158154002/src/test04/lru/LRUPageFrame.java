package test04.lru;

/**
 * 用双向链表实现LRU算法
 * @author 
 *
 */
public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;
		Node() {}
		
		Node(Node prev,Node next,int pageNum){
			this.prev=prev;;
			this.next=next;
			this.pageNum=pageNum;
		}
	}

	private int capacity;
	private int currentSize;
	
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
		if (currentSize<capacity) {
			if(last==null){
				last=new Node();
				last.pageNum=pageNum;
			}else{
				addToFirst(pageNum);
			}
			currentSize++;
		} else {
			Node node=last;
			while (node!=null&&node.pageNum!=pageNum) {
				node=node.prev;
			}
			//没有找到,添加到头部，删除最后一个,找到了并且不是第一个删除当前节点，添加到头部
			if (node==null) {
				addToFirst(pageNum);
				removeLast();
			}else if(node!=first) {				
				addToFirst(pageNum);
				remove(node);
			}

		}


	}

	private void remove(Node node){
			if (node.next!=null&&node.prev!=null) {
				node.prev.next=node.next;
				node.next.prev=node.prev;
				node=null;
			} else if(node.next==null){
				removeLast();
			}
	}
	
	private void removeLast(){
		Node temp=last.prev;
		temp.next=null;
		last=temp;
}
	
	private void addToFirst(int pageNum) {
		Node next=last;
		while (next.prev!=null) {
			next=next.prev;
		}
		first=new Node(null, next, pageNum);
		next.prev=first;
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