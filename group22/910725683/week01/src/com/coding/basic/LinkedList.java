package com.coding.basic;


public class LinkedList implements List {
	
	//����ͷ//
	private Node head;
	
	//����β��Ϊ�˷�����ϵ�//
	private Node tail;
	
	//�����ȣ�Ϊ�˷�����ϵ�//
	private int size=0;
	
	private static class Node{
		Object data;
		Node next=null;
	}
	
	public void add(Object o){
		/*����ǿյ����ͷ��ʼ���ӣ���ΪҪ�������ͷ������β����ı����
		 *������ǿյ����β�Ϳ�ʼ���ӣ���ΪҪ�������β
		 */
		if (head==null){
			addFirst(o);
			tail=head;
		}else{
			addLast(o);
		}
	}
	
	public void add(int index , Object o){
		//ͷβ�������ӣ�����ά�����//
		if (index==0){
			addFirst(o);
			return;
		}
		if (index==size){
			addLast(o);
			return;
		}
		checkIndex(index);
		Node current = getNode(index-1);
		Node node = new Node();
		node.data=o;
		node.next=current.next;
		current.next=node;
		size++;
	}
	
	//�����±��ȡ�ڵ㣬������±��йص�����ɾ��//
	private Node getNode(int index){
		checkIndex(index);
		Node current = head;
		for (int i=0;i<index;i++){
			current=current.next;
		}
		return current;
	}
	
	public Object get(int index){
		return getNode(index).data;
	}
	
	public Object remove(int index){
		//ͷβ������������ά�����//
		if (index==0){
			return removeFirst();
		}
		if (index==size-1){
			return removeLast();			
		}
		checkIndex(index);
		Node current = getNode(index-1);
		Node temp = current.next;
		current.next=temp.next;
		size--;
		return temp.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node();
		node.data=o;
		node.next=head;
		head=node;//�������ͷ//
		size++;			
	}
	public void addLast(Object o){
		Node node = new Node();
		node.data=o;
		tail.next=node;
		tail=node;//�������β//
		size++;		
	}
	public Object removeFirst(){
		Node current = head;
		head=current.next;//�������ͷ//
		current.next=null;//�жϹ���//
		size--;
		return current.data;
	}
	
	public Object removeLast(){
		Node current=getNode(size-2);
		Node temp = current.next;
		current.next=null;//�жϹ���//
		tail=current;//�������β//
		size--;
		return temp.data;
	}
	
	public Iterator iterator(){
		return new Iterator();
	}
	
	private class Iterator{
		Node current=head;
		public boolean hasNext(){
			return current!=null;
		}
		public Object next(){
			if (current==null){
				throw new IndexOutOfBoundsException("no node");
			}
			Node temp =current;
			current=current.next;
			return temp.data;
		}
	}

	/**
	 * �Ѹ���������
	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
	 */
	public void reverse(){
		/*����ָ�룬��ʼ��ʱ��ָ��ͷ��ͷ�����һ��(ǰָ�롢��ָ��)
		 *ѭ����ÿ������ƶ�����1������ָ���Ƶ�����β��size-1���ڵ���Ҫ�ƶ�(size-1)-1��
		 *�ȱ���ǰָ���ֵtemp������ǰ���������ͷ��Ȼ�����ƶ�ǰ����ָ��
		 *�ƶ��󣬽�ǰָ��Ľڵ����ӵ����������ͷ����ʼ��һ���ƶ�
		 *ѭ��������ע�⵽ʵ��������ֻ�о�����ĵڶ����ڵ㵽�����ڸ��ڵ㣬��Ҫ��������������ͷβ�ڵ�
		 *�������β�ڵ���Ҫ���ӵ����������ͷ���������ͷ�ڵ��ָ���ÿգ���Ȼ��1<->2
		 *ά��ͷβ���
		 */
		Node current=head;
		Node currentAfter=current.next;
		Node temp;
		for (int i=0;i<size-2;i++){
			temp=current;
			current=currentAfter;
			currentAfter=currentAfter.next;
			current.next=temp;
		}
		currentAfter.next=current;
		tail=head;
		tail.next=null;
		head=currentAfter;
	}
	
	/**
	 * ɾ��һ���������ǰ�벿��
	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10
	 */
	public  void removeFirstHalf(){
		//int�ضϣ�������С��//
		int removeLength = size / 2;
		for (int i=1;i<=removeLength;i++){
			removeFirst();
		}
	}
	
	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		checkIndex(i);
		length=length+i-1;
		if (i+length-1>size){
			length=size-i;
		}
		//�Ӻ���ǰɾ������ֹԽ��//
		for (int k=length;k>=i;k--){
			remove(k);
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
	public int[] getElements(LinkedList list){
		int indexLength=list.size();
		int[] result=new int[indexLength];
		for (int i=0;i<indexLength;i++){
			int index=(int)list.get(i);
			if (index>size){
				result[i]=0;
			}else{
				result[i]=(int)list.get(index);
			}
		}
		return result;
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * �ӵ�ǰ��������ɾ����list�г��ֵ�Ԫ�� 
	 * @param list
	 */
	//ע�⵽�����������ѭ��list��ʱ�򣬱����ڲ�ѭ���ı��Ƚ�����Ľڵ���±겢��������//
	public void subtract(LinkedList list){
		int startIndex=0;
		Iterator iter=list.iterator();
		while(iter.hasNext()){
			int src =(int) iter.next();
			while(startIndex<size){
				if (src==(int) get(startIndex)){
					remove(startIndex);
					break;
				}
				else{
					startIndex++;
				}
			}
		}
	}
	
	/**
	 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
	 */
	//ע�⵽��������������Ҫɾ���Ľڵ���±겢��������//
	public  void removeDuplicateValues(){
		int startIndex=1;
		int scr=(int)head.data;
		while(startIndex<size){
			if (scr==(int) get(startIndex)){
				remove(startIndex);
			}else{
				scr=(int) get(startIndex);
				startIndex++;
			}
		}
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ��дһ��Ч���㷨��ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�
	 * @param min
	 * @param max
	 */
	//��������ö��ַ������ǵ�����û�뵽��Ч���㷨������˵��B����Ȼ�����ᡣ��������һ��һ���Ƚϵ�//
	public  void removeRange(int min, int max){
		Node current=head;
		Node temp=head;
		boolean isHeadNoed=true;
		while(current!=null){
			if ((int)current.data<min || (int)current.data>max){
				if (isHeadNoed){
					current=current.next;
					removeFirst();
				}else{
					temp.next=current.next;
					current=current.next;
					size--;
				}
			}else{
				temp=current;
				current=current.next;
				isHeadNoed=false;
			}
			
		}
	}
	
	/**
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
	 * @param list
	 */
	//ע�⵽������������ѭ���±�λ�õ�������//
	public LinkedList intersection( LinkedList list){
		LinkedList result = new LinkedList();
		int startIndex = 0;
		for (Iterator iter = list.iterator();iter.hasNext();){
			int src = (int) iter.next();
			while (startIndex<size){
				Object tar = get(startIndex);
				if (src == (int) tar){
					result.add(tar);
					break;
				}else{
					startIndex++;
				}
			}
		}
		return result;
	}
	
	private void checkIndex(int index){
		if (index<0 || index >=size){
			throw new IndexOutOfBoundsException("get " + index+" in "+size);
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Node current = head;
		for (int i=0;i<size;i++){
			sb.append(current.data);
			if (current.next!=null){
				sb.append("->");
			}
			current=current.next;
		}
		return sb.toString();
	}
}