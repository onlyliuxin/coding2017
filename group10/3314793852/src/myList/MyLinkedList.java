	package myList;
	
	/*
	 * �õ���������ͷ�ڵ�ġ� 
	 */
	
	public class MyLinkedList {
		
		private int theSize;	//����Ĵ�С��
		private Node headNode;	//ͷ�ڵ�
		
		//�ڵ���ࡣ
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
		
		//���췽������ʼ��ʱ������һ����ͷ�ڵ�Ŀյ�����
		public MyLinkedList(){
			clear();
		}
		
		//���ͷ�ڵ�
		public Node getHeadNode(){
			return headNode;
		}
		
		//�������
		public void clear(){
			headNode=new Node(null,null);	//ͷ����ʼ��������date��ָ��nodeȫ������Ϊnull.
			theSize=0;						//��Ĵ�С����
		}
		
		//��ȡ��Ĵ�С
		public int size(){
			return theSize;
		}
		
		//��ӽڵ㵽����β����
		public void add(Object aData){
			add(theSize+1,aData);
		}
	
		//��ӽڵ㵽ָ��λ�á�
		public void add(int idx,Object aDate){
			
			//����һ���µĽڵ�
			Node newNode=new Node();
			newNode.data=aDate;
			
			//���������ҵ�ָ��λ�õĽڵ㣬�����½ڵ�嵽ָ��λ�ýڵ��ǰ��һ��λ�á�
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
	
		//ɾ���ڵ㡣
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
		
		//��ȡMyIterator�ӿڶ���
		public MyIterator myIterator(){
			 
			return myIterator(this);
		}
		
		public MyIterator myIterator(Object arr){
			MyIterator i=new MyIterator(arr);
			return i;
		}
		
		//��ӡ������
		public void print(){
			Node p=headNode.node;
			for(int i=1;i<=theSize;i++){
				System.out.println(i+"  "+p.data);
				p=p.node;
			}
		}
	}
	
	
	
	
	

