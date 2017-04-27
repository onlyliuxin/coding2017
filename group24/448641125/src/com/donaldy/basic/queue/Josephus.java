package com.donaldy.basic.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：
 * N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死，
 * 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序
 * @author liuxin
 *
 */
public class Josephus {
	
	public static List<Integer> execute(int n, int m){

		List killList = new ArrayList();

		//用于存储
		Queue<Integer> queue1 = new Queue<>();
		//用于操作
		Queue<Integer> queue2 = new Queue<>();

		for (int i = 0; i < n; ++i) {

			queue1.enQueue(i);

		}

		int cnt = 1;

		while (true) {

			if (queue1.size() < m) {
				break;
			}

			if (cnt % m == 0) {

				killList.add(queue1.deQueue());

			} else {

				queue1.enQueue(queue1.deQueue());

			}

			cnt ++;

		}

		while (!queue1.isEmpty()) {
			killList.add(queue1.deQueue());
		}


		return killList;
	}
	
}
