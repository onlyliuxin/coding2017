package week08.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 用Queue来实现Josephus问题 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数： N个人围成一圈（位置记为0到N-1），
 * 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来 该方法返回一个List， 包含了被杀死人的次序
 * 
 * @author gallenzhang
 *
 */
public class Josephus {

	public static List<Integer> execute(int n, int m) {
		Queue<Integer> allQueue = new Queue<>();
		Queue<Integer> killQueue = new Queue<>();
		for(int i = 0; i < n; i++){
			allQueue.enQueue(i);
		}
		
		int k = 0;
		while(!allQueue.isEmpty()){
			Integer num = allQueue.deQueue();
			k++;
			if(k == m){
				killQueue.enQueue(num);
				k = 0;
			}else{
				allQueue.enQueue(num);
			}
		}
		List<Integer> killList = new ArrayList<>();
		while(!killQueue.isEmpty()){
			killList.add(killQueue.deQueue());
		}
		
		return killList;
	}
}
