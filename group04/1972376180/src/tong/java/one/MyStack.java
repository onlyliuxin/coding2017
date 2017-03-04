package tong.java.one;
/**
 * 自定义栈
 * @author tong
 *
 */
public class MyStack {
	private MyArrayList datas = new MyArrayList();
	private int size;
	//入栈
	public void push(Object o){
		datas.add(o);
		size++;
	}
	//出栈
	public Object pop(){
		Object topData = datas.remove(size-1);
		size--;
		return topData;
	}
	
	public Object peek(){
		return datas.get(size-1);
	}
	
	public boolean isEmpty(){
		if(size==0){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		return size;
	}

}
