package me.lzb.basic;

/**
 * 先进后出
 * Created by LZB on 2017/3/11.
 */
public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    /**
     * 获取最后进的那个，并删除
     *
     * @return
     */
    public Object pop() throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("index boom");
        }

        return elementData.remove(elementData.size() - 1);
    }

    /**
     * 返回最后进去的元素
     *
     * @return
     */
    public Object peek() throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("index boom");
        }
        return elementData.get(elementData.size() - 1);
    }

    public boolean isEmpty() {
        if (elementData.size() <= 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return elementData.size();
    }
}
