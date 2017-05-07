package first;

import java.util.Stack;


public class LinkedList implements List {
	
	private Node head;
	int size;
	public LinkedList()
	{
		size=0;
		head=null;
	}
	public void add(Object o){
		if(head==null)
		{
			head=new Node(o);
			head.next=null;
			head.data=o;
		}
		else{
			Node p=head;
			{
				while(p.next!=null)
				{
					p=p.next;
				}
				Node n=new Node(o);
				p.next=n;
				n.data=o;
				n.next=null;
			}
		}
		size++;
	}
	public void add(int index , Object o){
		int i=1;
		Node p=head;
		while(i<index-1)
		{
			p=p.next;
			i++;
		}
		Node s=new Node(o);
		s.data=o;
	    s.next=p.next;
		p.next=s;
		size++;
	}
	public Object get(int index){
		int i=1;
		Node p=head;
		while(p!=null&&i<index)
		{
			p=p.next;
			++i;
		}
		if(p==null&&i>index) {return null;}
		return p.data;
	}
	public Object remove(int index){
		int i=1;
		Node p=head;
		Object o=null;
		if(index==1)
		{
			 o=head.data;
			 if(head.next!=null)
			 {
				  p=head.next;
				  head.data=p.data;
				  p=head;
			 }
			 else{
			 head=null;
			 }
		}
		else{
			while(i<index-1)
			{
				p=p.next;
				i++;
			}
			 o=p.next.data;
			 p.next=p.next.next;
			
		}
		size--;
		return o;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		if(head==null)
		{
			head=new Node(o);
			head.next=null;
			head.data=o;
		}
		else{
			Node p=new Node(o);
			Object o1=head.data;
			head.data=o;
			if(head.next!=null)
			{
				p.next=head.next;
			}
			else{
				head.next=p;
			    p.next=null;
			}
			p.data=o1;
			
		}
		size++;
	}
	public void addLast(Object o){
		if(head==null)
		{
			head=new Node(o);
			head.next=null;
			head.data=o;
		}
		else{
			Node p=head;
			{
				while(p.next!=null)
				{
					p=p.next;
				}
				Node n=new Node(o);
				p.next=n;
				n.data=o;
				n.next=null;
			}
		}
		size++;
	}
	public Object removeFirst(){
		Object o=head.data;
		//Node p=head;
		if(head.next!=null)
		{
		  Node p=head.next;
		  head.data=p.data;
		  head=p;
		}
		else{
			head=null;
		}
		size--;
		return o;
	}
	public Object removeLast(){
		Node p=head;
		Object o=null;
		int i=1;
		while(p!=null&&i!=size)
		{
			p=p.next;
			i++;
		}
		
		//p=null;
		o=p.data;
		p=null;
        size--;
		return o;
	}
	public void clear()
	{
		Node p=head;
		p=p.next;
		while(p!=null)
		{
			Node q=p.next;
			p=p.next;
			q=null;
		}
		head=null;
		size=0;
	}
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	private class LinkedListIterator implements Iterator
	{
		private LinkedList l=null;
		Node p=head;
		public  LinkedListIterator(LinkedList l)
		{
			this.l=l;
		}

		public boolean hasNext() {
			// TODO Auto-generated method stub
			boolean flag=false;
			if(p!=null)
			{
				flag=true;
			}
			return flag;
		}

		public Object next() {
			// TODO Auto-generated method stub
			Object o=p.data;
			p=p.next;
			return o;
		}
		
	}
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse()
	{
		if(head==null||null==head.next)
		{
			return;
		}
		Stack<Node> s=new Stack<Node>();
		Node currentNode=head;
		while(currentNode!=null)
		{
			s.push(currentNode);
			Node nextNode=currentNode.next;
			currentNode.next=null;  //把链表断开
			currentNode=nextNode;
		}
		head=s.pop();
		currentNode=head;
		while(!s.isEmpty())
		{
			Node nextNode=s.pop();
			currentNode.next=nextNode;
			currentNode=nextNode;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		int length=size/2;
		Node p=head;
		for(int i=0;i<length;i++)
		{
			if(i==0)
			{
				p.data=null;
				p=p.next;
				size--;
			}
			else{
				Node q=p;
				p=p.next;
				q=null;
				size--;
			}
		}
		head=p;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(length>size-1-i) return;
		if(i==0)
		{
			removeFirst();
			for(i=1;i<=length-1;i++)
			{
				removeFirst();
			}	
		}
		else{
			int j=0;
			Node p=head;
			while(j<i-1)
			{
				p=p.next;
				j++;
			}
			//要删除第一个节点的上一个节点
			Node q=p;
			int k=1;
			while(k<=length)
			{
				Node n=p.next;
				p=p.next;
				n=null;
				k++;
				size--;
			}
			q.next=p.next;
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
		if(this.size<1) return null;
		if((int)(list.get(list.size))>this.size) return null;
		//将链表转为数组
		int[] listToArray=new int[this.size];
		Node n=head;
		for(int i=0;i<this.size;i++)
		{
			listToArray[i]=(int)n.data;
			n=n.next;
		}
		
		//记录符合条件的数组
		int length=list.size;
		int[] array=new int[length];
		//用于记录数组种元素的个数
		int count=0;
		for(int i=0;i<length;i++)
		{
			int k=(int)(list.removeFirst());
			array[count++]=listToArray[k];			
		}
		return array;
	}
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 
	 * @param list
	 */
	public void subtract(LinkedList list)
	{
		for(int i=0;i<list.size();i++)
		{
			this.remove(list.get(i));
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		if(head==null)
		{
			throw new RuntimeException("Linkedlist is Empty!");
		}
		Node pre=head;
		Node cur=head;
		while(cur.next!=null)
		{
			cur=cur.next;
			Object data=pre.data;
			while(cur.data==data)
			{
				if(cur.next==null){
					pre.next=null;
					break;
				}
				pre.next=cur.next;
				size--;
				cur=cur.next;
				if(cur==null)
				{
					break;
				}
			}
			pre=pre.next;	
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min,int max)
	{
		if(head==null)
		{
			return;
		}
		int start=-1;
		int end=-1;
		int i=0;
		Node p=head;
		while(p!=null)
		{
			if((start==-1)&&(int)p.data<=min)
			{
				start=i;
			}
			if((end==-1)&&(int)p.data<=max)
			{
				end=i;
			}
			i++;
			p=p.next;
		}
		if(start==-1)
		{
			start=0;
		}
		if(end==-1)
		{
			end=size;
		}
		this.remove(start,end-start);
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList intersection(LinkedList list)
	{
		if(list==null)
		{
			return null;
		}
		 LinkedList result=new LinkedList();
		 int i1=0;
		 int i2=0;
		 while(i1<this.size && i2<list.size())
		 {
			 int value1=(int)this.get(i1);
			 int value2=(int)list.get(i2);
			 if(value1==value2)
			 {
				 result.add(value1);
				 i1++;
				 i2++;
			 }else if(value1<value2){
				 i1++;
			 }
			 else{
				 i2++;
			 }
			 
		 }
		 return result;
	}
	/**
	 * 传入数据删除节点
	 * @param obj
	 */
	public void remove(Object o)
	{
		if(head==null)
		{
			throw new RuntimeException("Linkedlist is Empty!");
		}
		
		if(head.data.equals(o))
		{
			head=head.next;
			size--;
		}
		else{
			Node pre=head;
			Node cur=head.next;
			while(cur!=null)
			{
				if(cur.data.equals(o))
				{
					pre.next=cur.next;
					size--;
				}
				pre=pre.next;
				cur=cur.next;
			}
		}
	}
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		Node p=head;
		sb.append("[");
		for(int i=0;i<size;i++)
		{
			if(i==size-1)
			{
				sb.append(p.data);
			}
			else{
				sb.append(p.data).append(",");
			}
			p=p.next;
		}
		return sb.toString();
	}
	private static  class Node{
		public Node(Object data) {
			this.data=data;
		}
		Object data;
		Node next;
		
	}
}

