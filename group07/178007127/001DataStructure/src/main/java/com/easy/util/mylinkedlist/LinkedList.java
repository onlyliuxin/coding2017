package com.easy.util.mylinkedlist;

import java.util.NoSuchElementException;
import java.util.SimpleTimeZone;

public class LinkedList  {
	
	private Node first;
	private Node last;
	int size=0;
	
	//region 构造函数
	public LinkedList(){
		
	}
	//endregion
	
	//region add方法
	public void addFirst(Object o){
		linkFirst(o);
	}
	
	public void addLast(Object o){
		linkLast(o);
	}
	
	public void add(Object o){
		linkLast(o);
	}
	
	public void add(int index , Object o){
		checkPositionIndex(index);
		
		if(index==size){
			linkLast(o);
		}else if(index==0){
			linkFirst(o);
		}else {
			linkBefore(index, o);
		}
	}
	//endregion

	//region get 方法
	public Object get(int index){
		return node(index).item;
	}
	//endregion

	//region remove 方法
	public boolean remove(Object o){
		Node temp=first;
		if(o==null){
			for(int i=0;i<size;i++){
				if(temp.item==null){
					unlink(i);
					return true;
				}
				temp=temp.next;
			}
		}else{
			for(int i=0;i<size;i++){
				if (temp.item.equals(o)) {
					unlink(i);
					return true;
				}
				temp=temp.next;
			}
		}
		return false;
	}
	
	public Object removeFirst(){
		Node temp = first;
		if(temp==null){
			throw new NoSuchElementException();
		}
		return unlinkFirst(temp);
	}
	public Object removeLast(){
		Node temp =last;
		if(last==null){
			throw new NoSuchElementException();
		}
		if(size==1){
			first=last=null;
			size--;
			return temp.item;
		}
		return unlinkLast(temp);
	}
	//endregion

	//region size 方法
	public int size(){
		return this.size;
	}
	//endregion

	//region toString方法
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node xNode = first;
		for(int i=0;i<size;i++){
			Object object = xNode.item;
			sb.append(object+",");
			xNode=xNode.next;
		}
		String temp = sb.toString();
		temp = temp.substring(0, temp.length() - 1);
		return "[" + temp + "]";
	}
	//endregion
	
	//region 私有成员
	private void linkBefore(int index,Object o){
		Node pred=node(index-1);
		Node newNode=new Node(o, node(index));
		pred.next=newNode;
		size++;
	}
	
	private void unlink(int index){
		Node before =node(index-1);
		Node target = before.next;
		Node after = target.next;
		before.next=after;
		size--;
	}
	
	private static  class Node{
		Object item;
		Node next;
		public Node(Object element,Node next) {
			this.item=element;
			this.next=next;
		}
	}
	
	private void linkLast(Object o){
		Node temp=last;
		Node newNode=new Node(o, null);
		last=newNode;
		if(temp==null){
			first=newNode;
		}else{
			temp.next=newNode;
		}
		size++;
	}
	
	private void linkFirst(Object o){
		Node temp=first;
		Node newNode=new Node(o, temp);
		first=newNode;
		if(temp==null){
			last=newNode;
		}else{
			first.next=temp;
		}
		size++;
	}
	
	private Object unlinkFirst(Node temp){
		Object element = temp.item;
		Node next = temp.next;
		first = next;
		if(next==null){
			last=null;
		}
		size--;
		return element;
	}
	
	private Object unlinkLast(Node temp){
		Node xNode = first;
		for(int i=0;i<size-2;i++){
			xNode = xNode.next;
		}
		last=xNode;
		last.next=null;
		size--;
		return temp.item;
	}
	
	private Node node(int index){
		if(index<size){
			Node xNode =first;
			for(int i=0;i<index;i++){
				xNode=xNode.next;
			}
			return xNode;
		}
		return null;
	}
	
	private void checkPositionIndex(int index){
		if(!isPositionIndex(index)){
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
	
	private boolean isPositionIndex(int index){
		return index>0&&index<=size;
	}
	
	private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
	//endregion

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		Node xNode =last;
		Node temp_first=xNode;
		for(int i= size-1;i>=0;i--){
			xNode.next =node(i);
			xNode=node(i);
		}
		last=xNode;
		first=temp_first;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int deleteSize =Math.round(size()/2);
		first =node(deleteSize);
		size=size()-deleteSize;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i==0&&length==size){
			size=0;
			first=last=null;
		}else if(i==0&&length<size){
			first=node(length);
		}else if(i>0&&length<size-i){
			node(i-1).next=node(i+length);
		}else{
			
		}
		size=size-length;
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public String[] getElements(LinkedList list){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<list.size();i++){
			Object obj= list.get(i);
			int index = Integer.parseInt(obj.toString());
			sb.append(node(index).item+",");
		}
		
		return sb.toString().split("[,]");
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
//		for(int i=0;i<list.size();i++){
//			String temp = list.get(i).toString();
//			
//		}
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
	public  LinkedList intersection( LinkedList list){
		return null;
	}

}
