	
	package myList;
	
	public class MyQueue {
		
		private Object[] arr;
		private int head;
		private int tail;
		private int theSize;
		
		public MyQueue(){
			clear();
		}
		
		//清空。
		public void clear() {
			 head=0;
			 tail=0;
			 theSize=0;
			 arr=new Object[3];
		}
		
		//push,进入队列。
		public void push(Object aDate){
			
			if(theSize!=arr.length){//只有在队列没有满的情况写才可以插入数据。
				arr[tail]=aDate;
				tail++;
				if(tail>(arr.length-1)){//当tail已经到达数组尾部时，而数组的头部还为空时，则将新的数组插入到数组的头部。
					tail=0;
				}
				theSize++;
			}
		}
		
		//pop,出队列。
		public Object pop(){
			Object a=null;
			if(theSize!=0){	//如果队列为空，则不能进行出队列的操作。
				a=arr[head];
				arr[head]=null;
				head++;
				if(head>(arr.length-1)){
					head=0;
				}
				theSize--;
			}
			return a;
		}
		
		//打印出队列。
		public void print(){
			for(int i=0;i<arr.length;i++){
				if(arr[i]!=null){
					System.out.println(arr[i]);
				}
			}
		}
		
	}
