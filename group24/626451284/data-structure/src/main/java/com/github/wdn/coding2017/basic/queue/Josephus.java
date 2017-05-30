package com.github.wdn.coding2017.basic.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2 0002.
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序
 * @author liuxin
 *
 */
public class Josephus {
    private static CircleQueue<Integer> queue;
    private static void init(int n){
        queue = new CircleQueue<>(n);
        for (int i = 0; i < n; i++) {
            queue.enQueue(i);
        }
    }
    public static List<Integer> execute(int n, int m){
        init(n);
        List<Integer> result = new ArrayList<>(n);
        int start = 1;//从1开始数
        while (!queue.isEmpty()){
            if(start==m){
                result.add(queue.deQueue());
                start = 1;
            }else{
                queue.enQueue(queue.deQueue());
                start++;
            }
        }
        return result;
    }

}
