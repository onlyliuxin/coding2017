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

/*
	public static List<Integer> execute(int n, int m){
		CircleQueue<Integer> queue = createCircleQueue(n);
		List<Integer> list = new ArrayList<Integer>();
		int index = m - 1;
		for(int i = 0; i < queue.size() - 1; i++){
			list.add(index);
			remove(queue, index);
			int start = index;
			index = getIndex(queue, start, m);
			
		}
		list.add(index);
		return list;
	}
	
	// 创建一个元素值为1-N的循环队列
	public static CircleQueue<Integer> createCircleQueue(int n){
		//最后一位为null,不存放数据， 因为循环队列里有一位空着，用来分辨队列是空还是满
		CircleQueue<Integer> queue = new CircleQueue<Integer>(n+1);
		for(int i = 0; i < n; i++){
			queue.enQueue(i+1);
		}
		return queue;
	}
	
	// 删除指定索引的元素，将该元素设置为null,并返回该元素的值
	public static int remove(CircleQueue<Integer> queue, int index){
		int value = queue.get(index);
		queue.set(index, null);
		return value;
	}
	
	// 获取要删除的元素的索引
	public static int getIndex(CircleQueue<Integer> queue, int start, int m){
		int index = 0;
		while(index < m){
			start = (start + 1) % queue.size();
			if(queue.get(start) != null){
				index++;
			}
		}
		return start % queue.size();
	}

*/
	public static List<Integer> execute(int n, int m){
		Queue<Integer> queue = new Queue<Integer>();
		for(int i = 0; i < n; i++){
			queue.enQueue(i);
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		int i = 1;
		while(!queue.isEmpty()){
			int value = queue.deQueue();
			if(i % m == 0){
				list.add(value);
			}else{
				queue.enQueue(value);
			}
			i++;
		}
		return list;
	}
	
}
