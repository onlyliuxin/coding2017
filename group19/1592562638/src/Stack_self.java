/*范例名称：
 * 原文件名称：
 * 要点：
 * 1. 实现基本的数据结构类：Stack

 */
public class Stack_self<T> {
	private ArrayList_self<T> elementData=new ArrayList_self<T>();
	private int size=0;
	
	//进栈
	public void push(Object item){
		elementData.add((T)item);
		size++;
	}
	
	//出栈
	public Object pop(){
		if(size>0){
			size--;
			return elementData.remove(size);
		}
		else{
			return null;
		}
	}
	
	//取出栈顶元素
	public Object peek(){
		return elementData.get(size-1);
	}
	
	//判断是否为空
	public boolean isEmpty(){
		return (size==0);
	}
	
	//返回size
	public int size(){
		return size;
	}
}
