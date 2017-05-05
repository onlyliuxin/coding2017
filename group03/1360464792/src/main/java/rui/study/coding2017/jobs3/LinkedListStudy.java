package rui.study.coding2017.jobs3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 链表数据结构学习
 *
 * @author 赵睿
 */
public class LinkedListStudy {

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public LinkedList<Integer> reverse(LinkedList<Integer> linkedList) {
        if (linkedList.size() == 0 && linkedList.size() == 1) {
            return linkedList;
        }

        Object[] objs = linkedList.toArray();
        LinkedList<Integer> tempLinkedList = new LinkedList<Integer>();
        for (int i = objs.length - 1; i >= 0; i--) {
            tempLinkedList.add(Integer.parseInt(objs[i].toString()));
        }
        return tempLinkedList;
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public LinkedList<Integer> removeFirstHalf(LinkedList<Integer> linkedList) {
        if (linkedList.size() == 0 && linkedList.size() == 1) {
            return linkedList;
        }
        int size = linkedList.size() / 2;
        for (int i = 0; i < size; i++) {
            linkedList.removeFirst();
        }
        return linkedList;
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     *
     * @param i
     * @param length
     */
    public LinkedList<Integer> remove(int i, int length, LinkedList<Integer> linkedList) {
        if (linkedList.size() == 0) {
            return linkedList;
        }
        if (length == 0) {
            return linkedList;
        }
        if (length + i > linkedList.size()) {
            throw new IndexOutOfBoundsException("数组越界");
        }

        for (int j = 0; j < length; j++) {
            linkedList.remove(i);
        }
        return linkedList;
    }

    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     *
     * @param list
     */
    public static int[] getElements(LinkedList<Integer> list, List<Integer> listInt) {
        int empty[] = new int[]{};
        if (listInt.size() == 0) {
            return empty;
        }
        if (list.size() == 0) {
            return empty;
        }
        int[] temp = new int[listInt.size()];
        int length = list.size();
        for (int i = 0; i < listInt.size(); i++) {
            int index = listInt.get(i);
            if (index > length) {
                throw new IndexOutOfBoundsException("index为:" + index + "长度为：" + length);
            }
            temp[i] = list.get(index);
        }
        return temp;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     *
     * @param list
     */

    public LinkedList subtract(LinkedList list, LinkedList linkedList) {
        if (list.size() == 0) {
            return linkedList;
        }
        Iterator iterator = list.iterator();

        ArrayList array = new ArrayList();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (linkedList.contains(obj)) {
                array.add(obj);
            }
        }
        for (int i = 0; i < array.size(); i++) {
            linkedList.remove(array.get(i));
        }
        return linkedList;
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public LinkedList removeDuplicateValues(LinkedList linkedList) {
        if (linkedList.size() == 0 && linkedList.size() == 1) {
            return linkedList;
        }
        LinkedList temp = (LinkedList) linkedList.clone();
        for (int i = 0; i < linkedList.size() - 1; i++) {
            if (linkedList.get(i).equals(linkedList.get(i + 1))) {
                temp.remove(i);
            }
        }
        return temp;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     *  @param min
     * @param max
     */
    public LinkedList<Integer> removeRange(int min, int max, LinkedList<Integer> linkedList) {
        if (min >= max) {
            throw new IndexOutOfBoundsException();
        }

        int maxIndex=getIndex(max,linkedList,0,linkedList.size());
        int minIndex=getIndex(min,linkedList,0,linkedList.size());

        for (int i = minIndex; i <maxIndex +1; i++) {
            linkedList.remove(minIndex);
        }
        return linkedList;
    }

    private int getIndex(int i, LinkedList<Integer> linkedList, int index,int lastIndex) {
        int len=(index+lastIndex)/2;
        int o = linkedList.get(len);
        if (i < o) {
            return getIndex(i, linkedList, index,len);
        } else if (i > o) {
            return getIndex(i,linkedList,len,lastIndex);
        } else {
            return len;
        }
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     *
     */
    public LinkedList intersection(LinkedList listA,LinkedList listB) {
        if(listA.size()==0||listB.size()==0){
            return new LinkedList();
        }

        LinkedList<Integer> linkedList=new LinkedList<Integer>();
        getCommon(0,0,listA,listB,linkedList);

        return linkedList;
    }

    public void getCommon(int a,int b,LinkedList<Integer> listA,LinkedList<Integer> listB,LinkedList<Integer> list){
        if(listA.get(a)>listB.get(b)){
            if(a>=listA.size()+1){
                return;
            }
            getCommon(a,b+1,listA,listB,list);
        }else if(listA.get(a)<listB.get(b)){
            if(b>=listB.size()+1){
                return;
            }
            getCommon(a+1,b,listA,listB,list);
        }else{
            list.add(listA.get(a));
            if(a>=listA.size()-1 && b>=listB.size()-1){
                return;
            }
            getCommon(a+1,b+1,listA,listB,list);
        }
    }
}
