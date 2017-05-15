package datastructure.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 用Queue来实现Josephus问题 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数： N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数，
 * 报到M的人会被杀死， 直到最后一个人留下来 该方法返回一个List， 包含了被杀死人的次序
 * 
 * @author liuxin
 *
 */
public class Josephus {

    public static List<Integer> execute(int n, int m) {
        List<Integer> list = new ArrayList<>();

        Queue queue1 = new Queue();
        for (int i = 0; i < n; i++) {
            queue1.enQueue(i);
        }
        Queue queue2 = new Queue();

        int index = 1;
        while (list.size() < n - 1) {
            int queue1Size = queue1.size();
            for (int i = 0; i < queue1Size; i++) {
                if (index % m != 0) {
                    queue2.enQueue(queue1.deQueue());
                } else {
                    list.add((int) queue1.deQueue());
                    index = 0;
                }
                index++;
            }
            int queue2Size = queue2.size();
            for (int i = 0; i < queue2Size; i++) {
                if (index % m != 0) {
                    queue1.enQueue(queue2.deQueue());
                } else {
                    list.add((int) queue2.deQueue());
                    index = 0;
                }
                index++;
            }
        }
        if (!queue1.isEmpty()) {
            list.add((int)queue1.deQueue());
        }
        if (!queue2.isEmpty()) {
            list.add((int)queue2.deQueue());
        }
        return list;
    }

}
