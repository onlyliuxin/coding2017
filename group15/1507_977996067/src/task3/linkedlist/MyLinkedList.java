package task3.linkedlist;

import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> {

    //存放的元素数量
    private int size;

    private Node<T> head;

    public MyLinkedList() {
        head = new Node<>(null, null);
    }

    public void add(T o) {
        add(size, o);
    }

    public void add(int index, T o) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index " + index + " 不合法");
        Node<T> targetNode = new Node<>(null, o);
        Node<T> targetPrevNode = getPrevNode(index);
        targetNode.next = targetPrevNode.next;
        targetPrevNode.next = targetNode;
        size++;
    }

    public T get(int index) {
        checkIndexRange(index);
        return getPrevNode(index).next.data;
    }

    public Node<T> getNode(int index) {
        checkIndexRange(index);
        return getPrevNode(index).next;
    }


    public T remove(int index) {
        checkIndexRange(index);
        Node<T> prevNode = getPrevNode(index);
        Node<T> nodeToRemove = prevNode.next;
        prevNode.next = nodeToRemove.next;
        size--;
        return nodeToRemove.data;
    }

    public int size() {
        return size;
    }

    public void addFirst(T o) {
        add(0, o);

    }

    public void addLast(T o) {
        add(size, o);
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }


    public Iterator<T> iterator() {
        return new MyLinkedItr();
    }

    /**
     * 找到位置为index的前一个node
     *
     * @param index 索引值
     */

    private Node<T> getPrevNode(int index) {
        Node<T> targetPrevNode = head;
        for (int i = 0; i < index; i++) {
            targetPrevNode = targetPrevNode.next;
        }
        return targetPrevNode;
    }

    /**
     * 检查索引是否越界
     *
     * @param index 索引值
     */
    private void checkIndexRange(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + " 越界");
    }

    private static class Node<T> {
        private Node<T> next;
        private T data;

        private Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    private class MyLinkedItr implements Iterator<T> {

        private Node<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public T next() {
            Node<T> nextNode = currentNode.next;
            T data = nextNode.data;
            currentNode = nextNode;
            return data;
        }

        @Override
        public void remove() {
            currentNode.next = currentNode.next.next;
        }
    }

    @Override
    public String toString() {
        if (size == 0)
            return "[]";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(get(i)).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * ==================================
     * 3.12作业
     * ==================================
     * <p>
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse() {
        if (size == 0)
            return;
        int length = size;
        for (int i = length - 1; i >= 0; i--) {
            add(get(i));
        }
        remove(0, length); //   :(
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public void removeFirstHalf() {
        remove(0, size / 2);
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     */
    public void remove(int i, int length) {
        if (length == 0)
            return;
        if (i + length > size)
            throw new IndexOutOfBoundsException("长度不够");
        Node<T> startNode = getPrevNode(i);
        startNode.next = (i + length == size) ? null : getNode(i + length);
        size -= length;
    }

    /**
     * 假定当前链表和listB均包含已升序排列的整数
     * 从当前链表中取出那些listB所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     */
    @SuppressWarnings("unchecked")
    public T[] getElements(MyLinkedList<Integer> list) {
        int size = list.size();
        Comparable[] result = new Comparable[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            result[count++] = get(list.get(i));
        }
        return (T[]) result;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素
     */

    public void subtract(MyLinkedList list) {
        int length = list.size();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < size; j++) {
                if (get(j).equals(list.get(i))) {
                    remove(j);
                    break;
                }
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues() {
        Iterator<T> iterator = iterator();
        int pos = 0;
        while (iterator.hasNext()) {
            //当前索引的值等于下一个索引的值时,就把当前索引删掉
            if (get(pos).equals(get(pos + 1))) {
                remove(pos);
            }
            pos++;
            iterator.next();
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     */
    public void removeRange(T min, T max) {
        if (min.compareTo(max) >= 0)
            throw new RuntimeException("Are you kidding me ?");
        int minIndex = 0;
        int maxIndex = size;
        for (int i = 0; i < size; i++) {
            if (get(i).compareTo(min) > 0) {
                minIndex = i;
                break;
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            if (get(i).compareTo(max) < 0) {
                maxIndex = i;
                break;
            }
        }
        remove(minIndex, maxIndex - minIndex + 1);

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     */
    public MyLinkedList intersection(MyLinkedList<T> list) {
        MyLinkedList<T> resultList = new MyLinkedList<>();
        int firstLength = size;
        int secondLength = list.size();
        int firstPos = 0;
        int secondPos = 0;
        while (firstPos < firstLength && secondPos < secondLength) {
            T firstItem = get(firstPos);
            T secondItem = list.get(secondPos);
            int compareResult = firstItem.compareTo(secondItem);
            if (compareResult == 0) {
                resultList.add(firstItem);
                firstPos++;
                secondPos++;
            } else if (compareResult < 0)
                firstPos++;
            else
                secondPos++;
        }
        return resultList;
    }
}