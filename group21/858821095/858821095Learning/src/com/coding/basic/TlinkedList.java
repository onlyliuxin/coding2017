package com.coding.basic;

public class TlinkedList implements Tlist {
	
	private int size=0;
	private Node  head = new Node(null, null, null);
	
	TlinkedList(){
		head.pre=head.next=head;
	}
	
	public void add(Object o){
		addBefore(o, head);
	}
	
	public void addBefore(Object o, Node e){
		Node node = new Node(o, e.pre, e);
		node.pre.next=node;
		node.next.pre=node;
		size++;
	}
	
	
	public void add(int index , Object o){
		addBefore(o, getNode(index));
	}
	
	private Node getNode(int index){
		if(index <0 || index>=size)
			throw new IndexOutOfBoundsException("Index: " + index+", Size:"+size);
		Node p=head.next;
		for(int i=0;i<index;i++){
			p=p.next;
		}
		return p;
	}
	
	public Object get(int index){
		
		return getNode(index).data;
	}
	public Object remove(int index){
		Node p = getNode(index);
		Object e = p.data;
		p.pre.next=p.next;
		p.next.pre=p.pre;
		p.next=p.pre=null;
		p.data=null;
		size--;
		
		return e;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		addBefore(o, head.next);
	}
	public void addLast(Object o){
		addBefore(o, head);
	}
	public Object removeFirst(){
		Node p = head.next;
		Object e = p.data;
		head.next=p.next;
		p.next.pre=head;
		p.pre=p.next=null;
		p.data=null;
		size--;
		return e;
	}
	public Object removeLast(){
		Node p = head.pre;
		Object e = p.data;
		p.pre.next=head;
		p.next.pre=p.pre;
		p.pre=p.next=null;
		p.data=null;
		size--;
		return e;
	}
	
	public void clear(){
		Node p = head.next;
		while(p!=head){
			Node q = p.next;
			p.pre=p.next=null;
			p.data=null;
			p=q;
		}
		size=0;
		head.pre=head.next=head;
	}
	public boolean contains(Object o){
		return indexOf(o)!=-1;
		
	}
	public int indexOf(Object o){
		
		int i = 0;
		if(o==null){
			for(Node p = head.next;p!=head;p=p.next){
				if(p.data==null)
					return i;
				i++;
			}
		}else{
			for(Node p = head.next;p!=head;p=p.next){
				if(p.data.equals(o))
					return i;
				i++;
			}
		}
		
		
		return -1;
	}
	
	public Iterator iterator(){
		return new LinkedIterator(this);
	}
	
	
	private class LinkedIterator implements Iterator{

		private int pos;
		private TlinkedList ll = null;
		LinkedIterator(TlinkedList ll){
			this.ll=ll;
		}
		@Override
		public boolean hasNext() {
			
			return pos<ll.size;
		}

		@Override
		public Object next() {
			if(hasNext()){
				return ll.getNode(pos++);
			}
			return null;
			
		}
		
	}
	
	private static  class Node{
		Object data;
		Node pre;
		Node next;
		Node(Object data, Node pre, Node next){
			this.data = data;
			this.next=next;
			this.pre=pre;
		}
	}
	
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		Node p = head;
		for(int i=0; i<size;i++){
			p.next.next=p;
			p.pre.pre=p;
			p=p.next;
		}
		Node q = head.next;
		for(int i=0;i<size;i++){
			System.out.print(q.data+" ");
			q = q.next;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
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
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(TlinkedListSimple list){
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
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
	 * @param list
	 */
	public  TlinkedListSimple intersection( TlinkedListSimple list){
		return null;
	}
}
