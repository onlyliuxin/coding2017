package com.m0312.download.api;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.m0226.basic.ArrayList;
import com.m0226.basic.Iterator;
import com.m0226.basic.List;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	/**
	 * 与addLast()是一样的
	 */
	public void add(Object o){
		addLast(o);
	}
	public void add(int index , Object o){
		if(index<0||index>size){
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		}
		Node prevNode=head;
		Node curNode=head.next;
		int count=0;
		while(count<=index){
			if(count==index){
				Node newNode=new Node();
				newNode.data=o;
				
				newNode.next=curNode;
				prevNode.next=newNode;
				size++;
				break;
			}
			curNode=curNode.next;
			prevNode=prevNode.next;
			count++;
		}
		
		
	}
	public Object get(int index){
		if(index<0||index>=size)
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		
		Node curNode=head.next;
		int count=0;
		while(count<=index){
			if(count==index){
				return curNode.data;
			}
			curNode=curNode.next;
			count++;
		}
		return null;
	}
	public Object remove(int index){
		if(index<0||index>=size)
			throw new IndexOutOfBoundsException("Joy Index "+index+", Size: "+size);
		Node prevNode=head;
		Node curNode=head.next;
		int count=0;
		while(count<=index){
			if(count==index){
				prevNode.next=curNode.next;
				Object object=curNode.data;
				curNode.next=null;
				curNode=null;
				size--;
				return object;
			}
			curNode=curNode.next;
			prevNode=prevNode.next;
			count++;
		}
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node objNode=new Node();
		objNode.data=o;
		if(head==null) head=new Node();
		objNode.next=head.next;
		size++;
		head.next=objNode;
	}
	public void addLast(Object o){
		Node objNode=new Node();
		objNode.data=o;
		if(head==null) head=new Node();
		
		//也可以用iterator迭代，先不用吧
		Node curNode=head;
		while(curNode.next!=null){
			curNode=curNode.next;
		}
		objNode.next=curNode.next;
		curNode.next=objNode;
		size++;
		
	}
	public Object removeFirst(){
		if(head==null||head.next==null)
			throw new NoSuchElementException();
		Node delNode=head.next;
		head.next=delNode.next;
		size--;
		return delNode.data;
	}
	public Object removeLast(){
		if(head==null||head.next==null)
			throw new NoSuchElementException();
		Node prevNode=head;
		Node curNode=head.next;
		while(curNode!=null){
			if(curNode.next==null){//说明是尾节点
				prevNode.next=curNode.next;
				size--;
				return curNode.data;
			}
			curNode=curNode.next;
			prevNode=prevNode.next;
		}
		return null;
	}
	public Iterator iterator(){
		return new Iterator() {
			private Node cur=head!=null?head.next:head;
			@Override
			public Object next() {
				if(cur==null){
					throw new NoSuchElementException();
				}
				Object object=cur.data;
				cur=cur.next;
				return object;
			}
			
			@Override
			public boolean hasNext() {
				if(cur==null){
					return false;
				}else{
					return true;
				}
				
			}
		};
	}


	private static  class Node{
		Object data;
		Node next;

	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){		
		Object[] objarr=new Object[size];
		Node temp=head!=null?head.next:null;
		int count=0;
		while(temp!=null){
			objarr[count]=temp.data;
			temp=temp.next;
			count++;
		}		
		temp=head;
		for(int j=objarr.length-1;j>=0;j--){
			Node node=new Node();
			node.data=objarr[j];
			temp.next=node;
			temp=node;
		}
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if(head==null||head.next==null)return;
		int delenum=size/2;
		int i=0;
		Node temp=null;
		Node nextNode=head.next;
		while(i<delenum){
			temp=nextNode;
			nextNode=nextNode.next;
			
			temp.next=null;//将删除的节点置空
			temp=null;
			i++;
			size--;
		}
		head.next=nextNode;
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i>size-1||i<0)
			throw new IndexOutOfBoundsException("Joy Index: "+i+", Size: "+size);
		//如果i之后不足length个元素，该怎么处理，抛出异常，还是仅将剩下的移除
		int j=0;
		int deleLen=0;//记录删除的个数
		
		Node temp=null;
		Node nextNode=head.next;	
		Node preNode=head;
		//将node指针移动到i
		while(j<i){
			nextNode=nextNode.next;
			preNode=preNode.next;
			j++;
		}
		while(deleLen<length){
			temp=nextNode;
			nextNode=nextNode.next;
			if(nextNode==null){
				break;
			}
			temp.next=null;//将删除的节点置空
			temp=null;
			deleLen++;
			size--;
		}
		preNode.next=nextNode;
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
		Iterator ite=list.iterator();
		int[] result=new int[list.size];
		int index;
		int i=0;
		while(ite.hasNext()){
			index=(int) ite.next();
			if(index>=size){
				throw new IndexOutOfBoundsException("Joy Index: "+index+", Size: "+size); 
			}
			result[i]=(int) get(index);
			i++;
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		Iterator ite=list.iterator();
		int index=-1;
		while(ite.hasNext()){
			Object obj=ite.next();
			index=indexOf(obj);
			while(index>=0){
				remove(index);
				size--;
				index=indexOf(obj);//防止当前链表有重复元素
			}
		}
	}
	/**
	 * 返回该值的索引，如果存在
	 * @param o 
	 * 2017年3月11日 下午5:42:15
	 * @Author Joy
	 */
	public int indexOf(Object o){
		if(head==null||head.next==null) return -1;
		int index=0;
		
		if(o==null){
			for(Node temp=head.next;temp!=null;temp=temp.next){
				if(temp.data==null){
					return index;
				}
				index++;
			}
		}else{
			for(Node temp=head.next;temp!=null;temp=temp.next){
				if(o.equals(temp.data)){
					return index;
				}
				index++;
			}
		}
		return -1;
	}
	
	/**
	 * ?????
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		if(head==null||head.next==null) return;
		//递增
		Node cur=head.next;
		Node nextNode=null; 
		Node temp=null; 
		for( ;cur!=null;cur=cur.next){
			nextNode=cur.next;
			while(Objects.equals(cur.data, nextNode.data)){
				temp=nextNode;
				nextNode=nextNode.next;
				temp.next=null;
				temp=null;
				size--;
			}
			cur.next=nextNode;
			System.out.println(nextNode.data+"*** size:"+size+",cur.next :");
		}
		System.out.println("size : "+size);
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
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}
