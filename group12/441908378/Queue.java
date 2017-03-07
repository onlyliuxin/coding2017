import java.util.Arrays;


public class Queue {

	
	private int size = 0;

	private Object[] elementData = new Object[100];
	
	public void enlargeCapacity(int minCapacity){
		int oldCapacity=elementData.length;
		if(oldCapacity<minCapacity){
			oldCapacity=(oldCapacity*3)/2+1;
		}
	}
	
	public void enQueue(Object o){		
		enlargeCapacity(elementData.length+1);
	 	elementData[elementData.length+1]=o;
	}
	
	public Object deQueue(){
		Object a = elementData[0];
		elementData=Arrays.copyOfRange(elementData, 1, elementData.length);
		return null;
	}
	
	public boolean isEmpty(){
		if(elementData.length==0){
		return true;
		}
		return false;
	}
	
	public int size(){
		return elementData.length;
	}
}
