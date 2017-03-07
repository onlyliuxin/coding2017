package hw1;

// mini jvm 用来做函数调用，比如表达式求值啦，都会用到栈
public class Stack {
	// 动态需要的成员属性
	private int size = 0;
	
	// ArrayList 使用我们自己定义的数据结构，自己吃自己的狗粮
	private ArrayList elementData = new ArrayList(); // 估计先调用自己吧
	
	// push对内部而言就是向数组插入一个元素，吃自己的狗粮了，狗粮是之前做的独立的
	public void push(Object o) {
		elementData.add(o);
		size++;
	}
	// 弹出一个元素，内部实现就是删除一个元素
	public Object pop() {
		//
		Object temp;
		temp = elementData.remove(size);
		size--; // 单独写，保证代码可读性
		return temp;
	}
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Object peek() {
		return elementData.get(size-1);
	}
	
	public int size() {
		return size;
	}
}
