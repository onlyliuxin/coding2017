import java.util.*;

/**
 * 
 */

/**
 * @author CCD
 *
 */
public class MyStack {
        
    private ArrayList elementData = new ArrayList();
	private Object[] Myelement = elementData.toArray();
	private int  Length  = elementData.size();
	
	public void push(Object E){	
		Myelement[++Length] = E ;
	}
	
	public Object pop(){
		int NowLength = size()-1;
		Object obj = peek();
		Length--;
		Myelement[Length] = null ;
		return obj;
	}
	
	public Object peek(){
		int NowLength = size();
		if(NowLength == 0)
			throw new EmptyStackException();
		NowLength -= 1 ;
		if(NowLength >= Length )
			throw new ArrayIndexOutOfBoundsException(NowLength + " >= " + Length);
		return Myelement[NowLength];
		
	}
	public boolean isEmpty(){
		return size() == 0;
	}
	public int size(){
		return  Length;
	}
}
