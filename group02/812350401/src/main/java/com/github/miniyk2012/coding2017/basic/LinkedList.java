package com.github.miniyk2012.coding2017.basic;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Objects;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.sun.tracing.dtrace.ArgsAttributes;

import utils.ArrayUtils;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	/**
	 * node接收的index一定是范围内的值，不可能越界
	 * @param index
	 * @return a Node
	 */
	Node node(int index) {
		Node x = head;
		for (int i=0; i<index; i++) {
			x = x.next;
		}
		return x;
	}
	
	public void add(Object o){
		add(size, o);
	}
	
	public void add(int index , Object o){
		checkPositionIndex(index);
		Node after = node(index);
		Node newNode = new Node(o, after);
		if (index == 0) {
			head = newNode;
		}
		else {
			Node before = node(index-1);
			before.next = newNode;
		}
		size++;
	}
	
	public Object get(int index){
		checkElementIndex(index);
		Node thisNode = node(index);
		return thisNode.data;
	}
	
	public Object remove(int index){
		checkElementIndex(index);
		Node toRemove = node(index);
		if (index == 0)
			head = toRemove.next;
		else {
			Node before = node(index-1);
			before.next = toRemove.next;
		}
		size--;
		return toRemove.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0, o);
	}
	
	public void addLast(Object o){
		add(o);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size-1);
	}
	
	public Iterator iterator(){
		Iterator iterator = new IteratorImp(this);
		return iterator;
	}
	
	private static class Node{
		Object data;
		Node next;
		
		Node(){}
		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }
	
	private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){	
		// 自感写的还行，没有多余代码
		Node previous = null;
		while (head != null) {
			Node next = head.next;
			head.next = previous;
			previous = head;
			head = next;
		}
		head = previous;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public void removeFirstHalf(){
		int halfIndex = size() / 2;
		head = node(halfIndex);
		size = size() - halfIndex; 
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 * @throws Exception 
	 */
	public void remove(int i, int length) throws Exception{
		if (length <0 || i <0 || i>=size()) throw new Exception();
		if (length == 0) return;
		if (i+length>size()) throw new Exception();
		
		Node after = (i+length==size()?null:node(i+length));
		if (i>=1) {
			node(i-1).next = after;
		} else {
			head = after;
		}
		
		size -= length;
	}
	
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 * @throws Exception 
	 */
	public int[] getElements(LinkedList list) throws Exception {
		// 不允许用get方法取得当前链表的元素，重分利用当前链表和list的有序性
		if (list.size == 0) return new int[0];
		if ((int)list.get(0)<0 || (int)list.get(list.size-1)>=size) {
			throw new Exception();
		}
		java.util.LinkedList<Integer> aStandardList = new java.util.LinkedList<>();
		Node current = head;
		for (int i=0,j=0; i<list.size(); i++)   // i是list的下标,j是当前链表的下标
		{	
			while(j < (int)list.get(i)) {
				current = current.next;
				j++;
			}
			aStandardList.add((Integer)current.data);
		}
		return ArrayUtils.list2Array(aStandardList);
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public void subtract(LinkedList list){
		if (list == null || list.size() == 0 || size == 0) return;
		Iterator it = list.iterator();
		while (it.hasNext()) {
			int ele = (int) it.next();
			removeBySort(ele);
		}
	}
	
	/**
	 * 当前链表为有序时，删除值为ele的所有元素；无序链表的行为未定义
	 * @param ele
	 */
	private void removeBySort(int ele) {
		boolean compare = false;
		// 找到第一个相等元素
		Node previous = null;
		Node current = head;
		while (current != null) {
			int data = (int) current.data;
			compare = Objects.equals(data, ele);
			if (compare) break;
			previous = current;
			current = current.next;
		}
		
		if (!compare) {
			return;
		}
		
		int subNum = 0;  // 删除的元素个数
		// 把所有从current开始的连续的相同的元素删除
		if (previous == null) {
			while(head != null) {
				head = head.next;
				subNum++;
				if (head==null || !Objects.equals(ele, head.data)) 
				{
					size -= subNum;
					return;
				}
			}
		} else {
			while(current != null) {
				current = current.next;
				previous.next = current;
				subNum++;
				if (current==null || !Objects.equals(ele, current.data)) {
					size -= subNum;
					return;
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		if (size() == 0) return;
		
		Node first = head;
		Node last = head;
		while (last != null) {
			int data = (int) first.data;
			while (true) {
			    size--;
				last = last.next;
				if (last == null) {
				    size++;
					first.next = null;
					return;
				}
				if (!Objects.equals(data, last.data)) {
				    size++;
					first.next = last;
					break;
				}
			}
			first = last;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max){
	    if (size() == 0) return;
        if (max < min) return;

	    // [head, *, *, before, (.. delete ..), after]

		Node before = head;   // before是最后一个小于等于min的数
        while (before.next != null && (int) before.next.data <= min) {
            before = before.next;
        }
        if (before.next == null) {
            return;
        }

		Node after = before.next;    // after是第一个大于等于max的数
        while (after !=null && (int) after.data < max) {
            after = after.next;
            size--;
        }
        if (after == null) size--;
        before.next = after;
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList intersection(LinkedList list){
	    if (list == null) return null;

        LinkedList cList = new LinkedList();

	    // 为了方便起见，先各自复制并去重，虽然效率不高，以后有时间再优化
        LinkedList aList = LinkedList.copy(this);
        LinkedList bList = LinkedList.copy(list);

        aList.removeDuplicateValues();
        bList.removeDuplicateValues();

        int a = 0;   // 用于遍历a链表
        int b = 0;   // 用于遍历b链表

        while (a < aList.size() && b < bList.size()) {
            int aValue = (int)aList.get(a);
            int bValue = (int)bList.get(b);
            if (aValue > bValue) {
                b++;
            } else if (aValue < bValue) {
                a++;
            } else {
                cList.add(a);
                a++;
                b++;
            }
        }
	    return cList;
	}

	public static LinkedList copy(LinkedList list) {
	    LinkedList copy = new LinkedList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            copy.add(it.next());
        }
        return copy;
    }
	
	public static void main(String args[]) throws Exception {
		LinkedList aLinkedList = new LinkedList();
		for (int i=0; i<4; i=i*i) {
			aLinkedList.add(i);  // [0,1,4,9]
		}
		LinkedList bLinkedList = new LinkedList();
		int[] z1 = aLinkedList.getElements(bLinkedList); // []
		System.out.println(Arrays.toString(z1));
	}
}
