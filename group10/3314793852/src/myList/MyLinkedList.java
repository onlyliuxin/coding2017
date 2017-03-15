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
		public void remove(int idx) {
			if(idx<0||idx>size()){
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Node p,q;
			p=headNode;
			
			for(int i=1;i<idx;i++){
				p=p.node;
			}
			
			q=p.node;
			p.node=q.node;
			theSize--;
		}
		
		//���ݽǱ�������
		public int get(int idx){
			if(idx<1||idx>size()){
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Node p=headNode;
			for(int i=1;i<=idx;i++){
				p=p.node;
			}
			return (int)p.data; 
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
		
		/**
		 * �Ѹ���������
		 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
		 */
		public  void reverse(){		
			Node a,temp;
			Node c=new Node(null,null);		//����һ���µ�ͷ��㡣
			
			
			a=headNode;
			temp=c;
			for(int j=0;j<size();j++){
				for(int i=1;i<=size()-j;i++){		
					a=a.node;
				}

//				for(int m=size()-j;m<size();m++){	
//					c=c.node;
//					System.out.println(c.data);
//				}
				
				temp.node=a;
				temp=temp.node;
//				System.out.println(temp.data);
				a=headNode;
			}
			
			a.node=c.node;
		}
		
		/**
		 * ɾ��һ���������ǰ�벿��
		 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
		 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10
		 */
		public  void removeFirstHalf(){
			Node p;
			p=headNode;
			
			
			int j=size();
			for(int i=1;i<=((size()/2)+1);i++){	//�������м�ڵ㴦
				p=p.node;
			}
			
			
			headNode.node=p;				//��ͷ����nodeָ���м�ڵ�.
			theSize=theSize-(j/2);
		}
		
		/**
		 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
		 * @param i
		 * @param length
		 */
		public  void remove(int i, int length){
			for(int j=0;j<length;j++){
				remove(i-1);
			}
			
		}
		/**
		 * �ٶ���ǰ�����listB���������������е�����
		 * �ӵ�ǰ������ȡ����ЩlistB��ָ����Ԫ��
		 * ���統ǰ���� = 11->101->201->301->401->501->601->701
		 * listB = 1->3->4->6
		 * ���صĽ��Ӧ����[101,301,401,601]  
		 * @param list
		 */
		public  int[] getElements(MyLinkedList list){
			int[] arr1=new int[list.size()];		//�����洢���صĽ��
			int[] arr2=new int[list.size()];		//�����洢list���ϵ��е���
			
			Node p,q;
			p=list.getHeadNode();
			q=headNode;
			for(int i=1;i<=list.size();i++){	//����list����
				p=p.node;
				arr2[i-1]=(int) p.getDate();	//ȡ�������е��������洢��arr2�С�
			}
			
//			for(int i=0;i<arr2.length;i++){
//				System.out.println(arr2[i]);
//			}	//��list��ȡ�����ݡ�
			
			for(int i=0;i<arr2.length;i++){	
				for(int j=1;j<=arr2[i];j++){
					q=q.node;
				}
				arr1[i]=(int) q.data;
				q=headNode;
			}
			
			return arr1;
		}
		
		/**
		 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
		 * �ӵ�ǰ��������ɾ����listB�г��ֵ�Ԫ�� 
		 * @param list
		 */
		
		public  void subtract(MyLinkedList list){
			int[] arr=new int[list.size()];		//�����洢list���ϵ��е���
			
			Node p,q;
			p=list.getHeadNode();
			q=headNode;
			for(int i=1;i<=list.size();i++){	//����list����
				p=p.node;
				arr[i-1]=(int) p.getDate();	//ȡ�������е��������洢��arr�С�
			}
			
			//ÿ����һ���������ݣ��ͱ���һ�鱾���������������Ƿ������ͬ�����ݣ���ͬ��ɾ����
			for(int i=0;i<arr.length;i++){//��������
				
				for(int j=1;j<=size();j++){//����������
					q=q.node;
					if((int)q.data==arr[i]){
						remove(j);
					}
				}
				q=headNode;
			}
		}
		
		/**
		 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
		 * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
		 */
		public  void removeDuplicateValues(){
			Node q,p;
			q=headNode;
			
			for(int i=1;i<=size();i++){
				q=q.node;
				p=q.node;
				if((int)q.data==(int)p.data){
					remove(i);
				}
			}
		}
		
		/**
		 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
		 * ��дһ��Ч���㷨��ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�
		 * @param min
		 * @param max
		 */
		public  void removeRange(int min, int max){
			int mMax=0,mMin=0;
			Node p,q;
			q=headNode;
			p=headNode;
			
			//�Ӵ���С�ң���һ�α�minС������������β����ʼ��
			int[] arr=new int[size()];		//�����洢��������е���
			Node m;
			m=headNode;
			for(int i=1;i<=size();i++){	//����list����
				m=m.node;
				arr[i-1]=(int) m.getDate();	//ȡ�������е��������洢��arr�С�
			}
			for(int i=arr.length-1;i>=0;i--){
				//System.out.println("arr"+arr[i]);
				//System.out.println("arr"+arr[i]);
				if(min>arr[i]){
					mMin=i+1;
					System.out.println("mMix"+mMin);
					break;
				}
			}
			//��С�����ң���һ�α�max�������������ͷ����ʼ��
			for(int i=1;i<=size();i++){
				q=q.node;
				if(max<(int)q.data){
					mMax=i;
					System.out.println("mMax"+mMax);
					break;
				}
			}
			
			//�ҵ�Ҫɾ������
			remove(mMin, mMax-mMin);
			
		}
		
		/**
		 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
		 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
		 * @param list
		 */
		public  MyLinkedList intersection( MyLinkedList list){
			Node p=headNode,
					q=list.headNode;
			MyLinkedList ls=new MyLinkedList();
			
			for(int i=1;i<size();i++){
				p=p.node;
				for(int j=1;j<size();j++){
					q=q.node;
					if((int)p.data==(int)q.data){
						ls.add(q.data);
					}
				}
				q=list.headNode;
			}
			return ls;
		}
		
	}
	
	
	
	
	

