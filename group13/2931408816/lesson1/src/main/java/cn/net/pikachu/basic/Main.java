package cn.net.pikachu.basic;

/**
 * Created by pikachu on 17-2-21.
 */
public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
