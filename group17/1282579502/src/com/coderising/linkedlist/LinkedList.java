package com.coderising.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LinkedList extends com.coding.basic.LinkedList {
	
	
	public void insert(int[] array){
		for(int i = 0; i< array.length; i++){
			this.add(array[i]);
		}
	}
	public int[] toIntArray(){
		int[] ret = new int[size];
		Node cur = head;
		int index = 0;
		while(cur!= null){
			ret[index] = (int)cur.data;
			index++;
			cur = cur.next;
		}
		return ret;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Node cur = head;
		while(cur != null){
			sb.append(cur.data+" ");
			cur = cur.next;
		}
		return sb.toString();
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		
		Node current = head;
		tail = head;
		Node next = null;
		Node previous = null;
		while(current != null){
			//store current next;
			next = current.next;
			//reverse the link, see the next node of current to the previous node
			current.next = previous;
			//update previous to be current for the next iteration
			previous = current;
			//update current to be the next node for the next iteration
			current = next;
		}
		head = previous;
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if(super.size <=1) return;
		int removeLength = super.size()/2;
		Node current = head;
		int count =0;
		
		while(current != null && count ++<removeLength){
			//System.out.println("current: " + current.data);
			current = current.next;
		}
		head = current;
		size=size -(removeLength);
		//System.out.println("size = " + size); 
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i>=size || length > size || i<0  || length<0) throw new IndexOutOfBoundsException("invalid size or length");
		Node current = head;
		if(i ==0 ){
			int offset = 0;
			
			while(offset<length){
				offset ++;
				current=current.next;
			}
			head = current;
			size-=length;
		}
		//need to skip first i items
		else{
			/*
			 * 1-2-3
			 * if i = 1
			 * skip 0, head is unchanged
			 */
			int offset = 0;		
			
			Node startNode = getNodeAt(head, i-1);
			//System.out.println("start node: " + startNode.data);
			offset = 0;
			current = startNode.next; //remember to skip the start node. this is kept. 
			//Each time entering the loop, one item is skipped. 
			//if we start from startNode, the first time entering the loop with skip this
			//wanted node.
			while(offset++ < length){
				//System.out.println("get " + current.data);
				current = current.next;
			}
			startNode.next = current;
			size -= length;
		}

		
	}
	
	private Node getNodeAt(Node head, int index){
		Node ret = head;
		for(int i = 0; i< index; i++){
			ret = ret.next;
		}
		return ret;
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
		Node cur = head;
		int[] ret = new int[list.size()];
		int index = 0;
		int count = 0;
		while(cur != null && index < list.size()){
			if(count == (int)list.get(index)){
				ret[index] = (int)cur.data;
				index++;
			}
			count++;
			cur = cur.next;
		}
		//System.out.println(Arrays.toString(ret));
		return ret;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	/*
	 * original 2-3-4-5
	 * list 3-4-5
	 * if cur = 2, smaller then 3, cur ++;
	 * if cur = 3, equal to 3, remove, cur = 4; next compare can start with 3,
	 * if cur = 4, equal to 4, remove 4; next compare can start with 4, 
	 */
	public  void subtract(LinkedList list){
		Node cur = head;
		Node pre = null;
		List<Integer> sortedList = new ArrayList<Integer>(); 
		
		for(int i = 0; i< list.size(); i++){
			sortedList.add(new Integer((int) list.get(i)));
		}
		int index = 0;
		//o(nlgn)
		Collections.sort(sortedList);
		pre = head;
		//o(n)
		while(cur != null && index<sortedList.size()){
			//System.out.println("compare: "+" cur: " + cur.data + " sorted:" + sortedList.get(index));
			if((int)cur.data == sortedList.get(index).intValue()){
				//System.out.println("remove: " + cur.data);
				if(head == cur){
					head = cur.next;
					pre = head;
					cur = cur.next;
				}
				else if(tail == cur){
					pre.next = null;
					cur = null;
				}
				else{
					pre.next = cur.next;
					cur = cur.next;
				}
				size--;
			}
			else if( (int)cur.data > sortedList.get(index).intValue()){
				index ++;
			}
			else{
				pre = cur;
				cur = cur.next;
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node cur = head;
		while(cur != null && cur.next != null){
			if(Objects.equals(cur.data, cur.next.data)){
				cur.next = cur.next.next;
				size --;
			}
			else{
				cur = cur.next;
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	/*
	 * 0 1 1 2 3 3 5 delete all >1 and <4
	 */
	public  void removeRange(int min, int max){
		if(min>=max) return;
		
		Node preDelete = null;
		Node afterDelete = null;
		Node cur = head;
		int count = 0;
		if((int)head.data > min){
			while(cur.next != null){
				count++;
				if( (int) cur.next.data > max){
					afterDelete = cur.next;
					break;
				}
				cur = cur.next;
			}
			if(afterDelete == null){
				head = null;
				size -= count;
			}else{
				head = afterDelete;
				size -= count;
			}
		}
		else if((int) tail.data< min){
			head = null;
			tail = null;
			size = 0;
		}
		else{
			while(cur.next != null){
				if(preDelete == null)//not found min yet{
				{
					if( (int) cur.next.data > min){
						preDelete = cur;
					}
				}
				else{
					count++;
					if( (int) cur.next.data >= max){
						afterDelete = cur.next;
						break;
					}
				}
				cur = cur.next;
			}
			//System.out.println("preDelete: " + preDelete.data);
			if(afterDelete ==  null){
				//System.out.println("afterDelete = null ");
				preDelete.next = null;
				count++;
				size -= count;
			}else{
				//System.out.println("afterDelete = " + afterDelete.data);
				preDelete.next = afterDelete;
				size-=count;
			}
			//System.out.println("size: " + size);
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList ret = new LinkedList();
		
		Node cur = head; 
		int index = 0;
		while(cur != null && index < list.size){
			if((int)cur.data == (int)list.get(index)){
				ret.add(cur.data);
				cur = cur.next;
				index ++;
			}
			else if((int) cur.data > (int) list.get(index)){
				index ++;
			}
			else{
				cur = cur.next;
			}
		}
		
		return ret;
	}
}
