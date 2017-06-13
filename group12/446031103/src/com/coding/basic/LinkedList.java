package com.coding.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.experimental.theories.Theories;

import sun.reflect.Reflection;

/**
 * 
 * LinkedList集合-链
 * 
 * @ClassName LinkedList
 * @author msh
 * @date 2017年2月21日 下午4:08:01
 */
public class LinkedList implements List {
	//链头
	private Node head;
	//集合大小
	private int size=0;
	/**
	 * 
	 * 向链中添加元素
	 *
	 * @Method add 添加
	 * @param o 元素
	 * @see com.coding.basic.List#add(java.lang.Object)
	 */
	public void add(Object o){
		Node newNode = new Node(o, null);
		if (null == head) {
			head = newNode;
		} else {
			Node lastNode = null;
			for (int i = 0; i < size; i++) {
				lastNode = (Node) get(i);
			}
			lastNode.next = newNode;
		}
		size++;
	}
	/**
	 * 
	 * 向链中添加元素 
	 *
	 * @Method add 增加
	 * @param index 下标
	 * @param o 元素
	 * @see com.coding.basic.List#add(int, java.lang.Object)
	 */
	public void add(int index , Object o){
		validate(index);
		Node newNode = null;
		Node perNode = null;
		Node nextNode = null;
		// 当为最后插入时
		if (index == size - 1) {
			newNode = new Node(o, null);
			for (int i = 0; i < index; i++) {
				Node tempNode = (Node) get(i);
				perNode = tempNode.next;
			}
			perNode.next = newNode;
		} else if (0 == index) {
			nextNode = head.next;
			newNode = new Node(o, nextNode);
			head = newNode;
		} else {
			for (int i = 0; i < index; i++) {
				Node tempNode = (Node) get(i);
				perNode = tempNode.next;
			}
			nextNode = perNode.next.next;
			newNode = new Node(o, nextNode);
			perNode.next = newNode;
		}
		size++;
	}
	/**
	 * 
	 * 取得元素 
	 *
	 * @Method get 取得
	 * @param index 下标
	 * @return
	 * @see com.coding.basic.List#get(int)
	 */
	public Object get(int index){
		validate(index);
		Node tempNode = head;	
		for (int i = 0; i <= index; i++) {
			tempNode = tempNode.next;
		}
		return tempNode;
	}
	/**
	 * 
	 * 删除元素
	 *
	 * @Method remove 删除
	 * @param index 下标
	 * @return
	 * @see com.coding.basic.List#remove(int)
	 */
	public Object remove(int index){
		Node removeNode = (Node) get(index);
		validate(index);
		if (index == size - 1) {
			Node tempNode = head;
			for (int i = 0; i < index; i++) {
				tempNode = tempNode.next;
			}
			tempNode.next = null;
		} else if (index == 0) {
			Node tempNode = head.next;
			head.next = null;
			head = tempNode;
		} else {
		}
		size--;
		return removeNode;
	}
	/**
	 * 
	 * 取得集合大小 
	 *
	 * @Method size 集合大小
	 * @return 集合大小
	 * @see com.coding.basic.List#size()
	 */
	public int size(){
		return size;
	}
	/**
	 * 
	 * 想链头中插入元素
	 *
	 * @MethodName addFirst
	 * @author msh
	 * @date 2017年2月21日 下午4:10:56
	 * @param o
	 */
	public void addFirst(Object o){
		Node newNode = new Node(o, head);
		head = newNode;
	}
	/**
	 * 
	 * 向链后加入元素 
	 *
	 * @MethodName addLast
	 * @author msh
	 * @date 2017年2月21日 下午4:11:43
	 * @param o
	 */
	public void addLast(Object o){
		add(o);
	}
	/**
	 * 
	 * 删除链头 
	 *
	 * @MethodName removeFirst
	 * @author msh
	 * @date 2017年2月21日 下午4:12:14
	 * @return
	 */
	public Object removeFirst(){
		if(null==head)
			throw new IndexOutOfBoundsException("Size: " + size);
		Node orgHead = head;
		Node tempNode = head.next;
		head.next = null;
		head = tempNode;
		return orgHead;
	}
	/**
	 * 
	 * 删除链尾
	 *
	 * @MethodName removeLast
	 * @author zhaogd
	 * @date 2017年2月21日 下午4:12:44
	 * @return
	 */
	public Object removeLast(){
		if(null==head)
			throw new IndexOutOfBoundsException("Size: " + size);
		Node lastNode = (Node) get(size);
		Node tempNode = head;
		for (int i = 0; i < (size - 1); i++) {
			tempNode = tempNode.next;
		}
		tempNode.next = null;
		return lastNode;
	}
	public Iterator iterator(){
		return null;
	}
	
	/**
	 * 
	 * 验证 
	 *
	 * @MethodName validate 下标
	 * @author msh
	 * @date 2017年2月21日 下午3:54:21
	 * @param index
	 */
	private void validate(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}
	/**
	 * 
	 * 链中元素
	 * 
	 * @ClassName Node
	 * @author zhaogd
	 * @date 2017年2月21日 下午4:13:10
	 */
	private static  class Node{
		Object data;
		Node next;
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		for (int i = size; i <0; i--) {
			Node last=(Node) get(i);
			if(0==i)
				last.next = null;
			else
			last.next = (Node) get(i-1);
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		
			for (int i = 0; i < size%2; i++) {
				remove(i);
			}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for (int a = i; a < i+length; a++) {
			remove(a);
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
	public static int[] getElements(LinkedList list){
		int [] result =new int[list.size];
		Class<?> callClass=Reflection.getCallerClass();
		for (int i = 0; i < list.size; i++) {
			Node node=(Node) list.get(i);
			try {
				Method method=callClass.getDeclaredMethod("get", new Class[]{int.class});
				result[i]=(int) method.invoke(callClass, new Object[]{node.data});
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){	
		for (int i = 0; i < list.size; i++) {
			Node node=(Node) list.get(i);
			for (int j = 0; j < size; j++) {
				Node orgNode=(Node) get(i);
				if (node.data.equals(orgNode.data)) {
					remove(j);
				}
			}		
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		for (int i = 0; i < size; i++) {
			Node node=(Node) get(i);
			for (int j = i+1; j < size; j++) {
				Node newNode=(Node) get(j);
				if(newNode.data.equals(node.data))
					remove(j);
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		for (int i = 0; i < size; i++) {
			Node node=(Node) get(i);
			if((int)node.data>min && (int)node.data<max)
				remove(i);
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList newList = new LinkedList();
		for (int i = 0; i < size; i++) {
			Node node=(Node) get(i);
			for (int j = 0; j < list.size; j++) {
				Node newNode=(Node) get(j);
				if(newNode.data.equals(node.data)){
					newList.add(node);
					break;
				}
					
			}
		}
		return newList;
	}
}
