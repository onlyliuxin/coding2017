package TestCollection;



public class LinkedList implements List {
	
	private Node head=new Node();;
	private int size;
	public Node  node(int a){//创建得到a位置节点的方法
		Node	teamp=null;
		 
		if(head!=null){
			 if(a<=size ){
				 teamp=head;
				 for(int i=0;i<a;i++){
					 teamp=teamp.next;//得到第a个位置的节点
				 }
			 }
			 }return teamp;
			 }
	public void add(Object o){
		
		if(head .next== null){
			Node firstNode=new Node();//形成第一个节点
			
			head.data=null;
			head.next=firstNode;//将头结点指向第一个节点
			firstNode.data=o;
			firstNode.next=null;
		}else{
			Node firstNode= head.next;//形成第一个节点
			Node newNode=new Node();
			newNode.data=o;
			newNode.next=null;
			firstNode.next=newNode;//把形成的新节点放在最后一个节点后面
			firstNode=newNode;//使新形成的节点变成最后一个节点
			
		}
		size++;
	}
	public void add(int index , Object o){
		 
		 Node teamp=node(index);//得到第index位置上的节点
		Node teamp1=node(index-1);//得到teamp前一个位置的节点
		Node newNode=new Node();//创建新节点
		newNode.data=o;
		newNode.next=teamp;
		teamp1.next=newNode;
		 size++;
	}
	public Object get(int index){
		Node teamp=node(index);//得到第index位置上的节点
		 
	Object o=	teamp.data;
		return o ;
	}
	public Object remove(int index){
		Node teamp=node(index);//得到第index位置上的节点
		Node teamp1=node(index-1);//得到第index-1位置上的节点
		Node teamp2=node(index+1);//得到第index+1位置上的节点
		teamp=null;
		teamp1.next=teamp2;//将teap1下一个位置指向teamp2
		size--;
		return null;
	}
	
	public int size(){
	 
		return size;
	}
	
	public void addFirst(Object o){
		Node firstNode=new Node();
		firstNode.data=o;
		
		Node teamp=node(0);//得到原来的第一个节点
		firstNode.next=teamp;//在第一个位子插入节点
		head.next=firstNode;
		size++;
	}
	public void addLast(Object o){
		Node  lastNode=new Node();
		lastNode.data=o;
		 
		Node teamp=node(size  );//得到原来的最后节点
		teamp.next=lastNode;//在最后位子插入节点
		 lastNode.next=null;
		size++;
		 
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		
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
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public static int[] getElements(LinkedList list){
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		
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
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}

	 