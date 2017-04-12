package com.coding.basic.linklist;

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

		Node(Node prev,Node next,int pageNum) {
			this.prev=prev;
			this.next=next;
			this.pageNum=pageNum;
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
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		if(this.first==null){
			first=new Node(null,null,pageNum);
			return ;
		}
		else{
			 if(last==null){
				if(!moveToFirst(pageNum)){ 
					Node nf=new Node(null,this.first,pageNum);
					this.first.prev=nf;
					this.first=nf;
					//判断是否为最后一个
					Node cur=this.first;
					int i=1;
					while(cur!=null){
						cur=cur.next;
						i++;
						if(i==this.capacity){
							this.last=cur;
							break;
						}
					}
				}
			}
			else{
				if(!moveToFirst(pageNum)){
					Node nf=new Node(null,this.first,pageNum);
					this.first.prev=nf;
					this.first=nf;
					this.last.prev.next=null;
					this.last=this.last.prev;
				}
			}
		}
	
	}
	public boolean moveToFirst(int pageNum){
		Node indexOf=indexOf(pageNum);
		if(indexOf!=null){
			if(indexOf==this.first){
				return true;
			}
			else if(indexOf==this.last){
				this.first.prev=indexOf;
				this.last=indexOf.prev;
				this.last.next=null;
				indexOf.next=this.first;
				this.first=indexOf;
			}
			else{
				indexOf.next.prev=indexOf.prev;  
				indexOf.prev.next=indexOf.next;
				this.first.prev=indexOf;
				indexOf.prev=null;
				indexOf.next=this.first;
				this.first=indexOf;
			}
			return true;
		}
		return false;
	}
	public Node indexOf(int pageNum){
		Node cur=this.first;
		while(cur!=null){
			if(cur.pageNum==pageNum){
				return cur;
			}
			cur=cur.next;
		}
		return null;
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
