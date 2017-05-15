package task8.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 约瑟夫环
 */
public class Josephus {

    public static List<Integer> execute(int n, int m) {

        CircleQueue<Integer> queue = new CircleQueue<>(n);
        for (int i = 0; i < n; i++) {
            queue.enQueue(i);
        }

        List<Integer> resultList = new ArrayList<>(n);
        int flag = 0;
        while (!queue.isEmpty()) {
            flag++;
            if (flag % m == 0) {
                resultList.add(queue.deQueue());
            }
        }
        return resultList;
    }
}
