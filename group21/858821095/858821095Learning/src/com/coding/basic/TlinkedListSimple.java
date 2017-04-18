package com.coding.basic;

public class TlinkedListSimple implements Tlist {
	
	private int size=0;
	private Node  head = new Node(null, null);
	
	public void add(Object o){
		Node node = new Node(o, null);
		Node pNode = head;
		while(pNode.next!=null){
			pNode=pNode.next;
		}
		pNode.next=node;
		size++;
	}

	private Node getNode(int index){
		if(index<0 || index >=size)
			throw new IndexOutOfBoundsException("Index: " + index+", Size:"+size);
		Node pNode = head.next;
		for(int i=0;i<index;i++){
			pNode=pNode.next;
		}
		return pNode;
	}
	public void add(int index , Object o){
		Node node = new Node(o, null);
		if(index == 0){
			node.next=head.next;
			head.next=node;
		}else{
			
			Node pNodePre = getNode(index-1);
			node.next=pNodePre.next;
			pNodePre.next=node;
		}
		size++;
	}
	
	
	public Object get(int index){
		return getNode(index).data;
	}
	public Object remove(int index){
		Node pNode=getNode(index);
		Object ele = pNode.data;
		if(index==0){
			head.next=pNode.next;
		}else{
			Node pNodePre = getNode(index - 1);
			pNodePre.next=pNode.next;
		}
		pNode.next=null;
		pNode.data=null;
		size--;
		return ele;
		
	}
	public void remove(Node pre, Node next){
		if(next.next==null){
			pre.next=null;
		}else{
			pre.next=next.next;
		}
		size--;
	}

	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o, null);
		node.next=head.next;
		head.next=node;
		size++;
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		Node pNode = head.next;
		Object ele = pNode.data;
		head.next=pNode.next;
		pNode.next=null;
		pNode.data=null;
		size--;
		return ele;
	
	}
	public Object removeLast(){
		if(size<1)
			return null;
		Node pNode=getNode(size-1);
		Object ele = pNode.data;
		Node pNodePre=getNode(size-2);
		pNodePre.next=null;
		pNode.next=null;
		pNode.data=null;
		size--;
		return ele;
	
	}
	
	private static  class Node{
		Object data;
		Node next;
		Node(Object data,Node next){
			this.data = data;
			this.next=next;
		}
	}
	public TIterator iterator(){
		return new TIterator(this);
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		if(size==0 || size==1)
	    	  return;

	        Node preNode = head.next;
	        Node nextNode = preNode.next;
	        Node cNode = null;
	        while (nextNode != null) {
	        	cNode = nextNode.next;
	        	nextNode.next=preNode;
	            preNode = nextNode;
	            nextNode=cNode;
	        }
	        head.next = preNode;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		if(size<2)
			return;
		int n=size/2;
		Node node = getNode(n);
		size-=n;
		head.next=node;
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		int len = length+i;
		if(size<len)
			throw new IndexOutOfBoundsException("OutOfBounds");
		
		if(i==0){
			Node nextNode = getNode(len);
			head.next=nextNode;
			size-=length;
			return;
		}
		Node preNode = getNode(i-1);
		if(len==size){
			preNode.next=null;
			size-=length;
			return;
		}
		Node nextNode = getNode(len);
		preNode.next=nextNode;
		size-=length;
		
	}
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(TlinkedListSimple list){
		if(size<0 ||list.size()>size)
			return new int[]{};
		int newlen = list.size();
		int[] newArray = new int[newlen];
		for(int i=0;i<newlen;i++){
			int lenb = (int) list.get(i);
			if(lenb>size || lenb<0)
				return null;
			newArray[i] = (int) get(lenb);
		}
		return newArray;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(TlinkedListSimple list){
		int len =list.size();
		if(size<0 ||len>size)
			return;
		
		for(int i=0;i<len;i++){
			int lenb = (int) list.get(i);
			if(lenb>size || lenb<0)
				return;
			this.remove(lenb-i);
		}
		
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if(size==0||size==1)
			return;
		Node preNode=head.next;
		Node nextNode=preNode.next;
		//Node node = null;
		while(nextNode!=null){
			//node=nextNode.next;
			if(nextNode.data==preNode.data){
				this.remove(preNode,nextNode);
			}else{
				preNode=nextNode;
			}
			nextNode=nextNode.next;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Node node = head.next;
		int i=0;
		while(node!=null){
			if((int)node.data < min){
				node=node.next;
				i++;
			}else{
				break;
			}
		}
		Node preNode = getNode(i);
		int start=i;

		
		while(node!=null){
			if((int)node.data < max){
				i++;
				node=node.next;
			}else{
				break;
			}
		}
		Node nextNode = getNode(i);
		int end=i;
		preNode.next=nextNode;
		int len=end-start-1;
		size-=len;
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  TlinkedListSimple intersection( TlinkedListSimple list){

		TlinkedListSimple ll = new TlinkedListSimple();
		TIterator listit = list.iterator();
		TIterator it = this.iterator();
		int itn = (int) it.next();
		int listitn = (int) listit.next();
		while(it.hasNext() && listit.hasNext()){
			if(itn<listitn){
				ll.add(itn);
				itn=(int) it.next();
			}else if(itn>listitn){
				ll.add(listitn);
				listitn=(int) listit.next();
			}else{
				ll.add(itn);
				itn=(int) it.next();
				listitn=(int) listit.next();
			}
		}

		if(itn<listitn){
			ll.add(itn);
			ll.add(listitn);
		}else if(itn>listitn){
			ll.add(listitn);
			ll.add(itn);
		}else{
			ll.add(listitn);
		}
		
		if(listit.hasNext()){
			while(listit.hasNext()){
				ll.add(listit.next());
			}
		}else if(it.hasNext()){
			while(it.hasNext()){
				ll.add(it.next());
			}
		}
		return ll;
	}
	
	private class TIterator implements Iterator{

		private int index;
		TlinkedListSimple ll=null;
		TIterator(TlinkedListSimple ll){
			this.ll=ll;
		}
		
		@Override
		public boolean hasNext() {
			
			return index<ll.size();
		}

		@Override
		public Object next() {
			if(hasNext()){
				return get(index++);
			}
			return null;
		}
		
	}
}
