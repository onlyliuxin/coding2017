package com.coding.basic.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 用Queue来实现Josephus问题 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数： N个人围成一圈（位置记为0到N-1），
 * 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来 该方法返回一个List， 包含了被杀死人的次序
 * 
 * @author liuxin
 *
 */
public class Josephus {

	public static List<Integer> execute(int n, int m) {
		if (n < m || n <= 0 || m < 0) {
			throw new RuntimeException("传入参数有误,执行失败!");
		}
		Stack<Integer> copy = new Stack<Integer>();
		Stack<Integer> count = new Stack<Integer>();
		for(int i = 0; i < n;i++){
			count.push(i);
		}
		while(!count.isEmpty()){
			copy.push(count.pop());
		}
		List<Integer> ints = new ArrayList<Integer>();
		int cot = 0;
		while(ints.size()<n){
			if(copy.isEmpty()){
				while(!count.isEmpty()){
					copy.push(count.pop());
				}
			}
			cot++;
			if(cot==m){
				ints.add(copy.pop());
				cot = 0;
			}else{
				count.push(copy.pop());
			}
		}
		return ints;
	}

	public static void main(String[] args) {
		List<Integer> list = execute(7, 2);
		System.out.println(list);
	}

}
