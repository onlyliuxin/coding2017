package com.johnChnia.coding2017.basic.queue;


import com.johnChnia.coding2017.basic.ArrayList;
import com.johnChnia.coding2017.basic.List;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序
 * 参考 http://interactivepython.org/courselib/static/pythonds/BasicDS/SimulationHotPotato.html
 *
 * @author john
 */
public class Josephus {

    public static List<Integer> execute(int n, int m) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }
        while (queue.size() > 1) {
            for (int i = 0; i < m - 1; i++) {
                queue.add(queue.remove());
            }
            arrayList.add(queue.remove());
        }
        return arrayList;
    }
}
