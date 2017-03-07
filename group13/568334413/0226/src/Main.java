import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import com.coding.basic.Queue;

import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        com.coding.basic.TreeSet treeSet = new com.coding.basic.TreeSet();
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(4);
        System.out.println("treeSet = " + treeSet.size);


    }

    public static void testQueueIterator() {
        Queue queue = new Queue();
        queue.enQueue("0");
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        while (queue.iterator.hasNext()) {
            System.out.println("next === " + queue.iterator.next());
        }

    }

    public static void testLinkedListIterator() {
        com.coding.basic.LinkedList arrayList = new com.coding.basic.LinkedList();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println("next === " + iterator.next());
        }
    }


    public static void testArrayListIterator() {
        com.coding.basic.ArrayList arrayList = new com.coding.basic.ArrayList();
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println("next === " + iterator.next());
            System.out.println("next =2== " + iterator.next());
        }
    }

    public static void linkedList() {
        Stack<String> stack = new Stack();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        String a = stack.peek();
        System.out.println("a = " + a);

//        LinkedList linkedList = new LinkedList();
//        linkedList.add("0");
//        linkedList.add("1");
//        linkedList.add("2");
//        linkedList.add("3");
//        linkedList.get(1);
//        linkedList.size();
        LinkedList linkedList = new LinkedList();
        linkedList.add("0");
        linkedList.add("1");
        linkedList.add(1, "2");
        linkedList.toString();
    }

    public static void arrayList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add("2");
        arrayList.remove(1);

//        arrayList.add(7, "100");

        System.out.println("arrayList = " + arrayList.size());
        java.util.List list = new ArrayList();

        com.coding.basic.ArrayList myList = new com.coding.basic.ArrayList();
        myList.add("0");
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");
        myList.add("6");
        myList.add("7");
        myList.add(8, "8");
        System.out.println("myList = " + myList.get(9));

        System.out.println("myList = " + myList.toString());
        myList.remove(1);
//        System.out.println("myList = " + myList.size());

        System.out.println("myList = " + myList.toString());

    }
}
