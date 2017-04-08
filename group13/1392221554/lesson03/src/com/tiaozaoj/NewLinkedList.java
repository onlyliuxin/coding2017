package com.tiaozaoj;

import java.util.Iterator;

import sun.net.www.content.text.plain;

public class NewLinkedList {
	
	private Node head;
	private int size = 0;
	
	//新建链表
	public NewLinkedList(){
		head = new Node("0x666");
		head.next = null;
	}
	
	//添加新节点
	public void add(Object o){
		Node newNode = new Node(o);
		newNode.next = null;
		Node p = head.next;
		while(p.next != null){
			p = p.next;
		}
		p.next = newNode;
		this.size++;
	}
	
	//判断index
	private void verifyIndex(int index){
		try{
			if(index<0 || index>size)
				throw new Exception("越界异常");
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
	
	public void add(int index,Object o){
		this.verifyIndex(index);
		int j = -1;
		Node newNode = new Node(o);
		//遍历结点
		for(Node p = head.next;p.next != null;p = p.next){
			if((index) == j+1){
				Node q = p.next;
				p.next = newNode;
				newNode.next = q;
				break;
			}
			j++;
		}
		this.size++;
	}
	
	//获取节点
	public Object get(int index){
		this.verifyIndex(index);
		int j = 0;
		//遍历结点
		Node p = head.next;
		for(;p.next != null;p = p.next){
			if((index) == j){
				break;
			}
			j++;
		}
		return p;
	}
	
	//删除节点
	public Object remove(int index){
		this.verifyIndex(index);
		int j = -1;
		//遍历结点
		Node p = head.next;
		for(;p.next != null;p = p.next){
			if((index) == j+1){
				break;
			}
			j++;
		}
		Node toRemoveNode = p.next;
		p.next = toRemoveNode.next;
		return toRemoveNode;
	}
	
	//获取大小
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		//如果只有头节点，则直接新建
		if(head.next == null)
			add(o);
		//否则在头结点和第一个节点之间插入
		Node p = head.next;
		Node newNode = new Node(o);
		head.next = newNode;
		newNode.next = p;
	}
	public void addLast(Object o){
		Node newNode = new Node(o);
		newNode.next = null;
		Node p = head.next;
		while(p.next != null){
			p = p.next;
		}
		p.next = newNode;
		this.size++;
	}
	public Object removeFirst(){
		if(head.next == null)
			return null;
		if(head.next != null && head.next.next == null){
			head.next = null;
			return null;
		}
		Node p = head.next;
		head.next = p.next;
		p = null;
		return head.next;
	}
	
	public Object removeLast(){
		Node p = head.next;
		Node q = null;
		while(p.next != null){
			q = p;//备份这个节点，再后移
			p = p.next;
		}
		q.next = null;
		p = null;
		return q;
	}
	
	private static class Node{
		public Object data;
		public Node next;
		
		public Node(Object o){
			this.data = o;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
        //单链表为空或只有头结点或只有一个元素，不用进行逆置操作  
        if(this == null|| this.head.next == null|| this.head.next.next == null)  
            return;  
        Node p = head.next.next;//令p指向线性表中第2个元素a2   
        head.next.next = null;//令线性表中第1个元素a1的next为空  
        while(p != null){  
            Node q = p.next;  
            //将p插入头结点之后  
            p.next = head.next;  
            head.next = p;  
            p = q;//继续访问下一个元素  
        } 
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
	}
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(NewLinkedList list){
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(NewLinkedList list){
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  NewLinkedList intersection( NewLinkedList list){
		return null;
	}
}
