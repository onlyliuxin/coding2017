package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mori on 2017/2/21.
 */
public class JavaTest {
    @Test
    public void testBinaryTreeNode(){
        BinaryTreeNode node = new BinaryTreeNode(5);
        node.insert(4);//左
        node.insert(7);//右
        node.insert(2);//左左
        node.insert(6);//右左
        node.insert(5);//右左左
        node.insert(6);//右左右
        System.out.println(node.getData());
        System.out.println(node.getLeft().getData());
        System.out.println(node.getRight().getData());
        System.out.println(node.getLeft().getLeft().getData());
        System.out.println(node.getRight().getLeft().getData());
        System.out.println(node.getRight().getLeft().getLeft().getData());
        System.out.println(node.getRight().getLeft().getRight().getData());
    }
    @Test
    public void testArrayList(){
        ArrayList<Integer> list =new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        Assert.assertEquals((Object) list.get(2),3);
        Assert.assertEquals((Object) list.remove(2),3);
        Assert.assertEquals((Object) list.get(2),5);
       Iterator listIterator = list.iterator();
        while (listIterator.hasNext()){
            System.out.println(listIterator.next());
        }
    }
    @Test
    public void testLinkedList(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(5);
        linkedList.addFirst(10);
        linkedList.add(1,6);
        // linkedList.removeLast();
        //linkedList.removeFirst();
        Iterator linkedListIterator = linkedList.iterator();
        while (linkedListIterator.hasNext()){
            System.out.println(linkedListIterator.next());
        }
        System.out.println("----");
        System.out.println(linkedList.remove(0));
        System.out.println(linkedList.remove(2));
        //System.out.println(linkedList.get(3));
        //System.out.println(linkedList.get(4));
    }
}