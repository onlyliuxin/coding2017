package com.github.HarryHook.coding2017.queue;

import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orb.StringPair;

/**
 * 用Queue来实现Josephus问题 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数： N个人围成一圈（位置记为0到N-1），
 * 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来 该方法返回一个List， 包含了被杀死人的次序
 * 
 * @author liuxin
 *
 */
public class Josephus {

    public static List<Integer> execute(int n, int m) {
	Queue<Integer> queue = new Queue<>();
	List<Integer> list = new ArrayList<>();

	for (int i = 0; i < n; i++) {
	    queue.enQueue(i);
	}
	int i = 0;
	while (!(queue.isEmpty())) {
	    int j = queue.deQueue();
	    if (++i % m == 0) {
		list.add(j);
	    } else {
		queue.enQueue(j);
	    }
	}
	return list;
    }

    public static void main(String[] args) {
	Josephus circle = new Josephus();
	List<Integer> list = circle.execute(41, 3);
	System.out.println(list);
    }

}
