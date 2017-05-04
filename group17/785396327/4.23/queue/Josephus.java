package queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongxun on 2017/4/24.
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：  N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数,     报到M的人会被杀死， 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序
 */
public class Josephus {
    public static List<Integer> execute(int n, int m) {
        if (m <= 0)
            return null;
        List<Integer> origin = new ArrayList<Integer>();
        List<Integer> sequence = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            origin.add(i);
        }
        int count = 1;//计数器
        int index = count - 1;//当前元素的索引
        while (origin.size() > 1) {
            if (count == m) {
                sequence.add(origin.remove(index));
                count = 1;//删除一个元素，则重新计数
            } else {
                count++;
                if (index == origin.size() - 1)
                    //遍历到最后一个元素，重回头索引
                    index = 0;
                else
                    index++;
            }
        }
        sequence.add(origin.get(0));
        return sequence;
    }
}
