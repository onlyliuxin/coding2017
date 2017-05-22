package com.coding.basic.stack;

import java.util.ArrayList;
import java.util.ArrayList;

/**
 * Created by bdl19 on 2017/4/8.
 */
public class Stack {
	private int count;
	private ArrayList elementData;

	public Stack() {
		this.count = 0;
		elementData = new ArrayList();
	}

	public void push(Object o) {
		count++;
		elementData.add(o);

	}

	public Object pop() {
		count--;
		Object o = elementData.get(count);
		elementData.remove(count);
		return o;
	}

	public Object peek() {

		return elementData.get(count-1);
	}

	public boolean isEmpty() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return count;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		Stack s = new Stack();
		while(!this.isEmpty()){
			sb.append(this.peek().toString());
			sb.append(",");
			s.push(this.pop());
		}
		while(!s.isEmpty()){
			this.push(s.pop());
		}

//       sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}

