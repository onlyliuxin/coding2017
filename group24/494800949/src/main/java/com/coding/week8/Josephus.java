package com.coding.week8;

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
		CircleQueue<Integer> queue = new CircleQueue<Integer>();
		for (int i = 0; i < n; i++) {
			queue.enQueue(i);
		}
		List<Integer> list = new ArrayList<>();
		int j = 1;
		/*
			循环将队首的元素弹出，
			若j=m则当前元素放入集合，j=1
			若j<m,则当前元素放到队尾，j++,直到队列为空
		 */
		while (queue.size()>0) {
			Integer r = queue.deQueue();
			if (j == m) {
				list.add(r);
				j = 1;
			} else if (j < m) {
				queue.enQueue(r);
				j++;
			}
		}
		return list;
	}

	
}
