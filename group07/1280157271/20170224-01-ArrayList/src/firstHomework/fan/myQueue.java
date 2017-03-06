package firstHomework.fan;

public class myQueue {
	private int iniLength = 10;
	private Object[] array = new Object[iniLength];
	private int size = 0;
	
	public void enQueue(Object o){	
		if(size>=array.length){//需要扩容
			Object[] newArray = new Object[iniLength+array.length];//array数组原长再加上10
			System.arraycopy(array, 0, newArray, 0, size);
			array = newArray;
		}
		array[size++] = o;
	}
	
	public Object deQueue(){//移除第一个元素，后面的元素整体向前位移
		Object deQueue = array[0];
		System.arraycopy(array, 1, array, 0, size-1);
		size--;
		return deQueue;
		
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public int size(){
		return this.size;
	}
	public static void main(String[] args) {  
        myQueue queue = new myQueue();  
        queue.enQueue("A");  
        queue.enQueue("B");  
        queue.enQueue("C");  
          
        queue.enQueue("D");  
        queue.enQueue("E");  
        queue.enQueue("F");  
        queue.enQueue("G");  
     
       while(!queue.isEmpty()){  
          System.out.println(queue.deQueue());//出队  
       }  
          
    }  
	
	
	
}
