package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		if(this.size == 0){//size为0，给head赋值
			this.head = new Node(o);
			size++;
		}else{//将o加至链表末尾
			Node Last = new Node(o);
			Node pres = this.head;
			while(pres.next != null){
				pres = pres.next;
			}
			pres.next = Last;
			size++;
		}
	}
	
	public void add(int index , Object o){//index:0~size
		if(index < 0 || index > this.size){//index小于0or大于size，参数错误
			return;
		}
		if(index == 0){//将o加至链表头部
			//size为0时，index必为0，执行该分支
			Node first = new Node(o);
			first.next = this.head;
			this.head = first;
			size++;
		}else if(index == size){//将o加至链表末尾
			//index == size != 0时，执行该分支
			Node Last = new Node(o);
			Node pres = this.head;
			while(pres.next != null){
				pres = pres.next;
			}
			pres.next = Last;
			size++;
		}else{
			//0<index<size时，执行该分支
			Node pres = this.head;//pres指向0
			for(int i = 0;i < index-1;i++){
				pres = pres.next;
			}
			
			//此时pres指向index-1
			Node insert = new Node(o);
			insert.next = pres.next;
			pres.next = insert;
			size++;
		}
	}
	
	public Object get(int index){//index:0~size-1
		if(index < 0 || index >= this.size){//index小于0or大于等于size，参数错误
			return null;
		}
		
		Node pres = this.head;//pres指向0
		for(int i = 0;i < index;i++){
			pres = pres.next;
		}
		//此时pres指向index
		return pres.data;
	}
	
	public Object remove(int index){//index:0~size-1
		if(index < 0 || index >= this.size){//index小于0or大于等于size，参数错误
			return null;
		}
		
		Object o = null;
		if(index == 0){//删除头节点
			o = this.head.data;
			this.head = this.head.next;
			size--;
		}else{//删除头节点以外的其他节点
			Node pres = this.head;//pres指向0
			for(int i = 0;i < index-1;i++){
				pres = pres.next;
			}
			
			//此时pres指向index-1
			o = pres.next.data;
			pres.next = pres.next.next;
			size--;
		}
		return o;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){//同add(int 0 , Object o)
		Node first = new Node(o);
		first.next = this.head;
		this.head = first;
		size++;
	}
	
	public void addLast(Object o){//同add(int size , Object o)
		if(this.size == 0){
			this.head = new Node(o);
			size++;
		}else{//size>=1
			Node Last = new Node(o);
			Node pres = this.head;
			while(pres.next != null){
				pres = pres.next;
			}
			pres.next = Last;
			size++;
		}
	}
	
	public Object removeFirst(){//同remove(int 0)
		if(this.size == 0){
			return null;
		}
		
		Object o = this.head.data;
		this.head = this.head.next;
		size--;
		return o;
	}
	
	public Object removeLast(){
		if(this.size == 0){
			return null;
		}
		
		Object o = null;
		if(this.size == 1){//size==1
			o = this.head.data;
			this.head = null;
			this.size--;
		}else{//size>=2
			Node pres = this.head;
			while(pres.next.next != null){
				pres = pres.next;
			}
			o = pres.next.data;
			pres.next = null;
			size--;
		}
		return o;
	}
	
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	
	private static class LinkedListIterator implements Iterator{
//		private LinkedList list;
		private Node pres;
		
		public LinkedListIterator(LinkedList list) {
			super();
//			this.list = list;
			this.pres = list.head;
		}

		@Override
		public boolean hasNext() {
			if(this.pres != null){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public Object next() {
			Object o = this.pres.data;
			pres = pres.next;
			return o;
		}
		
	} 
	
	private static class Node{
		Object data;
		Node next;
		
		public Node(Object data) {
			super();
			this.data = data;
		}
	}
	
	public String toString(){
		String result = "[";
		if(this.size == 0){
			result = result + "]";
			return result;
		}else{
			Node pres = this.head;
			while(pres != null){
				result = result + pres.data + ",";
				pres = pres.next;
			}
			result = result.substring(0,result.length()-1);
			result = result + "]";
			return result;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){		
		if(this.size <= 1){
			return;
		}
		Node before = null;
		Node pres = this.head;
		Node after = pres.next;
		while(after != null){
			pres.next = before;
			before = pres;
			pres = after;
			after = after.next;
		}
		//此时pres指向最后一个节点
		pres.next = before;
		this.head = pres;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf(){
		if(this.size <= 1){
			return;
		}
		Node pres = this.head;
		Node temp = pres;
		for(int i = 0;i < this.size / 2;i++){
			temp = pres;
			pres = pres.next;
			temp.data = null;
			temp.next = null;
		}
		this.head = pres;
		this.size = this.size - this.size / 2;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param index
	 * @param length
	 */
	public void remove(int index, int length){//若length太大，size不够，则取到末尾
		if(index < 0 || index >= this.size || length <= 0){//index小于0or大于等于size，length小于等于0，参数错误
			return;
		}
		if(this.size <= 0){
			return;
		}
		if(index == 0){
			//此时index=0&&length>0&&size>0
			Node temp = this.head;
			for(int i = 0;i < length;i++){
				temp = temp.next;
				if(temp == null){
					break;
				}
			}
			this.head = temp;
			if(temp == null){
				this.size = 0;
			}else{
				this.size = this.size - length;
			}
		}else{
			//此时0<index<size&&length>0&&size>0
			Node start = this.head;
			for(int j = 0;j < index-1;j++){
				start = start.next;
			}//start指向index-1
			
			Node end = start;
			for(int l = 0;l <= length;l++){
				end = end.next;
				if(end == null){
					break;
				}
			}//end指向index+length
			start.next = end;
			if(end == null){
				this.size = index;
			}else{
				this.size = this.size - length;
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
	public int[] getElements(LinkedList list){//若listB中的index不在0~this.size-1之中，则返回-1
		if(this.size <= 0 || list == null || list.size <= 0){
			return null;
		}
		
		int[] result = new int[list.size()];
		Node presIndex = list.head;
		int index = (int)presIndex.data;
		for(int i = 0;i < list.size();i++){
			if(index < 0 || index >= this.size){
				result[i] = -1;
			}else{//index:0~this.size-1
				result[i] = (int)this.get(index);
			}
			presIndex = presIndex.next;
			if(presIndex != null){
				index = (int)presIndex.data;
			}
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 
	 * @param list
	 */
	public void subtract(LinkedList list){//当前链表以及参数列表均递增有序，可有重复值
		if(this.size == 0 || list == null || list.size() == 0){
			return;
		}
		
		//头节点的删除比较特殊，先不予考虑
		Node thisPres = this.head.next;//指向被删除节点
		Node thisPresBefore = this.head;//指向被删除节点的前一个节点
		Node listPres = list.head;
		while(thisPres != null && listPres != null){
			if((int)thisPres.data > (int)listPres.data){
				listPres = listPres.next;
			}else if((int)thisPres.data < (int)listPres.data){
				thisPresBefore = thisPresBefore.next;
				thisPres = thisPres.next;
			}else{//(int)thisPres.data == (int)listPres.data
				thisPresBefore.next = thisPres.next;
				thisPres = thisPres.next;
				this.size--;
			}
		}
		
		//最后考虑头节点的删除情况
		Node first = this.head;
		Node listPresTwo = list.head;
		while((int)first.data > (int)listPresTwo.data){
			listPresTwo = listPresTwo.next;
		}
		if((int)first.data == (int)listPresTwo.data){//删除头节点
			this.head = this.head.next;
			this.size--;
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		if(this.size <= 1){
			return;
		}
		
		Node start = this.head;
		Node end = start.next;
		while(end != null){
			if((int)start.data != (int)end.data){
				start = end;
				end = end.next;
			}else{//start.data == end.data
				while((int)start.data == (int)end.data){
					end = end.next;
					if(end == null){
						break;
					}
				}
				start.next = end;
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max){
		if(this.size == 0 || max - min < 2 || (int)this.head.data >= max){
			return;
		}
		
		//this.size >= 1 && max - min >= 2 && this.head.data < max
		int thisHeadData = (int)this.head.data;
		if(thisHeadData > min && thisHeadData < max){
			//this.size >= 1 && max - min >= 2 && 
			//min < this.head.data < max
			//找到新的头节点即可，this.size减小
			Node notSmallToMax = this.head.next;
			int sizeDec = 1;
			while(notSmallToMax != null){
				if((int)notSmallToMax.data >= max){
					break;
				}
				notSmallToMax = notSmallToMax.next;
				sizeDec++;
			}
			this.head = notSmallToMax;
			this.size = this.size - sizeDec;
		}else{
			//this.size >= 1 && max - min >= 2 && 
			//this.head.data <= min
			Node startBefore = this.head;//第一个>min节点的前一个节点
			Node start = startBefore.next;//第一个>min的节点
			while(start != null){
				if((int)start.data > min){
					break;
				}
				startBefore = start;
				start = start.next;
			}
			if(start == null || (int)start.data >= max){
				//链表中不存在满足删除条件的元素
				return;
			}
			
			//至少有一个元素需要被删除
			int sizeDec = 1;
			Node end = start;//最后一个<max的节点
			Node endAfter = end.next;//最后一个<max节点的后一个节点
			while(endAfter != null){
				if((int)endAfter.data >= max){
					break;
				}
				end = endAfter;
				endAfter = endAfter.next;
				sizeDec++;
			}
			startBefore.next = endAfter;
			this.size = this.size - sizeDec;
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList intersection(LinkedList list){
		if(list == null || list.size() == 0){
			return 	new LinkedList();
		}
		if(this.size == 0){
			return new LinkedList();
		}
		LinkedList result = new LinkedList();
		Node thisPres = this.head;
		Node listPres = list.head;
		while(thisPres != null && listPres != null){
			if((int)thisPres.data < (int)listPres.data){
				thisPres = thisPres.next;
			}else if((int)thisPres.data > (int)listPres.data){
				listPres = listPres.next;
			}else{
				//(int)thisPres.data == (int)listPres.data
				result.add(thisPres.data);
				thisPres = thisPres.next;
				listPres = listPres.next;
			}
		}
		return result;
	}
}
