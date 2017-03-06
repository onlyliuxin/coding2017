package day20170219;

/**
 * 先进后出，自顶向下
 * @author RedKnife
 *
 */
public class Stack {
	
	private ArrayList elementData = new ArrayList();
	
	private int currentIndex = 0;
	
	
	public Object push(Object o){
		elementData.add(o);
		currentIndex ++;
		return o;
	}
	
	public Object pop(){
		Object ob = peek();
		elementData.remove(currentIndex-1);
		return ob;
	}
	
	public Object peek(){
		return elementData.get(currentIndex-1);
	}
	
	public boolean isEmpty(){
		return currentIndex == 0 ;
	}
	
	public int size(){
		return elementData.size();
	}
}
