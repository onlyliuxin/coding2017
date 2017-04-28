package com.pan.linklist;


/*
 * 用双向链表实现LRU算法
 */
public class LRUPageFrame {
	private static class Node{
		Node prev;
		Node next;
		int pageNum = -1;// 物理页

		Node(){

		}
	}

	private int capacity;

	private Node first;// 链表头
	private Node last;// 链表尾
	boolean tag = false;

	public LRUPageFrame(int capacity){
		this.capacity = capacity;

		for(int i = 0; i < capacity; i++){
			Node curNode = new Node();
			if(null == first){
				last = first = curNode;
			}else{
				last.next = curNode;
				curNode.prev = last;
				last = last.next;
			}
			last.next = null;
		}
	}
	public void printList(){
		Node curNode = first;
		while(curNode != null){
			curNode = curNode.next;
		}
	}
	/*
	 *  获取缓存中对象
	 *  @param key
	 *  @return
	 */
	public void access(int pageNum){
		printList();
		Node index = findLogicPage(pageNum);
		modifyPhysicalPage(index,pageNum);
	}

	/*
	 * @param pageNum 表示要查询的逻辑页面
	 * @return 若在物理页中找到要查询的逻辑页面，则返回该物理页节点的引用，否则返回null
	 */
	public Node findLogicPage(int pageNum){

		Node index = null;
		Node curNode = first;
		while(curNode != null){
			if(curNode.pageNum == pageNum){
				index = curNode;
				tag = true;
			}
			curNode = curNode.next;
		}
		return index;
	}
	/*
	 * @prama index 代表了 有逻辑页的物理页的节点的引用
	 */
	public void modifyPhysicalPage(Node index,int pageNum){
		push(pageNum,index);
	}
	/*
	 *  @param pageNum 要 push的逻辑页面，  默认栈顶是 first, bottom 栈底  指定了栈的大小
	 */
	public void push(int pageNum,Node bottom){
		Node index = checkWhichListNodeNotUsed();
		if(index != null){
			index.pageNum = pageNum;
			return;
		}

		Node lastNode;
		if(null == bottom){
			lastNode = last;
		}else{
			lastNode = bottom;
		}
		Node curNode = lastNode.prev;
		while(curNode != null){
			lastNode.pageNum = curNode.pageNum;
			lastNode = curNode;
			curNode = curNode.prev;
		}
		lastNode.pageNum = pageNum;
		return;
	}

	/*
	 * @return 返回物理页中 pageNum 没有被使用的节点的引用(返回栈中最下面的)，如果全部都被使用，则返回 null
	 */
	public Node checkWhichListNodeNotUsed(){
		Node node = first;
		Node index = null;
		while(node != null){
			if(node.pageNum == -1){
				index = node;
			}
			node = node.next;
		}
		return index;
	}

	public String toString(){
		StringBuffer buffer = new StringBuffer();
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
