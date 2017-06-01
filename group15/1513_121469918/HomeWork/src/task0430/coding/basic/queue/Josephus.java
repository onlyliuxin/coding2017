package task0430.coding.basic.queue;

import java.util.ArrayList;
import java.util.List;

import task0228.coding.basic.Queue;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 
 * 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序
 * @author liuxin
 *
 */
public class Josephus {
	
	public static List<Integer> execute(int n, int m){
		if(n<=0 || m<=0 ){
			throw new RuntimeException("请设置正确的参数");
		}
		List<Integer> result = new ArrayList<>();
		
		Queue queue = new Queue();
		for (int i = 0; i < n; i++) {
			queue.enQueue(i);
		}
		
//		int x= 1;			
//		while(true){
//			queue.enQueue(queue.deQueue());
//			x++;
//			
//			if(x == m){
//				Integer i = (Integer) queue.deQueue();
//				if(queue.isEmpty()){
//					result.add(i);
//					break;
//				}
//				result.add(i);
//				x=1;
//			}
//		}
		
		int i = 0;
		while(!queue.isEmpty()){
			int x = (Integer)queue.deQueue();
			if(++i % m == 0){
				result.add(x);
			}else{
				queue.enQueue(x);
			}
		}
		return result;
	}
	
}