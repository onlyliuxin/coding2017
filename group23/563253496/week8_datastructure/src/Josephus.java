/**
 * Created by bdl19 on 2017/4/25.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中，
 * N个深陷绝境的人一致同意用这种方式减少生存人数：
 * N个人围成一圈（位置记为0到N-1）， 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * 该方法返回一个List， 包含了被杀死人的次序
 * "[1, 3, 5, 0, 4, 2, 6]", Josephus.execute(7, 2)
 *
 * @author liuxin
 */
public class Josephus {

    public static List<Integer> execute(int n, int m) {
        List<Integer> result = new ArrayList<>();

        List<Integer> list = new LinkedList<Integer>();
        int interval = m - 1;

        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 0) {
            /*for (int i = 0; i < m; i++) {
                index = (++index) % list.size();

            }*/
            index += interval;
            index %= list.size();
            result.add(list.get(index));
            list.remove(index);

        }


        return result;
    }

}