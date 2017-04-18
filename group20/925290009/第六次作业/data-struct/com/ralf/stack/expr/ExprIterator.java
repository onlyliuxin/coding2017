package com.ralf.stack.expr;

import java.util.ArrayList;

public class ExprIterator {

	private int operPos;
	private int numPos;
	private ArrayList<String> operateList = new ArrayList<>();
	private ArrayList<String> numList = new ArrayList<>();

	public ExprIterator(String exprString) {
		char[] chs = exprString.toCharArray();
		transToString(chs);
	}

	public Integer nextNumString() {
		if (hasNextNum()) {
			return Integer.parseInt(numList.get(numPos++));
		}
		return null;
	}
	public String nextOperateString() {
		if (hasNextOperate()) {
			return operateList.get(operPos++);
		}
		return null;
	}

	public boolean hasNextNum() {
		return numPos < numList.size();
	}
	
	public boolean hasNextOperate() {
		return operPos < operateList.size();
	}

	private void transToString(char[] chs) {

		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < chs.length; i++) {
			if (chs[i] == '+' || chs[i] == '-' || chs[i] == '*'
					|| chs[i] == '/') {
				numList.add(stringBuilder.toString());
				operateList.add(String.valueOf(chs[i]));
				stringBuilder.delete(0, stringBuilder.length());
			}
			else {
				stringBuilder.append(chs[i]);
			}
			
		}
		numList.add(stringBuilder.toString());
	}

}
