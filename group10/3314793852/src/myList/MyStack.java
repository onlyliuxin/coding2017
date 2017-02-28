	package myList;
	
	public class MyStack {
		
		private int top;			//栈顶
		private Object[] arr;		//栈数据的存储数组
		private int theSize;			//栈的当前容量
		
		public MyStack(){
			clear();
		}
		
		//获取当前栈的容量
		public int size(){
			return theSize;
		}
		
		//栈清空，top赋值为-1，然后重新定义一个新的数组。
		public void clear() {
			top=-1;
			theSize=0;
			arr=new Object[100];
		}
		
		//push，进栈。
		public void push(Object aData){
			top++;
			arr[top]=aData;
			theSize++;
		}
		
		//pop，出栈。
		public Object pop(){
			Object a;
			a=arr[top];
			arr[top]=null;
			top--;
			theSize--;
			return a;
		}
		
		//打印出栈中的数据
		public void print(){
			for(int i=0;i<theSize;i++){
				System.out.println(arr[i]);
			}
		}
	}
