package org.xukai.coderising.queue;

import com.google.common.base.Preconditions;

import java.util.ArrayDeque;
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
		Preconditions.checkArgument(n > 0);
		ArrayList<Integer> deadList = new ArrayList<>();
		ArrayDeque<Integer> deque1 = new ArrayDeque<>();
		ArrayDeque<Integer> deque2 = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			deque1.offer(i);
		}
		int count = 0;
		while(n >= m){
			if (deque1.isEmpty()) {
				while (!deque2.isEmpty()){
					deque1.offer(deque2.poll());
				}
			}
			count++;
			if (count == m) {
				deadList.add(deque1.poll());
				count = 0;
				n--;
				continue;
			}
			deque2.offer(deque1.poll());

		}

		return deadList;
	}


}
