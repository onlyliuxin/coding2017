package com.A_DataStructure;
 

import java.util.Iterator; 

public class myLinkedList implements List {
	
	private Node last=null;//分别指向第一个和最后一个节点
	private Node head=null;
	public Node getHead() {
		return head;
	}
	public Node getLast() {
		return last;
	}

	private int size;
	

	static  class Node{//链表的节点结构体
		Object data;//数据域
		Node next;	//Node引用，相当于指针，指向下一个节点
	
		 Node(Object e,  Node next) {
	            this.data = e;
	            this.next = next;
	        }
	}
	
	
	public void add(Object o){//在尾部添加，就相当于添加尾节点
		creatLastNode(o);
	}
	public void add(int index , Object o){//在index前面插入(index从0开始的)
		if(index<0||index>=size){
			System.out.println("index越界！！！");
		}
		else if(head==null){
			System.out.println("当前链表为空，不能插入");
		}
		else{
			if(index == 0){//说明传过来的位置为0，那么就添加头结点
				createFirstNode(o);
			}else{//就去找到指定位置
				Node indexBeforeNode = getNode(index-1);//这里返回的是index的前一个节点
				Node newIndex =new Node(o,indexBeforeNode.next) ;//x新节点保存indexBefore的指向
				
				indexBeforeNode.next = newIndex;
				size++;
			}
		}
	}
	
	public Object get(int index){
		return getNode(index).data;//返回的是节点中的数据对象
		//return getNode(index);
	}
	
	
	public Object remove(int index){
		if(head==null){
			System.out.println("链表为空，不能移除");
		}else{
			if(index==0){//移除头结点
				removeFirst();
			}else{//找到指定节点的前一个节点
				Node removeNode = getNode(index-1);
				removeNode.next = removeNode.next.next;//移除了index
				
				//return removeNode.next.data;//返回移除节点的数据对象
				if(index==size-1){
					last = removeNode;
					//System.out.println("此时是size-1=index，last值是："+last.data);
				}
				size--;
				return removeNode.next;
			}
		}
		return null;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		createFirstNode(o);
	}
	public void addLast(Object o){
		creatLastNode(o);
	}
	public Object removeFirst(){
		if(size>0){//列表不为空，即一定有头
			Node removeHead = head;
			head = head.next;
			size--;
			//return removeHead.data;//返回头结点的数据对象
			return removeHead;
		}else{
			System.out.println("链表为空！");
		}
		return null;
	}
	public Object removeLast(){
		if(size>0){
			Node removeLastBefore = getNode(size-2);//找到last节点的上一个节点
			//Object returnObj = removeLastBefore.next.data; 
			Object returnObj = removeLastBefore.next;
			removeLastBefore.next = null;
			last = removeLastBefore;
			size--;
			return returnObj;
		}else{
			System.out.println("链表为空！");
		}
		return null;
	}
//	public Iterator iterator(){
//		return null;
//	}
	
