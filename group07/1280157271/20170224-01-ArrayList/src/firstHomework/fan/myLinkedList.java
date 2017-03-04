package firstHomework.fan;

 

public class myLinkedList implements List {
	private static  class Node{//����Ľڵ�ṹ��
		Object data;//������
		Node next;	//Node���ã��൱��ָ�룬ָ����һ���ڵ�
	
		 Node(Object e,  Node next) {
	            this.data = e;
	            this.next = next;
	        }
	}
	
	private Node head,last=null;//�ֱ�ָ���һ�������һ���ڵ�
	private int size;
	
	 
	public void add(Object o){//��β����ӣ����൱�����β�ڵ�
		creatLastNode(o);
	}
	public void add(int index , Object o){//��indexǰ�����
		if(index == 0){//˵����������λ��Ϊ0����ô�����ͷ���
			createFirstNode(o);
		}else{//��ȥ�ҵ�ָ��λ��
			Node indexBeforeNode = getNode(index-1);//���ﷵ�ص���index��ǰһ���ڵ�
			Node newIndex =new Node(o,indexBeforeNode.next) ;//x�½ڵ㱣��indexBefore��ָ��
			indexBeforeNode.next = newIndex;
			size++;
		}
	}
	public Object get(int index){
		return getNode(index).data;//���ص��ǽڵ��е����ݶ���
	}
	
	public Object remove(int index){
		if(index==0){//�Ƴ�ͷ���
			removeFirst();
		}else{//�ҵ�ָ���ڵ��ǰһ���ڵ�
			Node removeNode = getNode(index-1);
			removeNode.next = removeNode.next.next;//�Ƴ���index
			size--;
			return removeNode.next.data;//�����Ƴ��ڵ�����ݶ���
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
			return removeHead.data;//����ͷ�������ݶ���
		}else{
			System.out.println("����Ϊ�գ�");
		}
		return null;
	}
	public Object removeLast(){
		if(size>0){
			Node removeLastBefore = getNode(size-2);//�ҵ�last�ڵ����һ���ڵ�
			Object returnObj = removeLastBefore.next.data; 
			removeLastBefore.next = null;
			last = removeLastBefore;
			size--;
			return returnObj;
		}else{
			System.out.println("����Ϊ�գ�");
		}
		return null;
	}
 
	/*
	 * ���ͷ���
	 * */
	private void createFirstNode(Object e){
		Node oldHead = head;
		Node newHead = new Node(e,oldHead);//�������Ľڵ���Ϊhead�ڵ��ǰһ�ڵ� 
		head = newHead;//���ܿղ��գ�headҪָ���µ�ͷ�ڵ�
		if(head == null){//�������Ϊ�գ�head��last��ָ���½ڵ㣨��Ϊ��last�Ͳ����Ҹ�ֵ����Ϊ��ȷ������������ģ�
			last = newHead;
		}else{//ͷ����Ѿ�����,�½ڵ���Ϊͷ��㣬ԭhead��ڶ���last����ָ������last
			newHead.next = head;
		}
		size++;
	}
	/*
	 * ���β���
	 * */
	private void creatLastNode(Object e){
		Node oldLast = last;
		Node newLast = new Node(e,null);//�µ�β�ڵ���һ���ڵ�Ϊ��
		last = newLast;//���ܿղ��գ�last��Ҫָ���µ�β�ڵ�
		if(head == null){//����Ϊ��
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

}
