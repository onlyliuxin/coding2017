package firstHomework.fan;

 

public class myLinkedList implements List {
	private static  class Node{//链表的节点结构体
		Object data;//数据域
		Node next;	//Node引用，相当于指针，指向下一个节点
	
		 Node(Object e,  Node next) {
	            this.data = e;
	            this.next = next;
	        }
	}
	
	private Node head,last=null;//分别指向第一个和最后一个节点
	private int size;
	
	 
	public void add(Object o){//在尾部添加，就相当于添加尾节点
		creatLastNode(o);
	}
	public void add(int index , Object o){//在index前面插入
		if(index == 0){//说明传过来的位置为0，那么就添加头结点
			createFirstNode(o);
		}else{//就去找到指定位置
			Node indexBeforeNode = getNode(index-1);//这里返回的是index的前一个节点
			Node newIndex =new Node(o,indexBeforeNode.next) ;//x新节点保存indexBefore的指向
			indexBeforeNode.next = newIndex;
			size++;
		}
	}
	public Object get(int index){
		return getNode(index).data;//返回的是节点中的数据对象
	}
	
	public Object remove(int index){
		if(index==0){//移除头结点
			removeFirst();
		}else{//找到指定节点的前一个节点
			Node removeNode = getNode(index-1);
			removeNode.next = removeNode.next.next;//移除了index
			size--;
			return removeNode.next.data;//返回移除节点的数据对象
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
			return removeHead.data;//返回头结点的数据对象
		}else{
			System.out.println("链表为空！");
		}
		return null;
	}
	public Object removeLast(){
		if(size>0){
			Node removeLastBefore = getNode(size-2);//找到last节点的上一个节点
			Object returnObj = removeLastBefore.next.data; 
			removeLastBefore.next = null;
			last = removeLastBefore;
			size--;
			return returnObj;
		}else{
			System.out.println("链表为空！");
		}
		return null;
	}
 
	/*
	 * 添加头结点
	 * */
	private void createFirstNode(Object e){
		Node oldHead = head;
		Node newHead = new Node(e,oldHead);//传进来的节点作为head节点的前一节点 
		head = newHead;//不管空不空，head要指向新的头节点
		if(head == null){//如果链表为空，head和last都指向新节点（不为空last就不能乱赋值，因为不确定链表最后在哪）
			last = newHead;
		}else{//头结点已经存在,新节点作为头结点，原head变第二，last还是指向它的last
			newHead.next = head;
		}
		size++;
	}
	/*
	 * 添加尾结点
	 * */
	private void creatLastNode(Object e){
		Node oldLast = last;
		Node newLast = new Node(e,null);//新的尾节点下一个节点为空
		last = newLast;//不管空不空，last是要指向新的尾节点
		if(head == null){//链表为空
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

}
