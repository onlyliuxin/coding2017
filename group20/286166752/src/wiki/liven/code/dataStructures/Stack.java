package wiki.liven.code.dataStructures;

/**
 * Created by leven on 2017/2/21.
 * 栈:只允许在一端进行删除或者增加操作的线性表。
 * 本实现,采用ArrayList。
 */
public class Stack {


    private ArrayList list;//线性表
    private int top = -1;//栈顶指针,默认指向栈低


    /**
     * 元素进栈
     * @param o
     * 1.指针先加1
     * 2.将元素放入栈中
     *
     */
    public void push(Object o){
        top++;
        list.add(o);
    }

    /**
     * 栈顶元素出栈,返回栈顶指针的值
     * @return
     */
    public int pop(){
        if (top==-1)
            throw new IndexOutOfBoundsException("栈为空,无法执行出栈操作。");
        list.remove(top);
        top--;
        return top;
    }

    /**
     * 获取栈顶元素的值
     * @return
     */
    public Object getTop() {
        if (top==-1)
            throw new IndexOutOfBoundsException("栈为空,无法执行出栈操作。");
        Object o = list.get(top);
        return o;
    }

    /**
     * 判空
     * @return
     */
    public boolean stackEmpty(){
        if (top==-1){
            return true;
        }else {
            return false;
        }
    }

}
