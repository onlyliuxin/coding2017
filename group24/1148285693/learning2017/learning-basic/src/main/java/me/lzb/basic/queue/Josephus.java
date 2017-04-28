package me.lzb.basic.queue;

import java.util.*;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序
 * Created by LZB on 2017/4/27.
 */
public class Josephus {

    public static List<Integer> execute(int n, int m) {
        List<Integer> result = new ArrayList<>();

        java.util.Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }

        while (!(queue.size() < m)) {
            result.add(killOne(queue, m));
        }

        return result;
    }


    private static Integer killOne(java.util.Queue<Integer> queue, int m) {
        if (queue.isEmpty()) {
            return null;
        }

        if (queue.size() < m) {
            return null;
        }


        java.util.Queue<Integer> tmp = new LinkedList<>();
        int i = 1;
        while (!queue.isEmpty()) {
            if (i == m) {
                Integer re = queue.poll();

                move(queue, tmp);
                return re;

            } else {
                tmp.add(queue.poll());
                i++;
            }
        }

        return null;
    }

    private static void move(java.util.Queue<Integer> queue, java.util.Queue<Integer> queue2) {
        while (!queue2.isEmpty()) {
            queue.add(queue2.poll());
        }
    }


}
