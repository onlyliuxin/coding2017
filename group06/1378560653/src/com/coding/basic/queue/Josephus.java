package com.coding.basic.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 用Queue来实现Josephus问题:约瑟夫环
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序
 * 实现方法：
 * 1.将所有的人（0~N-1）入队
 * 2.0~M-1出队，再入队，将M剔出
 * 3.重复2，直到剩下最后一个人
 * @author kai
 *
 */
public class Josephus {
	
	public static List<Integer> execute(int n, int m){	
		List<Integer> result = new ArrayList<>();
		Queue<Integer> queue = new Queue<Integer>();
		
		for(int i = 0; i < n; i++){
			queue.enQueue(i);
		}
		
		while(queue.size() > 1){
			for(int i = 0; i<m-1; i++){
				int temp = queue.deQueue();
				queue.enQueue(temp);
			}
			result.add(queue.deQueue());
		}
		result.add(queue.deQueue());
		return result;
	}
	
}
