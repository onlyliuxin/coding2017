package com.coding.basic.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * @author liuxin
 *
 */
public class Josephus {
	
	public static List<Integer> execute(int n, int m){
		
		Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i < n; i++){
        	queue.enQueue(i);
        }
        
        List<Integer> result = new ArrayList<Integer>();
        int i = 0;

        while (!queue.isEmpty()) {

            int x = queue.deQueue();            

            if (++i % m == 0){
            	result.add(x);
            } else{
            	queue.enQueue(x);
            }
        }

        
        return result;
	}
	
}
