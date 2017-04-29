package queue;

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
		
		QueueWithTwoStacks<Integer> cq = new QueueWithTwoStacks<Integer>();
		for (int i = 0; i < n; i++) {
			cq.enQueue(i);
		}
		List<Integer> result = new ArrayList<>();
		while (cq.size() > 0) {
			for (int i = 0; i < m-1; i++) {
				cq.enQueue(cq.deQueue());
			}
			result.add(cq.deQueue());
		}
		
		return result;
	}
	
}
