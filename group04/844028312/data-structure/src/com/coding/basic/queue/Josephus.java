package com.coding.basic.queue;

import java.util.ArrayList;
import java.util.List;

import com.coding.basic.Queue;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死，直到最后一个人留下来
 * @author liuxin
 *
 */
public class Josephus {
	
	public static List<Integer> execute(int n, int m){
		List<Integer> list=new ArrayList<Integer>();
		Queue queue1=new Queue();
		Queue queue2=new Queue();
		for(int i=0;i<n;i++){
			queue1.enQueue(i);
		}
		int start = 0;
		while(queue1.size()>1){
			int tag = 1;
			int i=0;
			while(tag<m){
				if(i==start){
					if(!queue1.isEmpty())
						queue2.enQueue(queue1.deQueue());
					else{
						reset(queue1,queue2);
						queue2.enQueue(queue1.deQueue());
					}
					tag++;
				}
				else{
					queue2.enQueue(queue1.deQueue());
					i++;
				}
			
			}
			if(tag==m){
				Object o;
				if(queue1.isEmpty()){
					o=queue2.deQueue();
				}
				else
					o=queue1.deQueue();
				list.add((Integer) o);
				start=queue2.size();
			}
			if(queue1.size()+queue2.size()>1)
				reset(queue1,queue2);
			else{
				if(!queue1.isEmpty())
					list.add((Integer) queue1.deQueue());
				if(!queue2.isEmpty())
					list.add((Integer) queue2.deQueue());
			}
		}
	
		return list;
	}
	private static void reset(Queue queue1,Queue queue2){
		if(queue1.isEmpty()){
			while(!queue2.isEmpty()){
				queue1.enQueue(queue2.deQueue());
			}
		}
		else{
			while(!queue1.isEmpty()){
				queue2.enQueue(queue1.deQueue());
			}
			while(!queue2.isEmpty()){
				queue1.enQueue(queue2.deQueue());
			}
		}
	}
}
