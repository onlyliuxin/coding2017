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
		
		//根据角标获得数据
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
		
		/**
		 * 把该链表逆置
		 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
		 */
		public  void reverse(){		
			Node a,temp;
			Node c=new Node(null,null);		//创建一个新的头结点。
			
			
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
		 * 删除一个单链表的前半部分
		 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
		 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
		 */
		public  void removeFirstHalf(){
			Node p;
			p=headNode;
			
			
			int j=size();
			for(int i=1;i<=((size()/2)+1);i++){	//遍历到中间节点处
				p=p.node;
			}
			
			
			headNode.node=p;				//将头结点的node指向中间节点.
			theSize=theSize-(j/2);
		}
		
		/**
		 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
		 * @param i
		 * @param length
		 */
		public  void remove(int i, int length){
			for(int j=0;j<length;j++){
				remove(i-1);
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
		public  int[] getElements(MyLinkedList list){
			int[] arr1=new int[list.size()];		//用来存储返回的结果
			int[] arr2=new int[list.size()];		//用来存储list集合的中的数
			
			Node p,q;
			p=list.getHeadNode();
			q=headNode;
			for(int i=1;i<=list.size();i++){	//遍历list集合
				p=p.node;
				arr2[i-1]=(int) p.getDate();	//取出集合中的数，并存储到arr2中。
			}
			
//			for(int i=0;i<arr2.length;i++){
//				System.out.println(arr2[i]);
//			}	//从list中取出数据。
			
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
		 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
		 * 从当前链表中中删除在listB中出现的元素 
		 * @param list
		 */
		
		public  void subtract(MyLinkedList list){
			int[] arr=new int[list.size()];		//用来存储list集合的中的数
			
			Node p,q;
			p=list.getHeadNode();
			q=headNode;
			for(int i=1;i<=list.size();i++){	//遍历list集合
				p=p.node;
				arr[i-1]=(int) p.getDate();	//取出集合中的数，并存储到arr中。
			}
			
			//每遍历一个数组数据，就遍历一遍本链表，查找链表中是否存在相同的数据，相同就删除。
			for(int i=0;i<arr.length;i++){//遍历数组
				
				for(int j=1;j<=size();j++){//遍历本链表
					q=q.node;
					if((int)q.data==arr[i]){
						remove(j);
					}
				}
				q=headNode;
			}
		}
		
		/**
		 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
		 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
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
		 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
		 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
		 * @param min
		 * @param max
		 */
		public  void removeRange(int min, int max){
			int mMax=0,mMin=0;
			Node p,q;
			q=headNode;
			p=headNode;
			
			//从大往小找，第一次比min小的数。从链表尾部开始。
			int[] arr=new int[size()];		//用来存储当链表的中的数
			Node m;
			m=headNode;
			for(int i=1;i<=size();i++){	//遍历list集合
				m=m.node;
				arr[i-1]=(int) m.getDate();	//取出集合中的数，并存储到arr中。
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
			//从小往到找，第一次比max大的数。从链表头部开始。
			for(int i=1;i<=size();i++){
				q=q.node;
				if(max<(int)q.data){
					mMax=i;
					System.out.println("mMax"+mMax);
					break;
				}
			}
			
			//找到要删除的域。
			remove(mMin, mMax-mMin);
			
		}
		
		/**
		 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
		 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
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
	
	
	
	
	

