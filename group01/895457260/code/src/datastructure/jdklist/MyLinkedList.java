package datastructure.jdklist;

import datastructure.nongeneric.MyBaseList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Haochen on 2017/2/15.
 * TODO:
 */
public class MyLinkedList<T> extends MyBaseList<T> {

    private Node<T> head;
    private Node<T> rear;
    private int size;

    public MyLinkedList() {
        head = new Node<>();
        rear = new Node<>();
        clear();
    }

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;
    }

    @Override
    public int size() {
        return size;
    }

    private void addNode(Node node, Node previous) {
        node.next = previous.next;
        node.previous = previous;
        node.next.previous = node;
        previous.next = node;
    }

    private void removeNode(Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
        node.previous = null;
        node.next = null;
        node.data = null;
    }

    public void addFirst(T element) {
        add(0, element);
    }

    public void addLast(T element) {
        add(size(), element);
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size() - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;
            @Override
            public boolean hasNext() {
                return node.next != rear;
            }

            @Override
            public T next() {
                node = node.next;
                return node.data;
            }

            @Override
            public void remove() {
                if (node != head && node != rear) {
                    node = node.previous;
                    removeNode(node.next);
                    size--;
                }
            }
        };
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        Object[] result = a.length < size ? new Object[size] : a;
        for (Node node = head.next; node != rear; node = node.next) {
            result[result.length] = node.data;
        }
        return (T1[]) result;
    }

    private Node<T> findNode(Object o) {
        for (Node<T> node = head.next; node != rear; node = node.next) {
            if (node.data == null ? o == null : node.data.equals(o)) {
                return node;
            }
        }
        return null;
    }

    private Node<T> findNode(int index) {
        if (index == -1) {
            return head;
        } else if (index == size) {
            return rear;
        } else if (index < -1 || index > size) {
            indexOuOfBound(index);
        }
        Node<T> node;
        if (index > size() / 2) {
            node = rear.previous;
            for (int i = 0; i < size() - index - 1; ++i) {
                node = node.previous;
            }
        } else {
            node = head.next;
            for (int i = 0; i < index; ++i) {
                node = node.next;
            }
        }
        return node;
    }

    @Override
    public boolean remove(Object o) {
        Node node = findNode(o);
        removeNode(node);
        size--;
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Node target = findNode(index);
        Node newLinkHead = new Node();
        Node newLinkRear = newLinkHead;
        for (T t : c) {
            newLinkRear.next = new Node();
            newLinkRear.next.data = t;
            newLinkRear.next.previous = newLinkRear;
            newLinkRear = newLinkRear.next;
        }
        if (newLinkRear != newLinkHead) {
            newLinkRear.next = target.next;
            target.next.previous = newLinkRear;
            newLinkHead = newLinkHead.next;
            newLinkHead.previous = target;
            target.next = newLinkHead;
            size += c.size();
        }
        return true;
    }

    @Override
    protected T getNoCheck(int index) {
        return findNode(index).data;
    }

    @Override
    protected T setNoCheck(int index, T element) {
        Node<T> node = findNode(index);
        T t = node.data;
        node.data = element;
        return t;
    }

    @Override
    protected void addNoCheck(int index, T element) {
        Node pre = findNode(index - 1);
        Node node = new Node();
        node.data = element;
        addNode(node, pre);
        size++;
    }

    @Override
    public void clear() {
        head.next = rear;
        head.previous = rear;
        rear.next = head;
        rear.previous = head;
        size = 0;
    }

    @Override
    protected T removeNoCheck(int index) {
        Node<T> node = findNode(index);
        T t = node.data;
        removeNode(node);
        size--;
        return t;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node node;
        for (node = head.next; node != rear; node = node.next, index++) {
            if (node.data == null ? o == null : node.data.equals(o)) {
                break;
            }
        }
        return node == rear ? -1 : index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        Node node;
        for (node = rear.previous; node != head; node = node.previous, index--) {
            if (node.data == null ? o == null : node.data.equals(o)) {
                break;
            }
        }
        return node == head ? -1 : index;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        final int beginIndex = index - 1;
        return new ListIterator<T>() {
            private Node<T> node = findNode(beginIndex);
            private int index = beginIndex;

            @Override
            public boolean hasNext() {
                return node.next != rear;
            }

            @Override
            public T next() {
                node = node.next;
                index++;
                return node.data;
            }

            @Override
            public boolean hasPrevious() {
                return node.previous != head;
            }

            @Override
            public T previous() {
                node = node.previous;
                index--;
                return node.data;
            }

            @Override
            public int nextIndex() {
                return hasNext() ? index + 1 : index;
            }

            @Override
            public int previousIndex() {
                return hasPrevious() ? index - 1 : -1;
            }

            @Override
            public void remove() {
                if (node != head && node != rear) {
                    node = node.previous;
                    removeNode(node.next);
                    size--;
                }
            }

            @Override
            public void set(T t) {
                node.data = t;
            }

            @Override
            public void add(T t) {
                Node node = new Node();
                node.data = t;
                addNode(node, this.node);
                size++;
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        //这是错误实现
        Node<T> from = findNode(fromIndex);
        Node<T> to = findNode(toIndex);
        List<T> list = new MyLinkedList<>();
        if (from != null && to != null) {
            for (Node<T> node = from; node != to.next; node = node.next) {
                list.add(node.data);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(i);
            list.add(10 - i);
        }
        System.out.println("------------------size");
        System.out.println("size: " + list.size());

        System.out.println("------------------for(int i)");
        for (int i = 0; i < list.size(); ++i) {
            System.out.print(list.get(i) + " ");
        }

        System.out.println("\n-----------------foreach");
        for (int i : list) {
            System.out.print(i + " ");
        }

        System.out.println("\n-----------------iterator");
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println("\n-----------------listIterator");
        iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println("\n-----------------indexOf 0~10");
        for (int i = 0; i <= 10; ++i) {
            System.out.print("[" + i + "]" + list.indexOf(i) + " ");
        }

        System.out.println("\n-----------------lastIndexOf 0~10");
        for (int i = 0; i <= 10; ++i) {
            System.out.print("[" + i + "]" + list.lastIndexOf(i) + " ");
        }

        System.out.println("\n-----------------addFirst 100~104");
        for (int i = 100; i < 105; ++i) {
            list.addFirst(i);
        }
        list.print();
        System.out.println("-----------------addLast 200~204");
        for (int i = 200; i < 205; ++i) {
            list.addLast(i);
        }
        list.print();

        System.out.println("-----------------removeFirst x4");
        for (int i = 0; i < 4; ++i) {
            list.removeFirst();
        }
        list.print();

        System.out.println("-----------------removeLast x4");
        for (int i = 0; i < 4; ++i) {
            list.removeLast();
        }
        list.print();

        System.out.println("-----------------iterator remove");
        iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "[" + list.size() + "] ");
            iterator.remove();
        }
        System.out.println();
        list.print();
    }

}
