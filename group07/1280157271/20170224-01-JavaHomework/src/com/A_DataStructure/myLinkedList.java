package com.A_DataStructure;
 

import java.util.Iterator; 

public class myLinkedList implements List {
	
	private Node last=null;//�ֱ�ָ���һ�������һ���ڵ�
	private Node head=null;
	public Node getHead() {
		return head;
	}
	public Node getLast() {
		return last;
	}

	private int size;
	

	static  class Node{//����Ľڵ�ṹ��
		Object data;//������
		Node next;	//Node���ã��൱��ָ�룬ָ����һ���ڵ�
	
		 Node(Object e,  Node next) {
	            this.data = e;
	            this.next = next;
	        }
	}
	
	
	public void add(Object o){//��β����ӣ����൱�����β�ڵ�
		creatLastNode(o);
	}
	public void add(int index , Object o){//��indexǰ�����(index��0��ʼ��)
		if(index<0||index>=size){
			System.out.println("indexԽ�磡����");
		}
		else if(head==null){
			System.out.println("��ǰ����Ϊ�գ����ܲ���");
		}
		else{
			if(index == 0){//˵����������λ��Ϊ0����ô�����ͷ���
				createFirstNode(o);
			}else{//��ȥ�ҵ�ָ��λ��
				Node indexBeforeNode = getNode(index-1);//���ﷵ�ص���index��ǰһ���ڵ�
				Node newIndex =new Node(o,indexBeforeNode.next) ;//x�½ڵ㱣��indexBefore��ָ��
				
				indexBeforeNode.next = newIndex;
				size++;
			}
		}
	}
	
