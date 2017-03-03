package task01;

import java.util.Arrays;

/**第一周作业
 * 自己实现一个 LinkedList
 * Created by eurry on 2017/2/26.
 */
public class LinkedList {

    private int size = 0;
    private Node head=null;

    public void add(Object o){
        if(size == 0){
            head = new Node(o);
        }else{
            Node next = head;
            while(next.next != null){
                next = next.next;
            }
            next.next = new Node(o);
        }
        size++;
    }

    public void add(int index, Object o){
        if(index <= size){
            if(size == 0){
                add(o);
            }else{
                if(index==0){
                    addFirst(o);
                }else if(index==size){
                    add(o);
                }else{
                    Node next = head;
                    for(int i=0; i<size; i++){
                        Node temp = next.next;
                        if(i==index-1){
                            next.next = new Node(o);
                            next.next.next = temp;
                        }
                        next = next.next;
                    }
                    size++;
                }
            }
        }else{
            throw new IndexOutOfBoundsException("越界");
        }
    }

    public void addFirst(Object o){
        Node newHead = new Node(o);
        newHead.next = head;
        head = newHead;
        size++;
    }


    public Object get(int index){
        Node ele = null;
        Node next = head;
        for(int i=0; i<size; i++){
            if(i == index)
                ele = next;
            next = next.next;
        }
        return ele.element;
    }

    public Object remove(int index){
        if(index < size){
            Node re = null;
            if(index==0){
                re = head;
                head = head.next;
            }else{
                Node prev = null;
                Node next = head;
                for(int i=0; i<size; i++){
                    if(i == index-1)
                        prev = next;
                    next = next.next;
                }
                re = prev.next;
                prev.next = prev.next.next;
            }
            size--;
            return re.element;
        }else{
            throw new IndexOutOfBoundsException("越界");
        }
    }

    public Object removeFirst(){
        Object obj = head.element;
        head = head.next;
        size--;
        return obj;
    }

    public Object removeLast(){
        Node prev = null;
        Node next = head;
        for(int i=0; i<size; i++){
            if(i == size-2)
                prev = next;
            next = next.next;
        }
        Object obj = prev.next.element;
        prev.next = null;
        size--;
        return obj;
    }

    public int size(){
        return size;
    }

    public String toString(){
        String str = "";
        if(size > 0){
            Node ele = null;
            Node next = head;
            for(int i=0; i<size; i++){
                str += next.element.toString() + ",";
                next = next.next;
            }
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    /**
     * 静态内部类：节点
     */
    private static class Node{
        Object element;
        Node next = null;
        Node(Object o){
            this.element = o;
        }
    }

    /**
     * 测试
     */
    public static void main(String[] strs){
        LinkedList list = new LinkedList();
        list.add("A");
        list.add("B");
        list.add(1, "C");
        list.add("D");
        Object d = list.get(3);
        Object dd = list.remove(3);
        System.out.println(list.size());
        System.out.println(list.toString());
    }

}
