package com.coding.basic.stack.expr.util;

import com.coding.basic.List;
import com.coding.basic.array.ArrayList;

/**
 * 表达式迭代器
 * @author zj
 * @since 2017-4-17
 */
public class ExprIterator{

	private List<String> data = new ArrayList<String>(); 
	private int cursor;
	
	public ExprIterator(String expr){
		this.data = FixExprUtil.FixExprToArray(expr);
	}

	public boolean hasNext() {
		return cursor != (data.size());
	}

	public String next() {
		
		int i = cursor;
		cursor = (i+1);
		return data.get(i);
	}
	
}
