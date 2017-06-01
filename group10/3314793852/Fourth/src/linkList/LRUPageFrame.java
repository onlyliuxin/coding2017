	
	package linkList;
	
	/**
	 * 用双向链表实现LRU算法
	 * @author liuxin
	 * 该链表有头结点和尾节点。
	 */
	public class LRUPageFrame {
		
		private static class Node {
			
			Node prev;
			Node next;
			int pageNum;
	
//			Node() {
//			}
			
			Node(Node prev,Node next,int pageNum){
				this.prev=prev;
				this.next=next;
				this.pageNum=pageNum;
			}
		}

		
		private int capacity;
		private int currentSize;
		
		private Node first;// 链表头
		private Node last;// 链表尾
	
		
		public LRUPageFrame(int capacity) {
			this.capacity = capacity;
			first=new Node(null,null,0);
			last=new Node(first,null,0);
			first.next=last;
			currentSize=0;
		}
	
		/**
		 * 获取缓存中对象
		 * 存入数据。
		 * @param key
		 * @return
		 */
		public void access(int pageNum) {
			
			//判断当前大小是否等于容量，是，则进行删除头节点数据操作，否，直接插入。
			Node p=first;
			if(currentSize==capacity){
				//判断表中是否存在被引用量，删除指定值。
				int j=contain(pageNum);
				if(j!=0){
					delect(j);
				}
				else{
					delect(1);
				}
				//在表尾插入新节点。
				Node q=first;
				for(int i=0;i<=1;i++){
					q=q.next;
				}
				Node aNode=new Node(q,last,pageNum);
				q.next=aNode;
				last.prev=aNode;
				
			}else{
				for(int i=0;i<currentSize;i++){
					p=p.next;
				}
				Node aNode=new Node(p,last,pageNum);
				p.next=aNode;
				last.prev=aNode;
				
				currentSize++;
			}
		
		}
	

		
		/*
		 * 判断当前表中是否存在被引用的变量，并返回所在位置.
		 * 不存在则返回0。
		 */
		public int contain(int number){
			Node p=first;
			int flag=0;
			for(int i=1;i<=currentSize;i++){
				p=p.next;
				if(number==p.pageNum){
					flag=i;
				}
			}
			return flag;
		}
		
		/*
		 * 删除指定位置的值。
		 */
		public void delect(int place){
			Node p=first;
			for(int i=1;i<=place;i++){//根据位置，找到指定节点。
				p=p.next;
			}
			p.prev.next=p.next;
			p.next.prev=p.prev;
			
		}
		
		
		/*
		 * 打印链表中的内容。
		 */
		public String toString(){
			StringBuilder buffer = new StringBuilder();
			Node node = last.prev;
			while(node != null){
				if(node==first){
					break;
				}
				
				buffer.append(node.pageNum);
				node = node.prev;
				if(node != null&&node!=first){
					buffer.append(",");
				}
			}
			return buffer.toString();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 */