	/*
	 * 添加头结点
	 * */
	void createFirstNode(Object e){
		Node oldHead = head;
		Node newHead = new Node(e,oldHead);//传进来的节点作为head节点的前一节点 
		head = newHead;//不管空不空，head要指向新的头节点
		//System.out.println("head指向了newHead,打印一下："+head.data);
		if(oldHead == null){//如果链表为空，head和last都指向新节点（不为空last就不能乱赋值，因为不确定链表最后在哪）
			last = newHead;
//			System.out.println("执行head为空,打印一下：");
//			this.printlnLinked();
		}else{//头结点已经存在,新节点作为头结点，原head变第二，last还是指向它的last
			newHead.next = oldHead;
//			System.out.println("执行head不为空,打印一下：");
//			this.printlnLinked();
		}
		size++;
	}
	/*
	 * 添加尾结点
	 * */
	void creatLastNode(Object e){
		Node oldLast = last;
		Node newLast = new Node(e,null);//新的尾节点下一个节点为空
		last = newLast;//不管空不空，last是要指向新的尾节点
		if(head == null){//链表为空
			//System.out.println("执行head为空");
			head = newLast;
		}else{
			oldLast.next = newLast;		
		}
		size++;
	}
	/*
	 * 寻找指定结点
	 * */
	private Node getNode(int index){
		if(index<0||index>=size){
			System.out.println("index越界！！！");
		}else{
			Node node=head;
			while(index != 0){
				node = node.next;
				index--;
			}
			return node;
		}
		return null;
	}
	public void printlnLinked(){
		
		Node node1 =head;
		Node node2 =head;
		if(head!=null){
			for(int i=0;i<size;i++){
				System.out.println("第"+i+"个节点内容："+node1.data);
				node1 = node2.next;
				node2 = node1;
			}
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		if(head==null||head==last){//head==last说明要么链表为空要么只有一个节点	
		//就不做操作
		}else{
			last=head;
//			System.out.println(" reverse()中last的值："+head.data); 
			Node[] temp = new Node[size];//保存每个节点的指向
			 
			for(int i=0;i<size;i++){
				temp[i] = this.getNode(i);
				if(i==size-1){
					head=temp[i];
				}
				//System.out.println("temp[i]的值："+temp[i].data);
			}
			for(int i=size-1;i>0;i--){
				temp[i].next = temp[i-1];//当temp[1]=temp[1-1]结束后，i=0,结束循环
				if(i==1){
					temp[0].next = null;
				}
			} 
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		int sizeHalf = this.size/2;
		head = this.getNode(sizeHalf);
		this.size -= sizeHalf; 
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i<0||i>size||length<0||length>size|length+i>size){
			System.out.println("参数越界！！");
		}else{
			if(i==0){
				head = this.getNode(length);
			}else{
				this.getNode(i-1).next = this.getNode(i+length);
			} 
			size -=length;
		} 
	}
		
	 
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public   int[] getElements(myLinkedList list){
		int[] resultList=new int[list.size];
		int temp;
		if(list.size<=0){
			System.out.println("list数组为空！");
		}else{
			for(int i=0;i<list.size;i++){
				temp = (int) list.get(i); 
				resultList[i] = (int) this.get(temp);
			}
		} 
		return resultList;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	
	public  void subtract(myLinkedList list){
		for(int i=0;i<list.size();i++){
			Object listValue = list.get(i);
			for(int j=0;j<this.size();j++){
				if(listValue==this.get(j)){
					this.remove(j);
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
 
		int flag;
		myLinkedList newLinked = new myLinkedList();
		if(this.size()<2){
			System.out.println("链表长度小于2，无须操作");
		}else{
			newLinked.add(this.get(0));
			for(int i=1;i<this.size();i++){//原数组第一个已经复制进去 
				flag=1;
				for(int j=0;j<newLinked.size();j++){
					if(newLinked.get(j)==this.get(i)){
						flag=0;
						System.out.println("进去flag置0操作");
					}
				}
				if(flag==1){//说明刚才的this.get(i)在newLingked链表中没有重复
					newLinked.add(this.get(i));
				}
			}
			this.size=newLinked.size();
			this.head = newLinked.getHead();
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		if(min>max){
			System.out.println("min<max");
		}else{
			Node temp1 = null,temp2=null;
			int flag1=0,flag2=0,indexI1=0,indexI2 = 0;
			for(int i=0;i<this.size();i++){
				if(i==this.size()-1&&(int)this.get(i)<=min){
					System.out.println("链表最大值小于或等于min");
				}
				else{
					if((int)this.get(i)>min&&flag1==0){
						temp1 = this.getNode(i);
						indexI1 = i;
						flag1=1;//保证这个if只能进来一次
					}
					if((int)this.get(i)>max&&flag2==0){
						temp2 = this.getNode(i);
						indexI2 = i;
						flag2 = 1;
					}
				}
			}
			temp1.next=temp2;
			this.size -=(indexI2-indexI1);
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  myLinkedList intersection( myLinkedList list){
		myLinkedList newC = new myLinkedList();
		
		
		for(int i=0;i<list.size();i++){
			for(int j=0;j<this.size();j++){
				if(list.get(i)==this.get(j)){
					newC.add(this.get(j));
				}
			}
		}
		
		return newC;
	}
	

	

}
