package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wanc on 2017/2/21.
 */
public class BasicTest {

    @Test
    public void test() {
        //测试
//        testArrayList();
//        testLinkedList();
//        testBinaryTreeNode();
//        testStack();
        testQueue();
    }


    public void testQueue(){
        Queue queue = new Queue();
        queue.enQueue("S");
        queue.enQueue("Y");
        queue.enQueue(5);
        System.out.println(queue);
        System.out.println("queue.size()="+queue.size());
        System.out.println("queue.deQueue()="+queue.deQueue());
        System.out.println(queue);
        System.out.println("queue.isEmpty()="+queue.isEmpty());
        System.out.println(queue);
    }
    public void testStack(){
        Stack stack = new Stack();
        stack.push("S");
        stack.push("Y");
        stack.push(5);
        System.out.println("stack.size()="+stack.size());
        System.out.println("stack.peek()="+stack.peek());
        System.out.println(stack);
        System.out.println("stack.isEmpty()="+stack.isEmpty());
        stack.pop();
        System.out.println(stack);
    }
    public void testBinaryTreeNode(){
        System.out.println("-------------------BinaryTreeNode 测试开始-------------------");
        System.out.println("new 一个实例");
        BinaryTreeNode root = new BinaryTreeNode();
        root.insert(5);
        root.insert(6);
        root.insert(9);
        root.insert(3);
        root.insert(3);
        root.insert(2);
        root.insert(10);
        System.out.println(root);
        System.out.println("-------------------LinkedList 测试结束-------------------");
    }
    public void testLinkedList() {
        System.out.println("-------------------LinkedList 测试开始-------------------");
        System.out.println("new 一个实例");
        LinkedList list = new LinkedList();
        System.out.println("1.add(\"A\") 添加元素----A");
        list.add("A");
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("2.add(\"B\") 添加元素----B");
        list.add("B");
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("3.add(3) 添加元素----3");
        list.add(3);
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("4.add(1, 3) 在下标1插入元素----3");
        list.add(1, 3);
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("5.add(3, 6) 在下标3插入元素----6");
        list.add(3, 6);
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("6.remove(0) 删除下标0元素");
        list.remove(0);
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("7.size() 获取size");
        System.out.println("结果："+list.size());
        System.out.println();
        System.out.println("8.addFirst(\"F\") 在首位前插入F");
        list.addFirst("F");
        System.out.println("结果："+list);
        System.out.println("9.addLast(\"K\") 在末位前插入K");
        list.addLast("K");
        System.out.println("结果："+list);
        System.out.println("10.removeFirst() 删除首位");
        list.removeFirst();
        System.out.println("结果："+list);
        System.out.println("11.removeLast() 删除末尾");
        list.removeLast();
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("12.迭代器");
        Iterator i = list.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
        System.out.println("-------------------LinkedList 测试结束-------------------");
    }
    public void testArrayList() {
        System.out.println("-------------------ArrayList 测试开始-------------------");
        System.out.println("new 一个实例");
        ArrayList list = new ArrayList();
        System.out.println("1.添加元素----A");
        list.add("A");
        Assert.assertEquals(list.get(0),"A");
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("2.添加元素----B");
        list.add("B");
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("3.添加元素----3");
        list.add(3);
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("4.在下标1插入元素----3");
        list.add(1, 3);
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("5.在下标3插入元素----6");
        list.add(3, 6);
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("6.删除下标0元素");
        list.remove(0);
        System.out.println("结果："+list);
        System.out.println();
        System.out.println("7.获取size");
        System.out.println("结果："+list.size());
        System.out.println();
        System.out.println("8.迭代器");
        Iterator i = list.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
        System.out.println("-------------------ArrayList 测试结束-------------------");
    }
}
