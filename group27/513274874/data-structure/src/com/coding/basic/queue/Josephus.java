package com.coding.basic.queue;

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

		Queue<Integer> queue = new Queue<>();

		List<Integer> killed = new ArrayList<>();

		for(int i = 0 ;i< n ;i++){
			queue.enQueue(i);
		}

		int index = 0;

		while( !queue.isEmpty()){

			Integer num = queue.deQueue();

			index ++;

			if(index % m == 0){
				killed.add(num);
			}else {
				queue.enQueue(num);
			}
		}
		return killed;
	}


	
}
