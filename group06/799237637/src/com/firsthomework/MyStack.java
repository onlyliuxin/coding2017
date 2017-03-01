/*
 * 在ArrayList基础上实现Stack
 */

package com.firsthomework;
import java.util.ArrayList;
import java.util.EmptyStackException;


@SuppressWarnings("all")
public class MyStack {
	
	 private ArrayList elements=new ArrayList();
	 
	 public void push(Object o){
		 elements.add(o);
	 }
	
	public Object peek(){
		if(elements.size()==0){
			throw new EmptyStackException();
		}
		return elements.get(elements.size()-1);
	}
	
	public Object pop(){
		
		int len=elements.size();
		if(len==0){
			throw new EmptyStackException();
		}
		return elements.remove(len-1);
	}
	
	public boolean empty(){
		if(elements.size()==0){
			return true;
		}
		return false;
	}
	public int size(){
		return elements.size();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		MyStack ms=new MyStack();
		ms.push("top1");
		ms.push("top2");
		ms.push("top3");
		System.out.println(ms.peek());
		ms.pop();
		System.out.println(ms.peek());
		System.out.println(ms.size());
		
	
	
	}

}
