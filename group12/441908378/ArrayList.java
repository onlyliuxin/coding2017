import java.util.Arrays;

public class ArrayList  {

private int size = 0;

private Object[] elementData = new Object[100];

public void enlargeCapacity(int minCapacity){
	int oldCapacity=elementData.length;
	if(oldCapacity<minCapacity){
		oldCapacity=(oldCapacity*3)/2+1;
	}
}

public void add(Object o){
 	enlargeCapacity(elementData.length+1);
 	elementData[elementData.length+1]=o;
}
public void add(int index, Object o){
	enlargeCapacity(elementData.length+1);
	Object data[]= Arrays.copyOfRange(elementData, index, elementData.length);
	elementData[index] = o;
	System.arraycopy(data, 0, elementData, index+1, elementData.length);
	
}

public Object get(int index){
	Object o;
	o=elementData[index];
	return o;
}

public Object remove(int index){
	Object o;
	o=elementData[index];
	Object data[]= Arrays.copyOfRange(elementData, index+1, elementData.length);
	System.arraycopy(data, 0, elementData, index, elementData.length);
	return o;
}

public int size(){
 return size;
}

//public Iterator iterator(){
//return null;
//}

}
