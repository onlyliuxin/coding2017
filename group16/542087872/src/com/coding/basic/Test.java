package com.coding.basic;


/**
 * Created by xiaoyuan on 25/02/2017.
 */
public class Test {
    public static void main(String[] args) {

        testArrayList();
        testLinkedList();

        testQueue();
        testStack();


        testBinaryTreeNode();
    }

    private static void testBinaryTreeNode() {

        BinaryTreeNode binaryTreeNode = new BinaryTreeNode(10);
        binaryTreeNode.insert(5);
        binaryTreeNode.insert(4);
        binaryTreeNode.insert(6);
        binaryTreeNode.insert(11);

        traverse(binaryTreeNode);

    }

    private static void traverse(BinaryTreeNode node) {
        if (node.getLeft() != null) {
            traverse(node.getLeft());
        }

        System.out.println("-- " + node.getData() + " --");

        if (node.getRight() != null) {
            traverse(node.getRight());
        }

    }


    static void testStack() {

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.size());
        System.out.println(stack.isEmpty());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println(stack.isEmpty());

    }

    static void testQueue() {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);

        System.out.println(queue.size());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.size());
    }
    static void testLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);

        System.out.println(linkedList.size());
        System.out.println(linkedList);

        linkedList.add(4);
        linkedList.add(5);
        System.out.println(linkedList.size());
        System.out.println(linkedList);


        linkedList.add(0, 10);
        linkedList.add(0, 9);
        System.out.println(linkedList.size());
        System.out.println(linkedList);

        System.out.println(linkedList.get(3));

        linkedList.remove(0);
        System.out.println(linkedList.size());
        System.out.println(linkedList);


        linkedList.addFirst(100);
        linkedList.addLast(8888);
        System.out.println(linkedList.size());
        System.out.println(linkedList);


        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println(linkedList.size());
        System.out.println(linkedList);

        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    static void testArrayList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        // test size and add
        System.out.println(arrayList.size());
        System.out.println(arrayList);


        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("13");

        // test size
        // test grow
        System.out.println(arrayList.size());
        System.out.println(arrayList);

        // test add at index
        arrayList.add(2, 100);
        System.out.println(arrayList.size());
        System.out.println(arrayList);

        // test remove
        arrayList.remove(0);
        System.out.println(arrayList.size());
        System.out.println(arrayList);
        arrayList.remove(2);
        System.out.println(arrayList.size());
        System.out.println(arrayList);

        // test iterator
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
