package week4.origin.jvm.loader;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	
	private int capacity;
	private int curSize;//记录当前缓存大小
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		curSize=0;	
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		
		if(capacity <=0 ){
			throw new IndexOutOfBoundsException();
		}
		
		if(curSize == 0){
		    Node node=new Node(null,null,pageNum);
		    curSize++;
		    first=node;
		    last=node;
		    return ;
		}
		
		if(first.pageNum == pageNum){
	    	return ;
	    }
		
		//不管是否需要置换头指针均需要改变
		Node node=new Node(null,first,pageNum);
		first.prev=node;
	    first=node;
	    
	    if(curSize == 1){
	    	last.prev=node;
	    }
	    
		if(curSize < capacity){
			curSize++;
			return ;
		}
		
		if(curSize == capacity){//容量不足,开始置换
			
			//首先判断里面是否存在值相同元素
			Node curNode=first.next;
			while(curNode.next!=null){
				if(curNode.pageNum == pageNum){//如果找到
					curNode.prev.next=curNode.next;
					curNode.next.prev=curNode.prev;
					return ;
				}
				curNode=curNode.next;
			}
			Node tempNode=last.prev;
		    last=tempNode;
		    last.next=null;
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
	
	private static class Node {	
		Node prev;
		Node next;
		int pageNum;
		public Node(Node prev,Node next,int pageNum){
			this.prev=prev;
			this.next=next;
			this.pageNum=pageNum;
		}
	}
}