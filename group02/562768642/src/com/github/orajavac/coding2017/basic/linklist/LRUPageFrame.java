package com.github.orajavac.coding2017.basic.linklist;

public class LRUPageFrame {
private static class Node {
		
		Node prev;
		Node next;
		int pageNum=10000;
		String flag;

		Node() {
		}
	}

	private int capacity;
	private int length;
	
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		
		first = new Node();
		last = new Node();
		last.flag = "last";		//用来标识最后一个节点是链表尾，在这里链表尾并不算做内存页
		first.flag = "first";	//用来标识链表头，在这里链表头并不算做内存页
		first.next = last;
		last.prev=first;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		if(lookup(pageNum)){
			;
		}else{
			if (length<capacity){
				addFirst(pageNum);
			}else{
				remove(pageNum);	//删除尾元素
				addFirst(pageNum);
			}
		}
	}
	
	public boolean lookup(int pageNum){
		Node nodef = first;
		Node nodel = last;
		
		//判断要找的元素是否在第一个节点里
		if (nodef.next.pageNum == pageNum){
			return true;
		}
		
		//判断要找的元素是否在最后一个节点里
		if (nodel.prev.pageNum == pageNum){
			remove(pageNum);	//删除尾元素
			addFirst(pageNum);	//把访问pageNum添加到第一个节点里
			return true;
		}
		
		while(nodef.next!=null&&nodef.next.pageNum != 999){
			if (nodef.next.pageNum == pageNum){
				nodef.next = nodef.next.next;	//先删除元素
				nodef.next.prev = nodef;
				addFirst(pageNum);	//再把访问添加元素
				return true;
			}
			nodef = nodef.next;
		}
		return false;
	}
	
	public void remove(int pageNum){
		length--;
		Node n = last.prev;
		last.prev = n.prev;
		n.prev.next = last;
	}
	
	public void addFirst(int pageNum){
		Node s = first.next;
		Node n = new Node();
		n.pageNum=pageNum;
		n.next=s;
		s.prev=n;
		first.next=n;
		length++;
	}

	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node.next.flag != "last"){
			buffer.append(node.next.pageNum);			
			
			node = node.next;
			
			if(node.next.flag != "last"){
				buffer.append(",");
			}
			
		}
		return buffer.toString();
	}
	
	/**
	 * 测试双向链表逆序输出
	 * @return
	 */
	public String lastToString(){
		StringBuilder buffer = new StringBuilder();
		Node node = last;
		while(node.prev!=null&&node.prev.flag != "first"){
			buffer.append(node.prev.pageNum);			
			
			node = node.prev;
			
			if(node.prev!=null&&node.prev.flag != "first"){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
}
