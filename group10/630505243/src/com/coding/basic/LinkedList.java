package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;//ͷ�ڵ�
	private Node last;//β�ڵ�
	private int size=0;//����
	
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
			//�ҵ�index��ǰһ�ڵ�
			Node preNode = getNextNode(head, index-1);
			//index��ԭ�нڵ�
			Node node = getNextNode(preNode,index);
			//����indexǰ�ڵ�֮�󣬷���ԭ��index�ڵ�֮ǰ
			preNode.next = new Node(o,node);
		}
		this.size++;
		
	}
	
	public Object get(int index){
		return getNextNode(head,index);
	}
	public Object remove(int index){
		//��ȡɾ�����ǰһ�ڵ�
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
	 * �ݹ�Ѱ����һ�ڵ�
	 * @param node ���սڵ�
	 * @param index ������սڵ�ľ���
	 * @return
	 */
	private Node getNextNode(Node node,int index){
		if(index==0){
			return node;
		}
		Node rtnNode = null;
		if(node.next==null){
			return rtnNode;//���һ���ڵ����һ�ڵ�Ϊ��
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
			//��ȡԭ�����ڶ��ڵ�
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
