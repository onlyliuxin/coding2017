package com.coding.basic2;

import static org.junit.Assert.assertEquals;

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
	
	private class LinkedListIterator implements Iterator
	{
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
//--Method One
//			for(int i=0;i<list.size();i++){
//				int pos = (int)list.get(i);
//				result[i]=(int)get(pos);
//			}
//--Method Two
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
//--Method two ends
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		LinkedList ll = new LinkedList();
		ll.add("aaa");
		System.out.println("add(aaa) "+ll+" ; size="+ll.size());
		ll.add("bbb");
		System.out.println("add(bbb) "+ll+" ; size="+ll.size());
		ll.add("ddd");
		System.out.println("add(ddd) "+ll+" ; size="+ll.size());
		ll.add(1,"ccc");
		System.out.println("add(1, ccc) "+ll+" ; size="+ll.size());
		ll.add(0,"xxx");
		System.out.println("add(0, xxx) "+ll+" ; size="+ll.size());
		ll.addFirst("yyy");
		System.out.println("addFirst(yyy) "+ll+" ; size="+ll.size());
		ll.addLast("zzz");
		System.out.println("addLast(1, zzz) "+ll+" ; size="+ll.size());
		ll.remove(3);
		System.out.println("remve(3) "+ll+" ; size="+ll.size());
		ll.removeFirst();
		System.out.println("removeFirst() "+ll+" ; size="+ll.size());
		ll.removeLast();
		System.out.println("removeLast() "+ll+" ; size="+ll.size());
		System.out.println("get(0) "+ll.get(0));
		System.out.println("get(1) "+ll.get(1));
		System.out.println("get(size-1) "+ll.get(ll.size()-1));
		ll.reverse();
		System.out.println("reverse() "+ll+" ; size="+ll.size());
		ll.add("111");
		ll.add("222");
		ll.add("333");
		System.out.println("add(111,222,333) "+ll+" ; size="+ll.size());
		ll.removeFirstHalf();
		System.out.println("removeFirstHalf() "+ll+" ; size="+ll.size());
		ll.remove(0, 1);
		System.out.println("remove(0,1) "+ll+" ; size="+ll.size());
		//--------------
		LinkedList ints = new LinkedList();
		ints.add(0);
		ints.add(1);
		ints.add(2);
		ints.add(3);
		LinkedList pos = new LinkedList();
		pos.add(1);
		pos.add(2);
		pos.add(3);
		pos.add(0);
		System.out.println(concatArray(ints.getElements(pos)));
		ints.subtract(pos);
		System.out.println("subtract(1,2,3,0) "+ints+" ; size="+ints.size());
		//===============
		LinkedList dupVals = new LinkedList();
		dupVals.add(0);
		dupVals.add(1);
		dupVals.add(1);
		dupVals.add(1);
		dupVals.add(1);
		dupVals.add(2);
		dupVals.add(4);
		dupVals.add(4);
		dupVals.add(4);
		dupVals.removeDuplicateValues();
		System.out.println("removeDuplicateValues() "+dupVals+" ; size="+dupVals.size());
		//==============
		LinkedList rmRange = new LinkedList();
		rmRange.add(0);
		rmRange.add(1);
		rmRange.add(2);
		rmRange.add(3);
		rmRange.add(4);
		rmRange.add(5);
		rmRange.add(6);
		rmRange.add(7);
		rmRange.add(8);
		rmRange.add(9);
		rmRange.removeRange(6, 10);
		System.out.println("removeRange() "+rmRange+" ; size="+rmRange.size());
		//----------
		LinkedList intersect1 = new LinkedList();
		intersect1.add(0);
		intersect1.add(1);
		intersect1.add(2);
		intersect1.add(3);
		intersect1.add(5);
		intersect1.add(7);
		LinkedList intersect2 = new LinkedList();
		intersect2.add(1);
		intersect2.add(2);
		intersect2.add(3);
		intersect2.add(5);
		LinkedList intersect3 = intersect1.intersection(intersect2);
		System.out.println("intersection() "+intersect3 + "; size="+intersect3.size()); 
		intersect3 = intersect2.intersection(intersect1);
		System.out.println("intersection() "+intersect3 + "; size="+intersect3.size()); 
		//===============
		LinkedList ll1 = new LinkedList();
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		ll1.add(4);
		LinkedList ll2 = new LinkedList();
		ll2.add(0);
		ll2.add(2);
		ll2.add(3);
		ll1.subtract(ll2);
		System.out.println(ll1 + "; size="+ll1.size()); 
	}
}
