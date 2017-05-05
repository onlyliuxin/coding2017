package GithubWork;

public class Queue {
     private int maxSize;
     private Object[] array;//存放元素数组
     private int front;//前一个元素索引
     private int rear;//后一个元素索引
     private int items=0;//元素个数
	//构造对象并初始化
     public Queue(int s){
       	maxSize=s;
       	array=new Object[maxSize];
       	front=0;
       	rear=-1;
       	
	}
     public void enQueue(Object o){
		  if(rear==maxSize-1){
			  rear=-1;
			}
		  array[++rear]=o;
		  items++;
				 
	}
	
	public Object deQueue(){
		Object temp =array[front++];
		if(front==maxSize){
			front=0;
		}
		items--;
		return temp;
	}
	
	public boolean isEmpty(){
		
		return items==0;
	}
	
	public int size(){
		return array.length;
	}
}
