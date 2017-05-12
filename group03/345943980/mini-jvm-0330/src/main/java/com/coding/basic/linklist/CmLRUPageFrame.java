package com.coding.basic.linklist;

/**
 * 
 * @author chenming E-mail:cm_20094020@163.com
 * @version 创建时间：2017年4月10日 上午12:35:03
 */
public class CmLRUPageFrame {

	private static class Node {
		Node prev;
		Node next;
		int pageNum;

		Node() {
		}
	}

	private int capacity;

	private Node first;// 链表头
	private Node last;// 链表尾

	public CmLRUPageFrame(int capacity) {
		this.capacity = capacity;

	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param pageNum
	 */
	public void access(int pageNum) {
		Node node = first;
		Node foundNode = null;
		while(node!=null){
			if(node.pageNum==pageNum){
				foundNode = node;
			}
			node = node.next;
		}
		
		//在该队列中存在， 则提到队列头
		if(foundNode!=null){
			
		}else{
			
		}
		
		
		
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while (node != null) {
			buffer.append(node.pageNum);
			node = node.next;
			if (node != null) {
				buffer.append(";");
			}
		}
		return buffer.toString();
	}

}
