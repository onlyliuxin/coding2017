package cn.net.pikachu.basic;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList implements List {

	private Node head;
	private int curSize = 0;

	private class Itr implements Iterator {
		Node curNode = head;

		@Override
		public boolean hasNext() {
			return curNode != null;
		}

		@Override
		public Object next() {
			Node node = curNode;
			curNode = curNode.next;
			return node.data;
		}
	}

	public void add(Object o) {
		if (head == null) {
			head = new Node(o, null);
		} else {
			Node node = head;
			while (node.next != null) {
				node = node.next;
			}
			node.next = new Node(o, null);
		}
		curSize++;
	}

	public void add(int index, Object o) {
		// 这里可以等于
		if (index < 0 || index > curSize) {
			throw new IndexOutOfBoundsException(String.valueOf(index));
		}
		if (index == 0) {
			addFirst(o);
		} else {

			Node node = head;
			for (int i = 1; i < index; i++) {
				node = node.next;
			}
			node.next = new Node(o, node.next);
			curSize++;
		}

	}

	public Object get(int index) {
		if (index < 0 || index >= curSize) {
			throw new IndexOutOfBoundsException(String.valueOf(index));
		}
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.data;
	}

	public Object remove(int index) {
		if (index < 0 || index >= curSize) {
			throw new IndexOutOfBoundsException(String.valueOf(index));
		}
		if (index == 0) {
		    Object o = head.data;
		    head=head.next;
		    curSize--;
		    return o;
		}

		Node node = head;
		for (int i = 1; i < index; i++) {
			node = node.next;
		}
		Node t = node.next;
		node.next=t.next;
		curSize--;
		return t.data;
	}

	public int size() {
		return curSize;
	}

	public void addFirst(Object o) {
		Node node = new Node(o, head);
		head = node;
		curSize++;
	}

	public void addLast(Object o) {
		add(o);
	}

	public Object removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node node = head;
		head = head.next;
		curSize--;
		return node.data;
	}

	public Object removeLast() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node node;
		if (head.next == null) {
			node = head;
			head = null;
		} else {
			node = head;
			while (node.next.next != null) {
				node = node.next;
			}
			Node t = node.next;
			node.next = null;
			node = t;
		}
		curSize--;
		return node.data;
	}

	// 后面再实现
	public Iterator iterator() {
		return new Itr();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		Node node = head;
		while (node != null) {
			builder.append(node.data).append(",");
			node = node.next;
		}
		if (curSize>0)
			builder.deleteCharAt(builder.length() - 1);
		builder.append("]");
		return builder.toString();
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		if (head == null){
		    return;
        }
        Node node = head;
		head=head.next;
		node.next=null;
		while (head!=null){
		    Node t = head;
            head= head.next;
		    t.next=node;
		    node=t;
        }
        head=node;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int count = (curSize)/2;
        for (int i = 0; i < count; i++) {
            head=head.next;
        }
    }
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
	    if (length<=0){
	        throw new IllegalArgumentException("length = "+length);
        }
		if (i+length > size()){
		    throw new IndexOutOfBoundsException(String.valueOf(i+length));
        }
        if (i==0){
            for (int j = 0; j < length; j++) {
                head=head.next;
            }
            return;
        }
        Node node = head;
        for (int j = 1; j < i; j++) {
            node=node.next;
        }
        for (int j = 0; j < length; j++) {
            if (node.next!=null){
            node.next=node.next.next;
            }
        }
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
        if (list==null){
            return null;
        }
        if (list.size()==0){
            return new int[0];
        }
        int[] a = new int[list.size()];
        int curIndex = 0;
        Node curNode = head;
        for (int i = 0; i < list.size(); i++) {
            int index = (int) list.get(i);
            while (curIndex<index){
                curIndex++;
                curNode=curNode.next;
            }
            a[i]= (int) curNode.data;
        }
        return a;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
	    if (list==null || list.size()==0){
	        return;
        }
		int curIndex = 0;
	    int i = 0;
        while (i<list.size()) {

            // 没有实现Comparable比较难办 下次写成泛型好了
            // 这次默认只比较整数
            int a = (int) get(curIndex);
            int b = (int) list.get(i);
            if (Objects.equals(a,b)){
                remove(curIndex);
            }else if (a<b){
                curIndex++;
            }else if (a>b){
                i++;
            }
        }
    }
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node node = head;
		Node tempHead = node;
		Node t = tempHead;
		while (node.next!=null){
		    node=node.next;
            if (!Objects.equals(node.data,t.data)){
		        t.next=node;
		        t=t.next;
            }
        }
        t.next=null;
        head=tempHead;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
	    if (min>=max){
	        throw new IllegalArgumentException("min = "+min+", max = "+max);
        }
		int curIndex = 0;
		Node node = head;
		while (curIndex<size()){
		    if ((int)node.data>min && (int)node.data<max){
		        node=node.next;
		        remove(curIndex);
            }else {
		        curIndex++;
		        node=node.next;
            }
        }
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
	    if (list==null){
	        return null;
        }
        if (list.size()==0){
            System.out.println("0");
            return new LinkedList();
        }
        LinkedList l = new LinkedList();
        int i=0;
        int j=0;
        while (i<size() && j<list.size()){
            int a = (int) get(i);
            int b = (int) list.get(j);
            if (a == b){
                l.add(a);
                i++;
                j++;
            }else if (a<b){
                i++;
            }else {
                j++;
            }
        }
		return l;
	}
}
