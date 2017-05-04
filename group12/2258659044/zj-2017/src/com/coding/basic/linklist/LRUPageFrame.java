package com.coding.basic.linklist;


/**
 * 用双向链表实现LRU算法
 * @author ZJ
 *
 */
public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		Object obj;

		Node() {
		}
	}

	private int capacity;//容量
	
	private int size;//已经存放的数量
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
	public void access(Object obj) {
		
		if(obj == null){
			return;
		}
		Node node = getNode(obj);
		if(node!=null){
			move2head(node);
		}else{
			refresh(obj);
		}			
	}

	/**
	 * 刷新LRU队列
	 * @param obj
	 */
	private void refresh(Object obj) {
		//添加元素
		if(size<capacity){
			add(obj);
		}else{
			remove();
			add(obj);
		}
	}
	
	/**
	 * 添加
	 * @param obj
	 */
	private void add(Object obj){
		
		Node node = new Node();
		node.obj = obj;
		if(first == null){
			first = node;
			last = node;
		}else{
			node.next = first;
			node.next.prev = node;
			first = node;
		}
		size++;
	}

	/**
	 * 删除
	 * @return
	 */
	private Object remove(){
		
		Object obj = last.obj;
		last = last.prev;
		last.next = null;
		size --;
		return obj;
	}
	
	/**
	 * 是否存在缓存中
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isExist(Object obj){
		
		return getNode(obj)!=null?true:false;
	}
	
	/**
	 * 获取包含值为obj的节点
	 * @param obj
	 * @return
	 */
	private Node getNode(Object obj){
		
		for (Node node = first; node != null; node = node.next) {
			if(node.obj.equals(obj)){
				return node;
			}
		}
		return null;
	}
	
	/**
	 * 将节点置为头结点
	 * @param obj
	 */
	private void move2head(Node node){
		
		if(node != null){
			if(node.equals(first)){//该节点为头结点
				return;
			}else if(node.equals(last)){//该节点为尾节点				
				last = last.prev;
				last.next = null;
			}else{
				node.prev.next = node.next;
				node.next.prev = node.prev;								
			}
			//将本节点置为头结点
			first.prev = node;
			node.next = first;
			first = node;
		}
		
	}
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node != null){
			buffer.append(node.obj);			
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
}