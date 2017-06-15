package code08;

import java.util.ArrayList;
import java.util.List;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序

 	0 1 2 3  4 5 6 7 8 9    m=3, n=10

 	第一个人(2)出列后的序列为：
    3 4 5 6 7 8 9 0 1 (0,1,2 出队列，0,1　再入队列)

 	第二个人(5)出列后的序列为：
 　　6 7 8 9 0 1　3 4  (3,4,5 出队列，3,4　再入队列)

 	...

    如果剩余队列长度小于等于m则依次出队列

 */
public class Josephus {
	
	public static List<Integer> execute(int n, int m){
		List<Integer> kList = new ArrayList<Integer>();
		CircleQueue<Integer> circleQueue = new CircleQueue<Integer>(n);

		for (int i = 0; i < n ; i++) {
			circleQueue.enQueue(i);
		}

		Queue<Integer> tmpQueue = new Queue<Integer>();
		while(!circleQueue.isEmpty()){

			if(m > circleQueue.size()) {
				m = m % circleQueue.size();
			}

			for (int j = 0; j < m-1; j++) {
				tmpQueue.enQueue(circleQueue.deQueue());
			}

			Integer kill = circleQueue.deQueue();
			kList.add(kill);

			for (int j = 0; j < m-1; j++) {
				circleQueue.enQueue(tmpQueue.deQueue());
			}
		}
		return kList;
	}
	
}
