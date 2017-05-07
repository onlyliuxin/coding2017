package com.dudy.learn01.data_structure.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 用Queue来实现Josephus问题 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数： N个人围成一圈（位置记为0到N-1），
 * 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来 该方法返回一个List， 包含了被杀死人的次序
 */
public class Josephus {

	public static List<Integer> execute(int n, int m) {
		
		List<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new ArrayBlockingQueue<>(n);
		for(int i = 0; i< n; i++){
			queue.add(i);
		}
		int loop = 1;
		while(!queue.isEmpty()){
			Integer value = queue.poll();
			if(loop % m == 0){
				list.add(value);loop = 1;
			} else{
				queue.add(value);loop++;
			}
		}
		return list;
	}

}