	public Object get(int index){
		return getNode(index).data;//���ص��ǽڵ��е����ݶ���
		//return getNode(index);
	}
	
	
	public Object remove(int index){
		if(head==null){
			System.out.println("����Ϊ�գ������Ƴ�");
		}else{
			if(index==0){//�Ƴ�ͷ���
				removeFirst();
			}else{//�ҵ�ָ���ڵ��ǰһ���ڵ�
				Node removeNode = getNode(index-1);
				removeNode.next = removeNode.next.next;//�Ƴ���index
				
				//return removeNode.next.data;//�����Ƴ��ڵ�����ݶ���
				if(index==size-1){
					last = removeNode;
					//System.out.println("��ʱ��size-1=index��lastֵ�ǣ�"+last.data);
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
		if(size>0){//�б�Ϊ�գ���һ����ͷ
			Node removeHead = head;
			head = head.next;
			size--;
			//return removeHead.data;//����ͷ�������ݶ���
			return removeHead;
		}else{
			System.out.println("����Ϊ�գ�");
		}
		return null;
	}
	public Object removeLast(){
		if(size>0){
			Node removeLastBefore = getNode(size-2);//�ҵ�last�ڵ����һ���ڵ�
			//Object returnObj = removeLastBefore.next.data; 
			Object returnObj = removeLastBefore.next;
			removeLastBefore.next = null;
			last = removeLastBefore;
			size--;
			return returnObj;
		}else{
			System.out.println("����Ϊ�գ�");
		}
		return null;
	}
//	public Iterator iterator(){
//		return null;
//	}
	
	/*
	 * ���ͷ���
	 * */
	void createFirstNode(Object e){
		Node oldHead = head;
		Node newHead = new Node(e,oldHead);//�������Ľڵ���Ϊhead�ڵ��ǰһ�ڵ� 
		head = newHead;//���ܿղ��գ�headҪָ���µ�ͷ�ڵ�
		//System.out.println("headָ����newHead,��ӡһ�£�"+head.data);
		if(oldHead == null){//�������Ϊ�գ�head��last��ָ���½ڵ㣨��Ϊ��last�Ͳ����Ҹ�ֵ����Ϊ��ȷ������������ģ�
			last = newHead;
//			System.out.println("ִ��headΪ��,��ӡһ�£�");
//			this.printlnLinked();
		}else{//ͷ����Ѿ�����,�½ڵ���Ϊͷ��㣬ԭhead��ڶ���last����ָ������last
			newHead.next = oldHead;
//			System.out.println("ִ��head��Ϊ��,��ӡһ�£�");
//			this.printlnLinked();
		}
		size++;
	}
	/*
	 * ���β���
	 * */
	void creatLastNode(Object e){
		Node oldLast = last;
		Node newLast = new Node(e,null);//�µ�β�ڵ���һ���ڵ�Ϊ��
		last = newLast;//���ܿղ��գ�last��Ҫָ���µ�β�ڵ�
		if(head == null){//����Ϊ��
			//System.out.println("ִ��headΪ��");
			head = newLast;
		}else{
			oldLast.next = newLast;		
		}
		size++;
	}
	/*
	 * Ѱ��ָ�����
	 * */
	private Node getNode(int index){
		if(index<0||index>=size){
			System.out.println("indexԽ�磡����");
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
				System.out.println("��"+i+"���ڵ����ݣ�"+node1.data);
				node1 = node2.next;
				node2 = node1;
			}
		}
	}
	
	/**
	 * �Ѹ���������
	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
	 */
	public  void reverse(){		
		if(head==null||head==last){//head==last˵��Ҫô����Ϊ��Ҫôֻ��һ���ڵ�	
		//�Ͳ�������
		}else{
			last=head;
//			System.out.println(" reverse()��last��ֵ��"+head.data); 
			Node[] temp = new Node[size];//����ÿ���ڵ��ָ��
			 
			for(int i=0;i<size;i++){
				temp[i] = this.getNode(i);
				if(i==size-1){
					head=temp[i];
				}
				//System.out.println("temp[i]��ֵ��"+temp[i].data);
			}
			for(int i=size-1;i>0;i--){
				temp[i].next = temp[i-1];//��temp[1]=temp[1-1]������i=0,����ѭ��
				if(i==1){
					temp[0].next = null;
				}
			} 
		}
	}
	
	/**
	 * ɾ��һ���������ǰ�벿��
	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10
	 */
	public  void removeFirstHalf(){
		int sizeHalf = this.size/2;
		head = this.getNode(sizeHalf);
		this.size -= sizeHalf; 
	}
	
	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i<0||i>size||length<0||length>size|length+i>size){
			System.out.println("����Խ�磡��");
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
	 * �ٶ���ǰ�����list���������������е�����
	 * �ӵ�ǰ������ȡ����Щlist��ָ����Ԫ��
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	public   int[] getElements(myLinkedList list){
		int[] resultList=new int[list.size];
		int temp;
		if(list.size<=0){
			System.out.println("list����Ϊ�գ�");
		}else{
			for(int i=0;i<list.size;i++){
				temp = (int) list.get(i); 
				resultList[i] = (int) this.get(temp);
			}
		} 
		return resultList;
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * �ӵ�ǰ��������ɾ����list�г��ֵ�Ԫ�� 
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
	 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
	 */
	public  void removeDuplicateValues(){
 
		int flag;
		myLinkedList newLinked = new myLinkedList();
		if(this.size()<2){
			System.out.println("������С��2���������");
		}else{
			newLinked.add(this.get(0));
			for(int i=1;i<this.size();i++){//ԭ�����һ���Ѿ����ƽ�ȥ 
				flag=1;
				for(int j=0;j<newLinked.size();j++){
					if(newLinked.get(j)==this.get(i)){
						flag=0;
						System.out.println("��ȥflag��0����");
					}
				}
				if(flag==1){//˵���ղŵ�this.get(i)��newLingked������û���ظ�
					newLinked.add(this.get(i));
				}
			}
			this.size=newLinked.size();
			this.head = newLinked.getHead();
		}
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ��дһ��Ч���㷨��ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�
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
					System.out.println("�������ֵС�ڻ����min");
				}
				else{
					if((int)this.get(i)>min&&flag1==0){
						temp1 = this.getNode(i);
						indexI1 = i;
						flag1=1;//��֤���ifֻ�ܽ���һ��
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
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
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
