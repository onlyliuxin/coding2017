public class MyLinkedList implements MyList {
	
	//定义了一个头结点，里面包含data和next（相当于指针）
	Node head;
	
	private int size = 0;//为了统计元素个数
	
	//构建链表对象
	private Node[] elementDate = new Node[10];
	
	@Override
	//在尾部增加一个节点
	public void add(Object o) {
		// TODO Auto-generated method stub
		Node n = new Node(o);
		if(head == null){
			head = n;
			head.next = null;
		}else{
			if(size<elementDate.length){
				elementDate[size].data = n.data;
				n.next = null;
			}else{
				elementDate = new Node[elementDate.length+5];
				elementDate[size].data = n.data;
				n.next = null;
			}
		}
		size++;
	}
	
	@Override
	public void add(int index, Object o) {
		// TODO Auto-generated method stub
		Node n = new Node(o);
		try{
			if(index < elementDate.length){
				elementDate[index-1].next = n;
				elementDate[index-1].data = n.data;
				n.next = elementDate[index];
			}else if(index == elementDate.length){
				elementDate[index-1].next = n;
				elementDate[index].data = n.data;
				n.next = null;
			}
		}catch(Exception e){
			System.out.println("捕获异常信息为"+e.getMessage());
		}
		size++;
	}

	//选取第index数据并返回data
	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		Object flag;
		try{
			flag = elementDate[index];
		}catch(Exception e){
			flag = null;
			System.out.println("捕获异常信息为"+e.getMessage());
		}
		return flag;
	}

	//返回删除的第index元素
	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		Node n = new Node();
		Object flag ;
		try{
			if(head == null){
				flag = null;
			}
			else if(index == size){
				flag = 	elementDate[index-1].data;
				elementDate[index -2].next = null;
			}else{
				flag = 	elementDate[index-1].data;
				elementDate[index].next = elementDate[index-2].next;
			}
		}catch(Exception e){
			flag = null;
			System.out.println("捕获异常为"+e.getMessage());
		}
		size--;
		return flag;
	}
	
	//返回元素个数
	public int size(){
		return size;
	}
	
	//在链表的头加一个元素
	public void addFirst(Object o){
		Node n = new Node(o);
		if(head == null){
			head = n;
			head.next = null;
		}
		n.next = head;
		size++;
	}
	
	//在链表的尾加一个元素
	public void addLast(Object o){
		Node n = new Node(o);
		if(head == null){
			head = n;
			head.next = null;
		}
		if(size>0 && size<elementDate.length-1){
			elementDate[size]= elementDate[size-1].next;
			elementDate[size].next = null;
		}
		if(size>elementDate.length){
			elementDate = new Node[elementDate.length+5];
			elementDate[size].data = n.data;
			n.next = null;
		}
		size++;
	}
	
	//将链表的头元素删除
	public Object removeFirst(){
		Object flag;
		if(head == null){
			flag = null;
		}else{
			flag = head.data;
			head = head.next;
		}
		size--;
		return flag;
	}
	
	//将链表的尾删除
	public Object removeLast(){
		Object flag;
		if(head == null){
			flag = null;
		}else{
			if(size<elementDate.length){
				flag = elementDate[size].data;
				elementDate[size].next = null;
			}else{
				elementDate = new Node[elementDate.length + 5];
				flag = elementDate[size].data;
				elementDate[size].next = null;
			}
		}
		size--;
		return flag;
	}
	
	//迭代器
	public MyIterator myiterator(){
		return null;
	}
	
	
	//创建链表结点内部类，必须是静态的，随着外部类的加载而加载
	 static class Node{
		
		private Node(){}
		public Node(Object o){
			o = this.data;
		}
		Object data;
		Node next;
	}
}
