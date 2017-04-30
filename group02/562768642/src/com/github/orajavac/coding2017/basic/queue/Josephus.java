package com.github.orajavac.coding2017.basic.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序
 * @author liuxin
 *
 */
public class Josephus {
	
	public static List<Integer> execute(int n, int m){
		CircleQueue<Integer> q = new CircleQueue<Integer>();
		List<Integer> l = new ArrayList<Integer>();
		for (int i=1;i<=n;i++){
			q.enQueue(i);
		}
		for (int i=0;i<n;i++){
			for (int j=0;j<m;j++){
				Integer c = q.deQueue();
				if (j==m-1){
					l.add(c);
				}else{
					q.enQueue(c);
				}
			}
		}
		return l;
	}
	
	public static void main(String[] args){
		List<Integer> l = execute(8,4);
		for (int i=0;i<l.size();i++){
			System.out.print(l.get(i)+" ");
		}
	}
}
