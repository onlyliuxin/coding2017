package Impl;

import Interface.ArrayList;
import Interface.Iterator;
import ex.MyArrest;

/**
 * Created by Administrator on 2017/2/25.
 */
public class MyArraryList extends ArrayList {
    private Object[] objArr;
    private int size;
    private int postion;

    public MyArraryList() {
        this.objArr = new Object[10];
        this.size = 10;
        this.postion = 0;
    }


    public MyArraryList(int size) {
        this.objArr = new Object[size];
        this.size = size;
        this.postion = 0;
    }

    public MyArraryList(Object[] objArr) {
        this.objArr = objArr;
        this.size = objArr.length;
        this.postion = objArr.length - 1;
    }

    @Override
    public void add(Object o) {
        int limit = this.size + (this.size / 2);
        Object[] newObjArr = new Object[limit];
        //public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)从指定源数组中复制一个数组，
        // 复制从指定的位置开始，到目标数组的指定位置结束。从src引用的源数组到dest引用的目标数组，
        // 数组组件的一个子序列被复制下来。被复制的组件的编号等于length参数。
        // 源数组中位置在srcPos到srcPos+length-1之间的组件被分别复制到目标数组中的destPos到destPos+length-1位置。
        System.arraycopy(this.objArr, 0, newObjArr, 0, objArr.length);
        this.postion = this.size - 1;
        newObjArr[this.postion] = o;
        this.size = limit;
        objArr = null;
        this.objArr = newObjArr;
    }

    @Override
    public void add(int index, Object o) {
        arrIndexVildate(index);
        objArr[index - 1] = o;
        size++;
    }

    @Override
    public Object get(int index) {
        arrIndexVildate(index);
        return objArr[index - 1];
    }

    @Override
    public Object remove(int index) {
        arrIndexVildate(index);
        Object remoteObj = objArr[index - 1];
        objArr[index - 1] = null;
        size--;
        //TODO need GC ccontrol
        return remoteObj;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator iterator() {
        return new ArrayListIterator(this);
    }

    private class ArrayListIterator implements Iterator {
        private MyArraryList arraryList;
        private int index;

        public ArrayListIterator(MyArraryList arraryList) {
            this.arraryList = arraryList;
            this.index = arraryList.size - 1;
        }

        @Override
        public boolean hasNext() {
            if (index > arraryList.size) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object next() {
            Object obj = arraryList.get(index);
            index++;
            return obj;
        }
    }

    private void arrIndexVildate(int index) {
        if (index > size - 1 || index < 0) {
            new Exception(String.format("cant than that array index %s,but got %", size - 1, index));
        }
    }

    //test method
    public static void main(String[] args) {
        MyArraryList myArrary = new MyArraryList();
        MyArrest.arrestEq(10, myArrary.size());
        myArrary.add(1, 10);
        MyArrest.arrestEq(10, myArrary.get(1));
        myArrary.add(100);
        System.out.println(myArrary.get(11));
        myArrary.remove(1);
        MyArrest.arrestIsNull(myArrary.get(1));
        if (myArrary.iterator().hasNext()) {
            myArrary.iterator().next();
        }
        System.out.println("test myArrary2");
        MyArraryList myArrary2 = new MyArraryList(20);
        MyArrest.arrestEq(20, myArrary2.size());
        myArrary2.add(1, 10);
        MyArrest.arrestEq(10, myArrary2.get(1));
        myArrary2.add(100);
        MyArrest.arrestIsNull(myArrary2.get(20));
        myArrary2.remove(1);
        MyArrest.arrestIsNull(myArrary2.get(1));
        if (myArrary.iterator().hasNext()) {
            myArrary2.iterator().next();
        }
    }
}
