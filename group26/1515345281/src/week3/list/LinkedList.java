package week3.list;

import java.util.Stack;


public class LinkedList implements List{
	
	private int size=0;//表示该链表的长度
	private Node head;//链表的头元素
  
	public void add(Object o){
		if(null == head){
			head = new Node(o);
			size++;
			return ;
		}
		
		Node node=head;
		while(null != node.next){
			node=node.next;
		}
		Node addNode=new Node(o);
		node.next=addNode;
		size++;
	}
	
	public void add(int index , Object o){
		if(size == 0 || index ==size){
			add(o);
			return ;
		}
		
		ListUtils.checkIndexRange(index, size);
		
		if(index==0){
			Node node=new Node(head.next.data);
			node.next=head.next;
			head.next=node;
			head.data=o;
			size++;
			return ;
		}
		
		Node node=head;
		for(int i=0;i<index-1;i++){
			node=node.next;
		}
		
		Node addNode=new Node(o);
		addNode.next=node.next;
		node.next=addNode;
		size++;
	}
	
	public Object get(int index){
		
		ListUtils.checkIndexRange(index, size-1);
		
		Node node=head;
		for(int i=0;i<index;i++){
			node=node.next;
		}
		return node.data;
	}
	
	public Object remove(int index){
		
		ListUtils.checkIndexRange(index, size-1);
		
		if(index == 0){
			Node removeNode=head;
			head=head.next;
			size--;
			return removeNode.data;
		}
		
		Node node=head;
		for(int i=0;i<index-1;i++){
			node=node.next;
		}
		Node removeNode=node.next;
		node.next=removeNode.next;
		size--;
		return removeNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0,o);
	}
	
	public void addLast(Object o){
		add(size,o);
	}
	
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(size-1);
	}
	
	public Iterator iterator(){
		return new Iterator(){

			int cursor=0;
			Node node=head;
			
			@Override
			public boolean hasNext() {
				
				return cursor!=size;
			}

			@Override
			public Object next() {
				
				Node currentNode=node;
				node=node.next;
				cursor++;
				return currentNode.data;
			}
		};
	}
	
	private static class Node{
		Object data;
		Node next=null;
		public Node(Object data){
			this.data=data;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		
		if(null == head || null ==head.next)
			return ;
		
		Stack<Node> stack=new Stack<Node>();
		
		Node currentNode=head;
		
		while(currentNode!=null){
			
			stack.push(currentNode);
			
			Node tempNode=currentNode.next;
			currentNode.next=null;//断开连接
			
			currentNode=tempNode;
			
		}
		
		head=stack.pop();
		currentNode=head;
		
		while(!stack.isEmpty()){
			
			currentNode.next=stack.pop();
			currentNode=currentNode.next;	    	
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){

		int num=size()/2;
		for(int i=0;i<num;i++){
			removeFirst();		
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
		if(i <0 || i >= size){
			throw new IndexOutOfBoundsException();
		}
		
		int len=size()-i>=length ? length:size-i;
		
		int k=0;
		
		while(k<len){
			remove(i);
			k++;
		}
		
	}
	/**
	 * 假定当前链表和listB排列的整数
	 * 从当前链表中取出那些listB所指定均包含已升序的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(LinkedList list){
		
		int[] arr=new int[list.size()];
		
		for(int i=0;i<list.size();i++){
			arr[i]=(int)this.get((int)list.get(i));
		}
		return arr;
	}
	
	/**
	 * 传入数据删除节点
	 * @param obj
	 */
	public void remove(Object obj){
		if(head==null){
			throw new RuntimeException("LinkedList is empty!");
		}
		//如果要删除的结点是第一个，则把下一个结点赋值给第一个结点
		if(head.data.equals(obj)){
			head=head.next;
			size--;
		}else{
			Node pre=head; //上一节点
			Node cur=head.next; //当前结点
			while(cur!=null){
				if(cur.data.equals(obj)){
					pre.next=cur.next;
					size--;
				}
				pre=pre.next;
				cur=cur.next;
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		
		for (int i = 0; i < list.size(); i++) {
			this.remove(list.get(i));
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		
		if(head == null){
			throw new RuntimeException("LinkedList is empty!");
		}
		
		Node pre=head;
		
		Node cur=head.next;
	    
		while(cur!=null){
			Object dataPre=pre.data;
			Object dataCur=cur.data;
			while(dataPre.equals(dataCur)){
				if(cur.next==null){
					pre.next=null;
					break;
				}
				pre.next=cur.next;
				cur=cur.next;
				dataCur=cur.data;
				size--;
			}
			pre=pre.next;
			cur=cur.next;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
		if(head==null){
			return ;
		}
		
		while((int)head.data > min && (int)head.data < max){
			head=head.next;
		}
		
		Node cur=head;
		
		while(cur.next!=null){
			Node next=cur.next;
			if( (int)next.data> min  && (int)next.data < max){
				cur.next=next.next;
			}else{
				cur=cur.next;
			}
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		
		 LinkedList result=new LinkedList();
		 Node cur=head;
		 int i=0;
		 while(cur!=null && i<list.size()){
			 if(cur.data.equals(list.get(i))){
				 result.add(cur.data);
				 i++;
				 cur=cur.next;
			 }else if((int)cur.data>(int)list.get(i)){
				 i++;
			 }else{
				 cur=cur.next;
			 }
		 }
		 return result;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		Node node = head;
		while(node != null){
			buffer.append(node.data);
			if(node.next != null){
				buffer.append(",");
			}
			node = node.next;
		}
		buffer.append("]");
		return buffer.toString();
	}
}