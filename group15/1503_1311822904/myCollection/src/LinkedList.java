public class LinkedList implements List {
	private int size = 0;
	private Node head=new Node(null);
	
	public void add(Object o){
		if(head.data==null){
			head.data=o;
			return;
		}
		Node n=head;
		while (n.next!=null){
			n=n.next;
		}
		n.next=new Node(o);
		size++;
	}
	public void add(int index , Object o){
		Node n=getNode(index);
		Node newN=new Node(o);
		newN.next=n.next;
		n.next=newN;
		size++;
	}
	public Object get(int index){
		return getNode(index).data;
	}
	private Node getNode(int index){
		Node n=head;
		for (int i=0;i<index;i++){
			if(n==null)
				throw new IndexOutOfBoundsException();
			n=	n.next;
		}
		return n;
	}
	public Object remove(int index){
		Object o;
		if (index==0){
			o=head.data;
			head.data=null;
			size--;
			return o;
		}
		Node n=getNode(index-1);
		o=n.next.data;
		n.next=n.next.next;
		size--;
		return o;
	}
	
	public int size(){

		return size;
	}
	
	public void addFirst(Object o){
		size++;
		if(head.data==null){
			head.data=o;
			return;
		}
		Node n=new Node(o);
		n.next=head;
		head=n;

	}
	public void addLast(Object o){
		size++;
		//要是还没有值 就是头
		if (head.data==null){
			head.data=o;
		}
		Node n=head;
		//找到最后一个
		while (n.next!=null){
			n=n.next;
		}
		//接上去
		n.next=new Node(o);

	}
	public Object removeFirst(){
		//没有头
		if(head.data==null)
			throw new IndexOutOfBoundsException();
		Object o=head.data;
		head=head.next;
		size--;
		return o;
	}
	public Object removeLast(){
		//还没有值 异常
		if (head.data==null){
			throw new IndexOutOfBoundsException();
		}
		size--;
		Object o;
		//只有头有值
		if(head.next==null){
			o=head.data;
			head.data=o;
			return o;
		}
		Node n=head;
		//找到最后第二个
		while (n.next.next!=null)
			n=n.next;
		//拿到最后一个值
		o=n.next.data;
		//去掉最后一个节点
		n.next=null;
		return o;
	}
	public Iterator iterator(){
		return new  LinkedIterator();
	}
	private class LinkedIterator implements Iterator{
		private Node n=head;
		@Override
		public boolean hasNext() {
			if(n!=null&&n.next!=null){
				n=n.next;
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return n.data;
		}
	}
	
	private static  class Node{
		Object data;
		Node next;
		Node(Object o){
			data=o;
		}

		
	}

	public String toString(){
		String s="[";
		for (int i=0;i<size;i++)
			s+=get(i)+", ";
		s+="]";
		return s;
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		PLinkedList<T> p= (PLinkedList<T>) this.clone();
		this.clear();
		for(int i=p.size()-1;i>-1;i--){
			this.add(p.get(i));
		}

	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8 [4 2
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10  [5 2

	 */
	public  void removeFirstHalf(){
		PLinkedList<T> p= (PLinkedList<T>) this.clone();
		this.clear();
		for(int i=p.size()/2;i<p.size();i++){
			this.add(p.get(i));
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length) throws Exception {
		int size=this.size();
		if(i+length>size) {
			throw new Exception("不够长");
		}
		for(int j=1;j<=length;j++){
			this.remove(i);
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
	public  int[] getElements(LinkedList<Integer> list){
		int[] result=new int[list.size()];
		for(int i=0 ;i<list.size(); i++){
			result[i]=(Integer) this.get(list.get(i));
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		for(Object o: list){
			this.remove(o);
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		PLinkedList<T> p= (PLinkedList<T>) this.clone();
		for(T t:this){
			p.remove(t);
			if(p.contains(t)){
				this.remove(t);
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
		int middle=this.size();
		int i=middle;
		int j=middle+1;


	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}
