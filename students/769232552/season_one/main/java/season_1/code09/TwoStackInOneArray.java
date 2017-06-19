package code09;

import code01.ArrayList;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 */
public class TwoStackInOneArray {

    Object[] data = new Object[10];
    int top1 = -1, end1 = 0;
    int top2 = data.length, end2 = data.length - 1;

    /**
     * 向第一个栈中压入元素
     * @param o
     */
    public void push1(Object o){
        if(top1+1 < top2){
            data[++top1] = o;
            return;
        }
        resize();
        data[++top1] = o;
    }

    /**
     * 从第一个栈中弹出元素
     * @return
     */
    public Object pop1(){
        if(top1 < 0){
            return null;
        }
        return data[top1--];
    }

    /**
     * 获取第一个栈的栈顶元素
     * @return
     */

    public Object peek1(){
        if(top1 < 0){
            return null;
        }
        return data[top1];
    }

    /**
     * 向第二个栈压入元素
     */
    public void push2(Object o){

        if(top2 - 1 > top1){
            data[--top2] = o;
            return;
        }
        resize();
        data[--top2] = o;
    }

    /**
     * 从第二个栈弹出元素
     * @return
     */
    public Object pop2(){
        if(top2 > data.length - 1){
            return null;
        }
        return data[top2++];
    }
    /**
     * 获取第二个栈的栈顶元素
     * @return
     */

    public Object peek2(){
        if(top2 > data.length - 1){
            return null;
        }
        return data[top2];
    }

    /**
     * 重新分配空间
     */
    private void resize() {
        System.out.println("resize data array ...");
        Object[] newData = new Object[20];
        //copy stack 1
        for (int i = end1; i <= top1; i++) {
            newData[i] = this.data[i];
        }
        //copy stack 2
        int newDataTop = newData.length;
        for (int j = end2; j >= top2 ; j--) {
            newData[--newDataTop] = this.data[j];
        }
        this.top2 = newDataTop;
        this.end2 = newData.length -1;
        this.data = newData;
        System.out.println("data array resize to " + this.data.length);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int i = this.end1;
        sb.append("stack 1 : ");
        for(; i <= this.top1; i++){
            sb.append(data[i].toString()+" ");
        }
        sb.append(", stack 2 : ");
        int j = this.end2;
        for(; j >= this.top2; j--){
            sb.append(data[j].toString()+" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TwoStackInOneArray s = new TwoStackInOneArray();
        s.push1(10);
        s.push1(11);
        s.push1(12);
        s.push1(13);
        s.push1(14);

        s.push2(20);
        s.push2(21);
        s.push2(22);
        s.push2(23);
        s.push2(24);

        System.out.println(s);

        s.push2(25);

        System.out.println(s);

        s.pop2();
        s.pop2();
        s.pop1();
        s.pop1();

        System.out.println(s);

    }

}