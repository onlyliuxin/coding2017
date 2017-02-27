import java.util.List;

/**
 * Created by Great on 2017/2/23.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        LinkList linkList = new LinkList();
        Queue queue = new Queue();
        Stack stack = new Stack();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
            linkList.add(i);
            queue.add(i);
            stack.push(i);
        }
        System.out.println("ArrayList:");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println("LinkList:");
        for (int i = 0; i < linkList.size(); i++) {
            System.out.println(linkList.get(i));
        }
        System.out.println("Queue:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println("Stack:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
