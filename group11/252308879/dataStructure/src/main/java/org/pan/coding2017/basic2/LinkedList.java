package org.pan.coding2017.basic2;

import org.pan.coding2017.basic.Iterator;
import org.pan.coding2017.basic.List;

public class LinkedList implements List {

	private Node head;
	private Node tail;
	private int size=0;

	private void checkIndex(int index){
		if(index<0 || index>=this.size)
		{
			throw new IndexOutOfBoundsException("Error!Invalid index:"+index);
		}
	}

	private void checkSize(){
		if(size==0)
		{
			throw new RuntimeException("Empty LinkedList.");
		}
	}

	public void add(Object o){
		Node temp = new Node(o,null);
		if(this.head==null){
			this.head = temp;
			this.tail = head;
		}else{
			this.tail.next = temp;
			this.tail = temp;
		}
		this.size++;
	}

	/* (non-Javadoc)
	 * @see com.coding.basic2.List#add(int, java.lang.Object)
	 */
	public void add(int index , Object o){
		checkIndex(index);
		if(index==0)
		{
			Node newNode = new Node(o,head);
			head = newNode;
		}else{
			Node temp = head;
			for(int i=1;i<index;i++){
				temp = head.next;
			}
			Node newNode = new Node(o,temp.next);
			temp.next = newNode;
		}
		this.size++;
	}
	public Object get(int index){
		checkIndex(index);
		if(index==0)
		{
			return this.head.data;
		}else if(index==this.size-1)
		{
			return this.tail.data;
		}else{
			Node temp = head;
			for(int i=0;i<index;i++)
			{
				temp = temp.next;
			}
			return temp.data;
		}
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(this.size>0){
			Node temp = head;
			sb.append(temp.data);
			while(temp.hasNext()){
				temp = temp.next;
				sb.append(",");
				sb.append(temp.data);
			}
		}
		return sb.toString();
	}

	public Object remove(int index){
		checkIndex(index);
		Node temp = head;
		Node pre = head;
		Object result;
		if(index == 0)
		{
			result = head.data;
			head = head.next;
		}else{
			for(int i=0;i<index;i++)
			{
				if(i>0)
				{
					pre=pre.next;
				}
				temp=temp.next;
			}
			result = temp.data;
			pre.next=temp.next;
			temp = null;


			if(index == size-1)
			{
				tail = pre;
			}
		}
		size--;

		return result;
	}

	public int size(){
		return this.size;
	}

	public void addFirst(Object o){
		Node temp = new Node(o,head);
		head = temp;
		size++;

	}
	public void addLast(Object o){
		Node temp = new Node(o,null);
		tail.next = temp;
		tail = temp;
		size++;
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator{
		private Node current = head;

		@Override
		public boolean hasNext()
		{
			return current!=null;
		}

		@Override
		public Object next() {
			if(current==null)
			{
				throw new RuntimeException("Current element has not next.");
			}

			Object result = current.data;
			current = current.next;
			return result;
		}

		@Override
		public void remove() {

		}

	}


	private static  class Node{
		Object data;
		Node next;
		public boolean hasNext(){
			return (this.next!=null);
		}

		public Node(Object data,Node next){
			this.data=data;
			this.next=next;
		}
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		checkSize();
		int tempSize = size;
		Node temp = new Node(removeFirst(),null);
		tail = temp;
		while(size>0){
			temp = new Node(removeFirst(),temp);
		}
		head = temp;
		size = tempSize;
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		checkSize();
		if(size>1)
		{
			int temp = size;
			for(int i=0;i<temp/2;i++){
				removeFirst();
			}
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		checkSize();
		if(length>size-i)
		{
			throw new RuntimeException("No enough size to remove from index:"+i);
		}else{
			for(int j=0;j<length;j++)
			{
				remove(i);
			}
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
		if(size==0||list.size==0){
			return new int[0];
		}else{
			int[] result = new int[list.size()];
			Node temp = head;
			int currentPos = 0;
			for(int i=0;i<list.size();i++)
			{
				int pos = (int)list.get(i);
				while(temp!=null && currentPos<=pos)
				{
					if(currentPos<pos){
						temp = temp.next;
						currentPos++;
						continue;
					}else{
						result[i] = (int)temp.data;
						temp = temp.next;
						currentPos++;
						break;
					}
				}
			}
			return result;
		}
	}
	private static String concatArray(int[] array){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<array.length;i++)
		{
			sb.append(array[i]).append(",");
		}
		return sb.toString();
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素

	 * @param list
	 */

	public  void subtract(LinkedList list){
		for(int i=0;i<list.size();i++)
		{
			int val = (int)list.get(i);

			for(int j=0;j<size();j++){
				int tempVal = (int)get(j);
				if(tempVal>val){
					break;
				}else if(tempVal==val){
					remove(j);
					break;
				}else{
					continue;
				}
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node temp = head;
		while(temp!=null){
			while(temp.hasNext()&&temp.data.equals(temp.next.data))
			{
				temp.next = temp.next.next;
				size--;
			}
			temp = temp.next;
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		int headVal = (int)head.data;
		int tailVal = (int)tail.data;
		//if all the values in linkedList fall into the range, clean up;
		if(min<=headVal && max>=tailVal)
		{
			System.out.println("min<=headVal && max>=tailVal");
			head = null;
			tail = null;
			size = 0;
		}else{
			Node preRange = null;
			Node sufRange = null;
			Node temp = head;
			int counter = 0;
			while(temp!=null){
				if((int)temp.data<min && temp.hasNext() && (int)temp.next.data>=min)
				{
					preRange = temp;
					System.out.println("Found preRange node, val="+temp.data+",next val="+temp.next.data);
				}
				if((int)temp.data>max){
					sufRange = temp;
					System.out.println("Found sufRange node, val="+temp.data+",next val="+(temp.hasNext()?temp.next.data:null));
					break;
				}
				if((int)temp.data>=min && (int)temp.data<=max)
				{
					counter++;
				}
				System.out.println("Counter="+counter);
				temp = temp.next;
			}
			if(min<=headVal){
				head = sufRange;
			}
			if(max>=tailVal){
				tail = preRange;
			}
			if(preRange!=null){
				preRange.next = sufRange;
			}
			size -= counter;
		}

	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		if(size==0 || list==null || list.size()==0)
		{
			return new LinkedList();
		}else{
			int pos1=0;
			int pos2=0;
			LinkedList result = new LinkedList();
			while(pos1<size||pos2<list.size())
			{
				int val1 = (int)get(pos1);
				int val2 = (int)list.get(pos2);
				if(val1==val2)
				{
					result.add(val1);
					if(pos1<size-1 && pos2<list.size()-1){
						pos1++;
						pos2++;
					}else{
						break;
					}
				}else if(val1>val2)
				{
					if(pos2<list.size()-1){
						pos2++;
						continue;
					}else{
						break;
					}
				}else if(val1<val2)
				{
					if(pos1<size-1)
					{
						pos1++;
						continue;
					}else{
						break;
					}
				}
			}
			return result;
		}
	}
}
