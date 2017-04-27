package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;//头节点
	private Node last;//尾节点
	private int size=0;//长度
	
	public void add(Object o){
		if(last==null){
			head = new Node(o, null);
			last = head;
		}else{
			Node nod = last;
			nod.next = new Node(o,null);
			last = nod.next;
		}
		size++;
	}
	public void add(int index , Object o){
		if(last==null){
			head = new Node(o,null);
			last = head;
		}else{
			//找到index的前一节点
			Node preNode = getNextNode(head, index-1);
			//index的原有节点
			Node node = getNextNode(preNode,index);
			//放在index前节点之后，放在原有index节点之前
			preNode.next = new Node(o,node);
		}
		this.size++;
		
	}
	
	public Object get(int index){
		return getNextNode(head,index);
	}
	public Object remove(int index){
		//获取删除点的前一节点
		Node preNode = getNextNode(head,index-1);
		Node node = getNextNode(head,index);
		if(node.next!=null){
			preNode.next = getNextNode(head,index+1);
		}else{
			preNode.next = null;
		}
		this.size--;
		return node;
	}
	
	/**
	 * 递归寻找下一节点
	 * @param node 参照节点
	 * @param index 距离参照节点的距离
	 * @return
	 */
	private Node getNextNode(Node node,int index){
		if(index==0){
			return node;
		}
		Node rtnNode = null;
		if(node.next==null){
			return rtnNode;//最后一个节点的下一节点为空
		}else{
			return getNextNode(node.next,index-1);
		}
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o,head);
		this.head = node;
		this.size++;
	}
	public void addLast(Object o){
		Node newLast = new Node(o, null);
		Node node = last;
		node.next = newLast;
		last = newLast;
		this.size++;
	}
	public Object removeFirst(){
		if(head==null){
			return null;
		}else{
			if(head.next==null){
				head.data = null;
				size--;
				return head;
			}else{
				Node node = head;
				head = node.next;
				size--;
				return node;
			}
		}
	}
	public Object removeLast(){
		if(last==null){
			return null;
		}else{
			//获取原倒数第二节点
			Node preLast = getNextNode(head, size-2);
			Node orgLast = last;
			preLast.next = null;
			last = preLast;
			size--;
			return orgLast;
		}
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		public Node(Object o,Node next){
			this.data = o;
			this.next = next;
		}
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append("data = "+this.data);
			if(next!=null){
				sb.append(" ; next = "+this.next.data+" \n");
			}else{
				sb.append(" ; next = null");
			}
			sb.append("]");
			return sb.toString();
		}
		
		
	}
}
