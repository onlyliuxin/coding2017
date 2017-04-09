package com.github.chaoswang.learning.java.linkedlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Stack;

import com.github.chaoswang.learning.java.array.ArrayUtil;

public class LinkedList<E> {
	//所有修改操作都要维护size
	private int size = 0;
	//涉及到首尾节点操作时，要维护head和tail指针
	private Node head = null;
	private Node tail = null;
	
	//快
	public void add(E element){
		Node tmp = new Node(element, null);
		if(tail == null){
			head = tmp;
		}else{
			tail.next = tmp;;
		}
		tail = tmp;
		size++;
	}
	
	public void add(int index, E element){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		if(index == size){
			add(element);
			return;
		}else if(index == 0){
			addFirst(element);
			return;
		}
		Node tmpBefore = getNode(index -1);
		Node tmpAfter = getNode(index);
		Node tmp = new Node(element, tmpAfter);
		tmpBefore.next = tmp;
		size++;
	}
	
	public void addFirst(E element){
		Node tmp = new Node(element, null);
		if(head != null){
			tmp.next = head;
		}else{
			tail = tmp;
		}
		head = tmp;
		size++;
	}
	
	public E removeFirst(){
		if(size <= 0){
			throw new NoSuchElementException();
		}
		Node tmp = head;
		head = head.next; 
		size--;
		if(size == 0){
			tail = null;
		}
		return (E)tmp.element;
	}
	
	//快
	public E remove(int index) {
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			return removeFirst();
		}
		Node tmpBefore = this.getNode(index-1);
		Node tmp = this.getNode(index);
		Node tmpNext = this.getNode(index+1);
		tmpBefore.next = tmpNext;
		if(index == size - 1){
			tail = tmpBefore;
		}
		size--;
		if(size == 0){
			tail = null;
		}
		return (E)tmp.element;
	}
	
	//慢
	public E get(int index){
		return (E)this.getNode(index).element;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		if(head == null){
			return "[]" + ", head:"+head+", tail:"+tail;
		}
		StringBuffer sb = new StringBuffer("[");
		Node tmp = head;
		while(tmp != null){
			sb.append(tmp.element.toString());
			sb.append("(");
			if(tmp.next!=null){
				sb.append(tmp.next.element.toString());
			}
			sb.append(")");
			sb.append(",");
			
			tmp = tmp.next;
		}
		String returnStr = sb.toString();
		returnStr = returnStr.substring(0, returnStr.length()-1);
		return returnStr + "]" + ", head:"+head+", tail:"+tail;
	}
	
	private Node getNode(int index) {
		Node tmp = head;
		for(int i=0;i<index;i++){
			tmp = tmp.next;
		}
		return tmp;
	}
	
	/*不加static就和实例绑定了,虽然可以工作，但是每个Node中都将会包含一个指向外围类的引用，浪费时间和空间
	 * 根据Oracle官方的说法：
	 * Nested classes are divided into two categories: static and non-static. 
	 * Nested classes that are declared static are called static nested classes. 
	 * Non-static nested classes are called inner classes.
	 * 从字面上看，一个被称为静态嵌套类，一个被称为内部类。
	 * 从字面的角度解释是这样的：
	 * 什么是嵌套？嵌套就是我跟你没关系，自己可以完全独立存在，但是我就想借你的壳用一下，来隐藏一下我自己（真TM猥琐）。
	 * 什么是内部？内部就是我是你的一部分，我了解你，我知道你的全部，没有你就没有我。（所以内部类对象是以外部类对象存在为前提的）
	 */
	private static class Node{
		private Object element = null;
		private Node next = null;
		//双向链表
//		private Node previos = null;

		public Node(Object element, Node next) {
			this.element = element;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return element.toString();
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){	
		Stack<Node> stackCache = new Stack<Node>();
		Node currentNode = head;
		for(int i=0;i<size;i++){
			Node tmp = currentNode;
			currentNode = currentNode.next;
			//必须先断链，不然修改了next指针之后再遍历就形成死循环?
//			tmp.next = null;
			stackCache.push(tmp);//虽然已断链，但是顺序已经存储到栈里了
		}
		head = stackCache.pop();
		currentNode = head;
		while(!stackCache.isEmpty()){
			currentNode.next = stackCache.pop();
			currentNode = currentNode.next;
		}
		tail = currentNode;
		tail.next = null;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		Node halfNodeBefore = getNode((size/2)-1);
		head = halfNodeBefore.next;
		halfNodeBefore.next = null;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 如果list = 2->5->7->8->10 ,remove(2,2)以后的值为2->5->10
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		Node nodePointer = getNode(i-1);
		Node nodeTargetBefore = getNode(i-1+length);
		nodePointer.next = nodeTargetBefore.next;
		nodeTargetBefore.next = null;
	}
	
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list){
		ArrayList<Integer> cache = new ArrayList<Integer>();
		for(int i=0;i<list.size();i++){
			int index = Integer.parseInt(list.get(i).toString());
			Node indexNode = getNode(index);
			cache.add(Integer.parseInt(indexNode.element.toString()));
		}
		return ArrayUtil.returnByIntArray(cache);
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = [11,201,501,701]
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public void subtract(LinkedList list){
		HashSet<String> set = new HashSet<String>();
		for(int i=0;i<list.size();i++){
			set.add(list.get(i).toString());
		}
		
		for(int i=0;i<size;i++){
			if(set.contains(getNode(i).element.toString())){
				remove(i);
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 * 据一群同学反映，removeDuplicateValues（），removeRange（）这两个方法比较难于实现， 可以不做
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
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = [11,201,801,901]
	 * 返回的结果应该是[11,201]  
	 * @param list
	 */
	public LinkedList intersection(LinkedList list){
		HashSet<String> set = new HashSet<String>();
		for(int i=0;i<list.size();i++){
			set.add(list.get(i).toString());
		}
		LinkedList result = new LinkedList();
		for(int i=0;i<size;i++){
			if(set.contains(getNode(i).element.toString())){
				result.add(new Node(getNode(i).element, null));
			}
		}
		return result;
	}
}
