package com.github.orajavac.coding2017.basic.linklist;

import com.github.orajavac.coding2017.basic.Iterator;
import com.github.orajavac.coding2017.basic.List;

public class LinkedList implements List,Iterator {
	
	public Node head;
	
	private int size=0;
	
	private static class Node{
		Object data;
		Node next;	
	}
	
	public Iterator iterator(){
		return null;
	}
	
	public boolean hasNext(){
		return false;
	}
	
	public Object next(){
		return null;
	}
	
	public void add(Object o){
		if (head == null){
			head = new Node();
			Node n = new Node();
			n.data = o;
			head.next = n;
			size++;
			head.data=size;
		}else{
			Node e = head.next;
			while (e.next!=null){
				e=e.next;
			}
			Node n = new Node();
			n.data = o;
			e.next=n;
			size++;
			head.data=size;
		}
	}
	
	public Object get(int index){
		int len=Integer.parseInt(head.data.toString());
		if (index>len||index<=0){
			throw new RuntimeException("下标不存在"+index);
		}
		Node e = head;
		int i=0;
		while (e.next != null){
			i++;
			if (i == index){
				return e.next.data;
			}
			e=e.next;
		}
		return null;
	}
	
	public Object remove(int index){
		int len=Integer.parseInt(head.data.toString());
		if (index>len||index<=0){
			throw new RuntimeException("下标不存在"+index);
		}
		Node e = head;
		Object data = null;
		int i=0;
		while (e.next != null){
			i++;
			if (i == index){
				len--;
				head.data = len;
				data = e.next.data;
				e.next = e.next.next;
				return data;
			}
			e=e.next;
		}
		return null;
	}
	
	public Object removeFirst(){
		return remove(1);
	}
	
	public Object removeLast(){
		return remove(Integer.parseInt(head.data.toString()));
	}
	
	public void addFirst(Object o){
		Node e = head.next;
		Node n = new Node();
		n.data=o;
		n.next=e;
		size++;
		head.next=n;
		head.data=size;
	}
	
	public void addLast(Object o){
		add(o);
	}
	
	public int size(){
		return Integer.parseInt(head.data.toString());
	}
	
	public void listNode(){
		Node n = head;
		StringBuffer buffer = new StringBuffer();
		while (n.next!=null){
			buffer.append(n.next.data + " -> ");
			n=n.next;
		}
		if(buffer.length()>0){
			System.out.print(buffer.substring(0,buffer.length()-3));
			System.out.println();
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){		
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		if (i <= 0 || length<=0){
			throw new RuntimeException("起始值、结束值都不能小于0等于");
		}
		int len = length + i;
		if (len > size){
			throw new RuntimeException("删除索引长度超过了链表长度");
		}
		Node e = head;
		int y = 0;
		while (e.next != null){
			y++;
			if (y == i){
				Node n = e.next;
				while (n!=null){
					n = n.next;
					if (y == length){
						break;
					}
					y++;
					n=n.next;
				}
			}
			e=e.next;
		}
	}
	
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list){
		if (list==null||list.head==null){
			throw new RuntimeException("集合没有初始化");
		}
		int[] elements = new int[Integer.parseInt(list.head.data.toString())];
		Node l = list.head;
		Node n = head;
		int len = 0;
		int i = 0;
		while (l.next!=null){
			len = 0;
			n=head;
			while(n.next!=null){
				len++;
				if(len==Integer.parseInt(l.next.data.toString())){
					elements[i]=Integer.parseInt(n.next.data.toString());
					i++;
					break;
				}
				n=n.next;
			}
			l = l.next;
		}
		return elements;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public void subtract(LinkedList list){
		if (list==null||list.head==null){
			throw new RuntimeException("集合没有初始化");
		}
		Node l = list.head;
		Node n = head;
		int i = 0;
		while (l.next!=null){
			n=head;
			i=0;
			while(n.next!=null){
				i++;
				if(n.next.data.equals(l.next.data)){
					remove(i);
					break;
				}
				n=n.next;
			}
			l = l.next;
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 11->101->201->301->401->501->601->701
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max){
		if (min<=0||max<=0||min>max){
			throw new RuntimeException("录入不正确："+min+","+max+" 应该大于min且小于max的元素");
		}
		Node n = head;
		int data = 0;
		Node p = null;
		while(n.next != null){
			data=Integer.parseInt(n.next.data.toString());	//11
			if(data>min&&data<max){
				p = n.next.next;	//101
				n.next = p;
				size--;
			}else{
				n = n.next;
			}
		}
		head.data=size;
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		return null; 
	}

	public void add(int index, Object o) {
		// TODO Auto-generated method stub
		
	}
	
}
