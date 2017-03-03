package com.zzk.coding2017.zuoye_1;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		if(head==null){
			head = new Node();
			head.data = o;
			head.next = null;
		}else{
			Node tmp = new Node();
			tmp.data = o;
			tmp.next = head;
			head = tmp;
		}
	}
	public void add(int index , Object o){
		if(head==null){//如果head==null，说明根本不存在这个链表，所以不可能指定一个index来插入。
			return ;
		}else{
			if(index<0){
				return;
			}else{
				Node p = head;
				int i = 0;
				while(i<index&&p.next!=null){//寻找index。当i<index时，说明还没有找到index，而p.next!=null说明还可以继续找下去
					i++;
					p=p.next;
				}
				if(i==index){//找到index元素
					if(index==0){//如果index==0，那么需要移动head，否则不需要。
						Node tmp = new Node();
						tmp.data = o;
						tmp.next = head.next;
						head.next = tmp;
					}else{
						Node tmp = new Node();
						tmp.data = o;
						tmp.next = p.next;
						p.next = tmp;
					}
				}else{//没找到index元素
					return;
				}
			}
			
		}
	}
	public Object get(int index){
		if(head == null){
			return null;
		}else{
			if(index<0){
				return null;
			}else{
				Node p = head;
				int i = 0;
				while(i<index&&p.next!=null){
					i++;
					p=p.next;
				}
				if(i==index){
					return p.data;
				}else{
					return null;
				}
			}
		}
	}
	public Object remove(int index){
		if(head == null){
			return null;
		}else{
			if(index<0){
				return null;
			}else{
				//要删除index需要找到他的前一个元素，如果index==0，它没有前一个元素。所以要单独处理
				if(index == 0){
					Node tmp = head;
					head = head.next;
					return tmp.data;
				}else{//要找到index-1元素
					Node p = head ;
					int i = 0;
					while(i<index-1&&p.next!=null){
						i++;
						p=p.next;
					}
					if(i==index-1){
						if(p.next==null){//还要判断index是否存在
							return null;
						}else{
							Node tmp = p.next;
							p.next = p.next.next;
							return tmp.data;
						}
					}else{
						return null;
					}
				}
				
			}
		}
	}
	
	public int size(){
		if(head == null){
			return 0;
		}else{
			Node p = head;
			int i = 0;
			while(p.next!=null){
				i++;
				p=p.next;
			}
			return i+1;//因为循环求出的是末尾元素的索引，所以需要+1.
		}
	}
	
	public void addFirst(Object o){
		add(o);
	}
	public void addLast(Object o){
		if(head == null){
			return ;
		}else{
			Node p = head;
			while(p.next!=null){
				p=p.next;
			}
			Node tmp = new Node();
			tmp.data = o;
			p.next = tmp;
		}
	}
	public Object removeFirst(){
		if(head == null){
			return null;
		}else{
			Node result = head;
			head=head.next;
			return result.data;
		}
	}
	public Object removeLast(){
		if(head == null){
			return null;
		}else{//要删除最后一个，觉找到倒数第二个，但是，当链表只有一个头元素时，是没有倒数第二个元素的，所以要单独处理
			if(head.next==null){
				Node result = head;
				head = null;
				return result.data;
			}else{
				Node q = head;
				Node p = head.next;
				while(p.next!=null){
					p=p.next;
					q=p.next;
				}
				q.next = null;
				return p.data;
			}
		}
		
		
	}
	public Iterator iterator(){
		return new Iterator() {
			Node p = head;
			@Override
			public Object next() {
				// TODO Auto-generated method stub
				Node result = p;
				p=p.next;
				return result.data;
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				if(p!=null)
					return true;
				else
					return false;
			}
		};
	}
	
	
	private static  class Node{
		Object data;
		Node next;
	}
}
