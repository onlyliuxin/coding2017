package com.ace.coding;

import java.lang.reflect.Array;

public class LinkedList implements List {
	private Node head = null;
	private int size = 0;
	
	public void add(Object o){
		Node newNode = new Node();
		newNode.data = o;
		if(head == null){
			head = newNode;
		} else {
			Node pNode = head;
			while(pNode.next != null){
				pNode = pNode.next;
			}
			pNode.next = newNode;
		}
		size++;
	}
	
	public void add(int index , Object o){
		checkLinkedListIndex(index);
		
		Node newNode = new Node();
		newNode.data = o;
		Node pNode = getNode(index);
		newNode.next = pNode.next;
		pNode.next = newNode;
		
		size++;
	}
	
	private Node getNode(int index){
		Node pNode = head;
		for(int i = 0; i < index; i++){
			pNode = pNode.next;
		}
		return pNode;
	}
	
	public Object get(int index){
		Node pNode = getNode(index);
		return pNode.data;
	}
	public Object remove(int index){
		checkLinkedListIndex(index);
		if(index == 0){
		    return removeFirst();
        }

		Node pNode = head;
		for(int i = 0; i < index - 1; i++){
			pNode = pNode.next;
		}
		Node tempNode = getNode(index);
		pNode.next = tempNode.next;
		size--;
		return tempNode.data;
	}
	
	
	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = head;
		head = newNode;
		size++;
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		Node pNode = getNode(size() - 1);
		pNode.next = newNode;
		size++;
	}
	public Object removeFirst(){
		Node pNode = head;
		head = pNode.next;
		size--;
		return pNode.data;
	}
	public Object removeLast(){
		Object obj = remove(size() - 1);
		return obj;
	}
	
	public int size(){
		return size;
	}
	
	private void checkLinkedListIndex(int index){
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException("The index " + index + " is invalid.");
		}
	}
	
	public Iterator iterator(){
		return null;
//		return new ListIterator();
	}
	
	/*private class ListIterator implements Iterator{
		private Node pNode = head;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return pNode.next != null;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			Object obj = pNode.data;
			pNode = pNode.next;
			return obj;
		}
		
	}*/
	
	private static class Node{
		Object data;
		Node next;
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
	    Node headNode = head;
        Node newNode = null;

        reverseNode(headNode, newNode);
    }

	private void reverseNode(Node headNode, Node newHead){
		if(headNode != null){
			return ;
		}

		Node next = headNode.next;
        headNode.next = newHead;

        if(next == null){
            return ;
        }

		reverseNode(next, headNode);
	}

	private void checkLinkedListSize(){
        if(this.size() <= 0){
            throw new IndexOutOfBoundsException();
        }
    }


	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		//check empty
        checkLinkedListSize();
		int count = 1;
		Node pNode = head;
		for (int i = 0; i < size() / 2 - 1; i++) {
			pNode = pNode.next;
			count++;
		}
		head = pNode.next;
		size = size() - count;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		//check empty
		// check i and length are validate
        checkLinkedListSize();
        checkLinkedListIndex(i);
        checkLinkedListIndex(i+length);

        if(i == 0){
            Node pNode = getNode(length - 1);
            head = pNode.next;
        } else {
            Node pNode = getNode(i - 1);
            Node tempNode = getNode(i + length - 1);
            pNode.next = tempNode.next;
        }
		size = size - length;
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
	    int[] newArray = new int[list.size()];
	    ArrayList newArrayList = new ArrayList();
        for(int i = 0; i < list.size(); i++){
        	newArray[i] = (int)this.get((int)list.get(i));
        }

		return newArray;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素
	 * @param list
	 */

	public  void subtract(LinkedList list){
        ArrayList arrayList = new ArrayList();
        for(int i = 0; i < this.size(); i++){
            for(int j = 0; j < list.size(); j++){
                if(this.get(i) == list.get(j)){
                    arrayList.add(i);
                    break;
                }
            }
        }

        for(int k = 0; k < arrayList.size(); k++){
            this.remove((int)arrayList.get(k) - k);
        }
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
        ArrayList arrayList = new ArrayList();
        for(int i = 0; i < this.size() - 1; i++){
            if(this.get(i) == this.get(i+1)){
                arrayList.add(i);
            }
        }

        for(int k = 0; k < arrayList.size(); k++){
            this.remove((int)arrayList.get(k) - k);
        }
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
	    ArrayList newArrayList = new ArrayList();
	    int count = 0;
	    int start = 0;
	    int end = 0;
        for (int i = 0; i < this.size(); i++) {
        	if(min >= (int)this.get(i)){
        		start = i+1;
			}
			if(max >= (int)this.get(i)){
        		end = i;
			}
        }
		this.remove(start, end-start);
	}


	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
	    LinkedList newLinkedList = new LinkedList();
	    int l1 = 0;
	    int l2 = 0;
	    while(l1 < this.size() && l2 < list.size()){
	    	if(this.get(l1) == list.get(l2)){
	    		newLinkedList.add(this.get(l1));
	    		l1 ++;
	    		l2 ++;
			} else if ((int)this.get(l1) > (int)list.get(l2)){
	    		l1 ++;
			} else {
				l2 ++;
			}
		}

        return newLinkedList;
	}

	private boolean contains(Object obj){
	    for(int i = 0; i < this.size(); i++){
	        if(this.get(i) == obj){
	            return true;
            }
        }
        return false;
    }

}
