package queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiaxun
 */
public class Josephus {

    public static List<Integer> execute(int n, int m) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n; i++) {
            queue.enQueue(i);
        }
        List<Integer> resultList = new ArrayList<>();
        while (queue.size() >= 1) {
            for (int i = 1; i < m; i++) {
                queue.enQueue(queue.deQueue());
            }
            resultList.add(queue.deQueue());
        }
        return resultList;
    }

}
