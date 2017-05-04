package com.coding.basic.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 知道最后一个人留下来
 * @author liuxin
 *
 */
public class Josephus {
	
	public static List<Integer> execute(int n, int m){
		if(m<1||n<=0){
			throw new IndexOutOfBoundsException();
		}
		List<Integer> result = new ArrayList<Integer>();
		Queue<Integer> q = new ArrayBlockingQueue<Integer>(n);
		for (int i = 0; i < n; i++) {
			q.add(i);
		}
		int k=1;
		while(!q.isEmpty()){
			int d = q.poll();
			if(k==m){
				result.add(d);
				k=1;
			}else{
				q.add(d);
				k++;
			}
		}
		return result;
	}
	
}
