	package myList;
	
	/*
	 * 该单链表是有头节点的。 
	 */
	
	public class MyLinkedList {
		
		private int theSize;	//链表的大小。
		private Node headNode;	//头节点
		
		//节点的类。
		public static class Node{
			
			private Object data;
			private Node node;
			
			public Node(){
				
			}
			
			public Node(Object data, Node node) {
				this.data = data;
				this.node = node;
			}
			public Object getDate(){
				return data;
			}
			
		}
		
		//构造方法，初始化时，生成一个有头节点的空单链表。
		public MyLinkedList(){
			clear();
		}
		
		//获得头节点
		public Node getHeadNode(){
			return headNode;
		}
		
		//链表清空
		public void clear(){
			headNode=new Node(null,null);	//头结点初始化，数据date和指针node全部设置为null.
			theSize=0;						//表的大小归零
		}
		
		//获取表的大小
		public int size(){
			return theSize;
		}
		
		//添加节点到链表尾部。
		public void add(Object aData){
			add(theSize+1,aData);
		}
	
		//添加节点到指定位置。
		public void add(int idx,Object aDate){
			
			//创建一个新的节点
			Node newNode=new Node();
			newNode.data=aDate;
			
			//遍历链表，找到指定位置的节点，并将新节点插到指定位置节点的前面一个位置。
			Node p,q;
			p=headNode;
			
			for(int i=1;i<idx;i++){
				p=p.node;
			}
			
			q=p.node;
			p.node=newNode;
			newNode.node=q;
			
			theSize++;
			
			
		}
	
		//删除节点。
		public void remove(int idx){
			
			Node p,q;
			p=headNode;
			
			for(int i=1;i<idx;i++){
				p=p.node;
			}
			
			q=p.node;
			p.node=q.node;
			theSize--;
		}
		
		//获取MyIterator接口对象
		public MyIterator myIterator(){
			 
			return myIterator(this);
		}
		
		public MyIterator myIterator(Object arr){
			MyIterator i=new MyIterator(arr);
			return i;
		}
		
		//打印出链表。
		public void print(){
			Node p=headNode.node;
			for(int i=1;i<=theSize;i++){
				System.out.println(i+"  "+p.data);
				p=p.node;
			}
		}
	}
	
	
	
	
	

