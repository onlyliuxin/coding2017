/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass;

import java.util.Arrays;

/**
 *
 * @author CJ
 */
public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[100];

    private final int defaultGrowSize = 100; // 每次增长 100 个元素

//    private int curIterIndex = 0; // 用于记录 Iterator的索引

    private void CheckAndGrowUp() {
        if (size+1 > elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length + defaultGrowSize);
        }
    }

    // 添加一个方法，检测看，添加元素的话，是否需要增长，专门用于数组长度扩展的
    public void add(Object o) {
        CheckAndGrowUp();
        elementData[size] = o;
        size++;
    }

    @Override
    public void add(int index, Object o) {
        // 先探测是否添加数组大小
        CheckAndGrowUp();
        // 保留后半部分，然后 全部替换即可
        
        Object[] tmpObjectArr = Arrays.copyOfRange(elementData, index, elementData.length);
        elementData[index] = o;
        for (int i = index+1; i < size+1; i++) {
            elementData[i] = tmpObjectArr[i-index-1];
        }
        size++;

    }

    public Object get(int index) {
        // 应该是 不需要跑出 下标越界异常的，因为数组越界会自动抛出
        return elementData[index];
    }

    public Object remove(int index) {
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size] = null; // 后续后面是 数值？那么就不能为null了，而是零？
        size--;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    // 覆盖toString方法，方便测试
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayList: [");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]).append(", ");
        }
//        System.err.println(size);
        sb.delete(sb.length()-2,sb.length()).append("]");
        return sb.toString();
    }

    public Iterator iterator() {

        return new Iterator() {
            int curIterIndex = 0;
            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return  curIterIndex < size;
            }

            @Override
            public Object next() {
                // TODO Auto-generated method stub
                return elementData[curIterIndex++];
            }

        };
    }

    public static void main(String[] args) {
        ArrayList curArrList = new ArrayList();
        for (int i = 0; i <= 101; i++) {
            curArrList.add(i);
        }
        System.err.println("Test add Arr");
        System.err.println(curArrList);
        System.err.println("Test add in specific index");
        curArrList.add(10, 1010);
        System.err.println(curArrList);
        System.err.println("Test remove");
        curArrList.remove(10);
        System.err.println(curArrList);
        System.err.println("Test Iterator");
        Iterator curIter = curArrList.iterator();
        while(curIter.hasNext()){
            System.err.println(curIter.next());
        }
    }

}
