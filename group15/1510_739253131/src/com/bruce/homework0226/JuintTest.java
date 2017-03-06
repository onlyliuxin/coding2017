package com.bruce.homework0226;

import com.bruce.utils.MyException;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Bruce.Jiao on 17-2-23.
 */
public class JuintTest extends TestCase{

    @Test
    public void testArrayList(){
        try {
            ArrayListV00 arrayList = new ArrayListV00(0);
            arrayList.add("aaa");
            arrayList.add("bbb");
            arrayList.add("ccc");
            arrayList.add("fff");
            arrayList.add("ggg");
            System.out.println("集合下标2处的元素："+arrayList.get(2));
            System.out.println("是否包含ccc这个元素："+arrayList.contains("ccc"));
            System.out.println("是否包含ddd这个元素："+arrayList.contains("ddd"));
            System.out.println("删除前集合大小为："+arrayList.size());
            System.out.println("删除下标2处元素前底层数组："+arrayList);
            arrayList.remove(2);
            System.out.println("删除下标2处元素后底层数组："+arrayList);
            System.out.println("删除一个元素后集合大小为："+arrayList.size());
            arrayList.remove(2);
            System.out.println("再删除下标2处元素后底层数组："+arrayList);
            System.out.println("集合为："+ Arrays.toString(arrayList.toArray()));
            System.out.println("集合底层数组长度："+ arrayList.arrayLength());
//            System.out.println("集合下标-1处的元素："+arrayList.get(-1));
        } catch (MyException e) {
            System.out.println("发生异常>>>"+e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLinkedList(){
        try {
            LinkedListV00<String> linkedList = new LinkedListV00<>();
            linkedList.add("aaa");
            linkedList.add("bbb");
            linkedList.add("ccc");
            linkedList.add("ddd");
            System.out.println("删除index=2的元素前："+linkedList);
            System.out.println("链表尺寸"+linkedList.size());
            System.out.println("拿到index=2的元素"+linkedList.get(2));
            linkedList.remove(2);
            System.out.println("删除index=2的元素后："+linkedList);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStack(){
        try {
            StackV00 stack = new StackV00();
            stack.push("ccc");
            stack.push(null);
            stack.push("bbb");
            stack.push("aaa");
            System.out.println("栈的大小："+stack.size());
            System.out.println("栈是否为空："+stack.isEmpty());
            System.out.println("栈是否为空："+stack);
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            stack.clear();
            System.out.println("清空后，栈大小："+stack.size());
            System.out.println("栈是否为空："+stack.isEmpty());
        } catch (MyException e) {
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueue(){
        try {
            QueueV00 queue = new QueueV00();
            System.out.println("队列是否为空:"+queue.isEmpty());
            queue.add("aaa");
            queue.add("bbb");
            queue.add("ccc");
            queue.add("ddd");
            System.out.println(queue);
            System.out.println("queue.peek结果:"+queue.peek());
            System.out.println("peek后队列长度:"+queue.length());
            System.out.println("queue.poll结果:"+queue.poll());
            System.out.println("poll后队列长度:"+queue.length());
            System.out.println("队列是否为空:"+queue.isEmpty());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArrayLinked(){
        try {
            ArrayListV00 arrayList = new ArrayListV00();
            LinkedListV00 linkedList = new LinkedListV00();
            long start1 = System.currentTimeMillis();
            for(int i = 0;i<10000;i++){
                arrayList.add("abc"+i);
            }
            long end1 = System.currentTimeMillis();
            for(int i = 0;i<10000;i++){
                linkedList.add("abc"+i);
            }
            long end2 = System.currentTimeMillis();
            System.out.println("ArrayList的时间："+(end1-start1));
            System.out.println("LinkedList的时间："+(end2-end1));
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public String getRandomString(int length){
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<length;i++){
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
