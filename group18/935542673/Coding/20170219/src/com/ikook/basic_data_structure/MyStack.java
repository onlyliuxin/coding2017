package com.ikook.basic_data_structure;

/**
 * @author ikook;  QQ号码: 935542673
 */
public class MyStack {

	private MyArrayList elementDate = new MyArrayList();

	/**
	 * 入栈操作
	 * @param obj
	 */
	public void push(Object obj) {
		elementDate.add(obj);
	}

	/**
	 * 出栈操作
	 * @return
	 */
	public Object pop() {
		emptyExce();
		return elementDate.remove(topIndex());
	}

	/**
	 * 获取栈顶元素
	 * @return
	 */
	public Object getTop() {
		emptyExce();		
		return 	elementDate.get(topIndex());
	}

	/**
	 * 判断栈是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * 获取栈的深度
	 * @return
	 */
	public int size() {
		return elementDate.size();
	}
	
	/**
	 * 栈顶元素所在索引封装类
	 * @return
	 */
	private int topIndex() {
		return size() - 1;
	}
	
	/**
	 * 队列为空异常处理封装类
	 */
	private void emptyExce() {
		if (isEmpty()) {
			try {
				throw new Exception("队列为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
