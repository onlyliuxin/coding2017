package com.coding.basic;

import java.util.Arrays;

public class LinkedList implements List {
	
	private Node head;
	private Node last;
	private int size=0;
	public void add(Object o){
		if(head==null){
			head =new Node();
			head.data=o;
			last=head;
		}
		else{
			Node temp=new Node();
			temp.data=o;
			last.next=temp;
			last=temp;
		}
		size++;
	}
	public boolean enCapacity(int index){
		if(index>=0&&index<size){
			return true;
		}
		return false;
	}
	public Node indexOf(int index){
		if(enCapacity(index)){
				Node current=head;
				int currentIndex=0;
				while(current!=null){
					if(currentIndex==index){
						return current;
					}
					current=current.next;
					currentIndex++;
				}
			
		}
		return null;
	}
	public void add(int index , Object o){
		 if(enCapacity(index)){
			 Node newNode=new Node();
			 newNode.data=o;
			 if(index==0){
				 newNode.next=head;
				 head=newNode;
			 }
			 else{
				 Node BIndexOf=indexOf(index-1);
				 Node indexOf=indexOf(index);
				 newNode.next=indexOf;
				 BIndexOf.next=newNode;
			 }
			size++;
		}
		 else if(index==size){
			 Node newNode=new Node();
			 newNode.data=o;
			last.next=newNode;
			last=newNode;
		 }
		
		
	}
	public Object get(int index){
		if(enCapacity(index)){
			Node current=head;
			int currentIndex=0;
			while(current!=null){
				if(currentIndex==index){
					return current.data;
				}
				current=current.next;
				currentIndex++;
			}
		}
		return null;
	}
	public Object remove(int index){
		Node remove =indexOf(index);
		if(index==0){
			head=head.next;
			size--;
			return remove.data;
		}
		else if(index+1==size){
			Node bRemove =indexOf(index-1);
			bRemove.next=null;
			last=bRemove;
			size--;
			return remove.data;
		}
		else if(remove!=null){
			Node bRemove =indexOf(index-1);
			Node aRemove =indexOf(index+1);
			bRemove.next=aRemove;
			size--;
			return remove.data;
		}
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node first=new Node();
		first.data=o;
		first.next=head;
		head=first;
		size++;
		
	}
	public void addLast(Object o){
		Node last=new Node();
		last.data=o;
		this.last.next=last;
		this.last=last;
		size++;
	}
	public Object removeFirst(){
		Node temp=head;
		head=head.next;
		size--;
		return temp.data;
	}
	public Object removeLast(){
		Node temp=last;
		last=indexOf(size-2);
		last.next=null;
		size--;
		return temp.data;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
	
	public class ListIterator implements Iterator{
		private int curos;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return curos!=size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			int i=0;
			curos=i+1;
			return get(i);
		}
		
	}
	/**
	 * 把该链表逆置  1 2 3 4 5
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		Node temp1=last;
		for(int i=size-2;i>=0;i--){
			Node temp2=indexOf(i);
			temp1.next=temp2;
			temp1=temp2;
		}
		head.next=null;
		temp1=head;
		head=last;
		last=temp1;
	}
	
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if(size>1){
			Node index=indexOf(size/2-1);
			index.next=null;
			last=index;
			size=size-size/2;
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){//1 2 3 4 5
		if( i<size && i>=0){
			int len=length+i>size? size-i:length;
			int j=0;
			while(j<len){
				remove(i);
				j++;
			}
		}
		/*if((length+i)<=size && length>0 && i>=0){
			Node before=indexOf(i-1);
			Node after=indexOf(length+i);
			if(before==null&&after==null){
				head=null;
				last=null;
				size=0;
			}
			else if(before==null&&after!=null){
				head=after;
				size=size-length;
			}
			else if(before!=null&&after==null){
				before.next=null;
				last=before;
				size=size-length;
			}
			else{
				before.next=after;
				size=size-length;
			}
		}*/
		
	}
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(LinkedList list){
		if(list==null){
			return null;
		}
		int size=list.size;
		int jude=0;
		int [] newInt=new int[size];
		while(jude<size&&size>0){
			int index=(int) list.get(jude);
			if(index>=0&&index<this.size){
				newInt[jude]=(int) get(index);
				jude++;
			}
			
		}
		return Arrays.copyOf(newInt, jude);
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		if(list==null){
			return ;
		}
		int size=list.size;
		int jude=0;
		while(jude<size&&size>0){
			int index=(int) list.get(jude);
			for(int i=0;i<this.size;i++){
				int data=(int) get(i);
				if(data==index){
					if(i==0){
						head=indexOf(i+1);
					}
					else if(i==this.size-1){
						last=indexOf(i-1);
						last.next=null;
					}
					else{
						Node bIndex=indexOf(i-1);
						Node aindex=indexOf(i+1);
						bIndex.next=aindex;
					}
					this.size--;
					break;
				}
			}
			jude++;
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		int i=0;
		int j=0;
		Node pre=head;
		Node next=head.next;
		while(i<size-1){
			if((int)pre.data==(int)next.data){
				Node temp=indexOf(j);
				temp.next=next.next;
				size--;
			}
			j++;
			next=next.next;
			if(next==pre){
				next=next.next;
			}
			
			if(j==size-1){
				pre=pre.next;
				next=head;
				j=0;
				i++;
			}
		}
		/*for(int i=0;i<size;i++){
			int data1=(int) get(i);
			for(int j=i+1;j<size;j++){
				int data2=(int) get(j);
				if(data1==data2){
					if(j==0){
						head=indexOf(j+1);
					}
					else if(j==this.size-1){
						last=indexOf(j-1);
						last.next=null;
					}
					else{
						Node bIndex=indexOf(j-1);
						Node aindex=indexOf(j+1);
						bIndex.next=aindex;
					}
					this.size--;
				}
			}
		}*/
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min       1 2 3 4 5 6 7 8   3 ,6 
	 * @param max        
	 */  
	public  void removeRange(int min, int max){
		if(head==null){
			return;
		}
		int start=-1;
		int end=-1;
		Node temp=head;
		int i=0;
		while(temp!=null){
			if((start == -1) &&(int)temp.data>min){
				start=i;
			}
			if((int)temp.data>=max){
				end=i;
				break;
			}
			i++;
			temp=temp.next;
		}
		if(start==-1){
			start=0;
		}
		if(end==-1){
			end=size;
		}
		this.remove(start,end-start);
	
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		if(list==null){
			return null;
		}
		int i=0;
		int j=0;
		LinkedList c=new LinkedList();
		while(i<this.size&&j<list.size){
			if((int)get(i)==(int)list.get(j)){
				c.add(get(i));
				i++;
				j++;
			}
			else if((int)get(i)>(int)list.get(j)){
				j++;
			}
			else{
				i++;
			}
		}
		return c;
	}
}
