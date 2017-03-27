package com.coding.basic;

import java.util.Iterator;

/**
 * @author Scholar
 * @Time：2017年3月6日 下午9:45:54
 * @version 1.0
 */
public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public boolean add(Object o){
		addLast(o);
		return true;
		
	}
	public void add(int index , Object o){
		checkPositionIndex(index);
		if (index == 0) {
			addFirst(o);
		} else if (index == size) {
			addLast(o);
		} else {
			Node x = head;
			for (int i = 0; i < index - 2; i++) {
				x = x.next;
			}
			Node temp = new Node(o,x.next);
			x.next = temp;
		}
		size++;
	}
	public Object get(int index){
		checkElementIndex(index);
		Node x = head;
		for (int i = 0; i < index; i++) {
			x = x.next;
		}
		return x.data;
	}
	@SuppressWarnings("unused")
	public Object remove(int index){
		checkElementIndex(index);
		Object element = null;
		if (index == 0) {
			Node removeNode = head;
			head = head.next;
			element = removeNode.data;
			removeNode = null;
		} else {
			checkElementIndex(index - 1);
			Node x = head;
			for (int i = 0; i < index - 1; i++) {
				x = x.next;
			}
			Node removeNode = x.next;
			x.next = removeNode.next;
			element = removeNode.data;
			removeNode = null;
		}
		size--;
		return element;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node temp = head;
		Node newNode = new Node(o,temp);
		head = newNode;
		size++;
	}
	public void addLast(Object o){
		Node temp = new Node(o, null);
		if (size == 0) {
			head = temp;
		} else {
			Node x = head;
			while (x.next != null) {
				x = x.next;
			}
			x.next = temp;
		}
		size++;
	}
	public Object removeFirst(){
		Object element = null;
		if (size != 0) {
			element = head.data;
			head.data = null;
			
			Node next = head.next;
			head.next = null;
			head = next;
			size--;
		}
		return element;
	}
	public Object removeLast(){
		Object element = null;
		if (size != 0) {
			if (head.next == null) {
				element = head.data;
				head.data = null;
			} else {
				Node x = head;
				for (int i = 0; i < size - 2; i++) {
					x = x.next;
				}
				Node removeNode = x.next;
				x.next = null;
				element = removeNode.data;
				removeNode = null;
			}
			size--;
		}
		return element;
	}
	public Iterator<?> iterator(){
		return new LinkedListIterator();
	}
	
    //检查下标是否合法
    private void checkElementIndex(int index){
    	if (!isElementIndex(index)) {
    		throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	}
    }
    
    @SuppressWarnings("unused")
	private void checkPositionIndex(int index){
    	if (!isPositionIndex(index)) {
    		throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	}
    }
    
    //检查该参数是否为现有元素的索引。
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    //检查参数是否是迭代器或添加操作的有效位置的索引
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
	
	@SuppressWarnings("unused")
	private static class Node{
		Object data;
		Node next;
		Node(Object data, Node next){
			this.data = data;
			this.next = next;
		}
	}
	
	private class LinkedListIterator implements Iterator<Object>{

        private Node currentNode = head;  
        private int nextIndex = 0;//参考源码中的写法  
          
        @Override  
        public Object next() {  
              
            Object data = currentNode.data;  
            currentNode = currentNode.next;  
            nextIndex ++;  
            return data;  
        }  
  
        @Override  
        public boolean hasNext() {  
            return nextIndex != size;  
        }  
          
    }  
		
    
    
    
    
    
    
    
    
    
    
    
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		 if (head!=null) {
				//上一结点
			  Node pre=head;

			//当前结点
			  Node cur=head.next;

			//用于存储下一节点
			  Node tem;

			//cur==null 即尾结点
			  while(cur!=null){

			//下一节点存入临时结点
			   tem=cur.next;

			//将当前结点指针指向上一节点
			   cur.next = pre;

			//移动指针
			   pre=cur;
			   cur=tem;
			  }
			  head.next = null;
			}


		//reverse(head);
	}
	private Node reverse(Node first) {
		// first看作是前一结点，first.next是当前结点，reHead是反转后新链表的头结点  
        if (first == null || first.next == null) {  
            return head;// 若为空链或者当前结点在尾结点，则直接还回  
        }  
        Node reHead = reverse(first.next);// 先反转后续节点head.getNext()  
        first.next.next = first;// 将当前结点的指针域指向前一结点  
        first.next = null;// 前一结点的指针域令为null;  
        return reHead;// 反转后新链表的头结点 
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf(){
		int removeLength = size()/2;
		for (int i = 0; i < removeLength; i++) {
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int index, int length){
		for (int i = 0; i < length; i++) {
			this.remove(index);
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
		int[] result = new int[list.size()];
		Iterator iterator = list.iterator();
		int offset = 0;
		while (iterator.hasNext()) {
			int temp = (int) this.get((int)iterator.next());
			result[offset++] = temp;
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	
	public void subtract(LinkedList list){
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			int temp = (int) iterator.next();
			Node x = head;
			Node p = null;
			while (x.next != null) {
				if ((int)x.data > temp) {
					break;
				}
				if((int)x.data == temp){
					if (p == null) {
						removeFirst();
						x = head;
					} else {
						p.next = x.next;
					}
                }
				p = x;
				x = x.next;
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node p = head;
		while(p.next != null){
			Node x = p;
			while (x.next != null) {
				if(x.next.data == p.data){
                    x.next=x.next.next;
                }
                x=x.next;
			}
			p = p.next;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Node p = head;
		int i = (int)p.data;
		if (i > min && i < max) {
			while ((int)p.data > min) {
				if ((int)p.data >= max) {
					break;
				}
				removeFirst();
				p = head;
			}
		} else {
			while (p.next != null) {
				int temp = (int) p.next.data;
				if (temp >= max) {
					break;
				}
				if (temp > min) {
					p.next=p.next.next;
				}
				p = p.next;
			}
		}
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