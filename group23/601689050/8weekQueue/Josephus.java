
/**
 * Created by lxx on 2017/5/6.
 */



/**
 * 用Queue来实现Josephus问题
 * 在这个古老的问题当中， N个深陷绝境的人一致同意用这种方式减少生存人数：
 * N个人围成一圈（位置记为0到N-1），
 * 并且从第一个人报数， 报到M的人会被杀死， 直到最后一个人留下来
 * @author
 *
 */
public class Josephus {

    private static class Node {
        int data;
        Node next;

        public Node(int i) {
            data = i;
        }
    }

    public static void execute(int n, int m) {

        Node header = new Node(0);
        Node pointer = header;
        for (int i = 1; i < n; i++) {
            pointer.next = new Node(i);
            pointer = pointer.next;
        }
        pointer.next = header;
        while (pointer != pointer.next) {
            for (int i = 1; i <= m; i++) {
                pointer = pointer.next;
            }
            System.out.println(pointer.next.data);
            pointer.next = pointer.next.next;
        }
        System.out.println(pointer.next.data);

    }
    public static void main(String args[]){
        Josephus circle = new Josephus();
        circle.execute(8,4);
    }
}
