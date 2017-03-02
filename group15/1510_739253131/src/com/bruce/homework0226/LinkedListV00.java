package com.bruce.homework0226;

import com.bruce.utils.MyException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * 实现LinkedList的基本功能
 * Created by Bruce.Jiao on 2017/2/25.
 */
public class LinkedListV00<E> implements Serializable {

    /**
     * 双向链表中节点实例的个数
     */
    private transient int size = 0;

    /**
     * 前一个节点
     */
    private transient Node<E> first;

    /**
     * 后一个节点
     */
    private transient Node<E> last;

    /**
     * 空构造
     */
    public LinkedListV00(){}

    /**
     * 添加一个节点
     * @param element
     * @return
     */
    public boolean add(E element){
        linkNext(element);
        return true;
    }

    /**
     * 拿到制定位置的元素
     * @param index
     * @return
     */
    public E get(int index) throws MyException{
        if(index < 0 || index > size){
            throw new MyException("索引越界");
        }
        return node(index).element;
    }

    /**
     * 删除指定位置的元素
     * 将index-1处节点的next指向index+1处节点，将index+1处节点的previous指向index-1节点
     * @param index 节点位置
     * @return 删除节点的element数据
     */
    public E remove(int index) throws MyException{
        if(index < 0 ||  index > size){
            throw new MyException("节点索引越界");
        }
        return unlink(node(index));
    }

    /**
     * 返回链表的长度
     * @return 双向链表中节点实例的个数
     */
    public int size(){
        return size;
    }

    /**
     * 拿到链表对应的数组
     * @return 链表各节点的element元素组成的数组
     */
    public Object[] toArray(){
        Object[] array = new Object[size];
        for(int i = 0;i<size;i++){
            array[i] = node(i).element;
        }
        return array;
//源码
//        Object[] result = new Object[size];
//        int i = 0;
//        for (Node<E> x = first; x != null; x = x.next)
//            result[i++] = x.element;
//        return result;
    }

    /**
     * 表示一个节点的内部类
     * @param <E> 链表元素泛型
     */
    private static class Node<E>{
        E element;
        Node<E> previous;
        Node<E> next;
        Node(Node<E> previous,E element,Node<E> next){
            this.element = element;
            this.previous = previous;
            this.next = next;
        }
    }

    /**
     * 根据索引拿对应的节点
     * @param index 索引号
     * @return 索引号对应的节点
     */
    private Node<E> node(int index){
        Node<E> x;
        //如果index小于size的一半，即目标节点在链表前半部分
        if(index < (size >> 1)){
            //从第一个节点挨个向后查找,一直到（index-1）处，将其next赋值给x
            x = first;
            for(int i = 0; i<index;i++){
                x = x.next;
            }
        }else{
            //否则，即目标节点在链表后半部分,
            //从最后一个节点挨个向前查找，一直查找到（index+1）处，将其previous赋值给x
            x = last;
            for(int i = size-1;i>index;i--){
                x = x.previous;
            }
        }
        //返回x
        return x;
    }

    /**
     * 链接下一个
     * @param e 新节点的element
     */
    private void linkNext(E e){
        //将当前的last节点赋值给n
        final Node<E> n = last;
        //创建一个新的Node节点，其previous为n,next为null
        final Node<E> newNode = new Node<E>(n,e,null);
        //创建一个新的节点后，则last更新为新节点newNode
        last = newNode;
        //如果n为null，说明还是一个空的双向链表，将新节点newNode赋值给first
        //否则，将newNode赋值给n的next
        if(n == null){
            first = newNode;
        }else{
            n.next = newNode;
        }
       //添加一个新节点后，size加1
        size++;
    }

    /**
     * 从链表解除一个节点
     * @param node 将要被链表接触关联的目标节点
     * @return 目标节点的element元素
     */
    private E unlink(Node<E> node){
        //拿到传入节点的元素
        final E element = node.element;
        //拿到传入节点的next节点
        final Node<E> next = node.next;
        //拿到传入节点的previous节点
        final Node<E> previous = node.previous;
        //如果传入节点的previous=null，说明是第一个节点
        if(previous == null){
            //将链表第一个节点指向本节点的下一个节点next，即把原有的第一个节点解除
            first = next;
        }else{
            //将本节点前一个节点的next指向本节点后一个节点，即跳过了本节点
            previous.next = next;
            //将本节点的previous节点设置为null
            node.previous = null;
        }
        //如果传入节点的next=null，说明是最后一个节点
        if(next == null){
            //将链表最后一个节点指向本节点的前一个节点,即把原来的最后一个节点解除
            last = previous;
        }else{
            //将本节点下一个节点的previous节点指向本节点的前一个节点，即跳过了本节点
            next.previous = previous;
            //本节点的next节点设置为null
            node.next = null;
        }
        node.element = null;
        size--;
        return element;
    }

    /**
     * 仅作为临时方法，打印链表元素使用，方面查看
     * @return
     */
    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
