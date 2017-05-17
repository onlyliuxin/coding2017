package stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	private Object[] data;
	private int capacity;
	private int index1;//第一个栈的栈顶元素的索引
	private int index2;//第二个栈的栈顶元素的索引
	
	
	public TwoStackInOneArray(int capacity){
		this.capacity = capacity;
		data = new Object[capacity];
		index1 = -1;
		index2 = capacity;
	}
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if(isFull()){
			throw new IndexOutOfBoundsException("栈已满");
		}
		data[++index1] = o;
		
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(isEmpty1()){
			throw new IndexOutOfBoundsException("栈1 为空");
		}
		Object o = data[index1];
		data[index1--] = null;
		return o;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		return data[index1];
	}
	
	/**
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if(isFull()){
			throw new IndexOutOfBoundsException("栈已满");
		}
		data[--index2] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(isEmpty2()){
			throw new IndexOutOfBoundsException("栈2 为空");
		}
		Object o = data[index2];
		data[index2++] = null;
		return o;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		return data[index2];
	}
	
	public int size1(){
		return index1 + 1;
	}
	public int size2(){
		return capacity - index2;
	}
	
	public boolean isFull(){
		return index2 == (index1 + 1);
	}
	
	public boolean isEmpty1(){
		return index1 == 0;
	}
	
	public boolean isEmpty2(){
		return index2 == capacity - 1;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < capacity; i++){
			sb.append(data[i]);
			if(i < capacity - 1){
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
