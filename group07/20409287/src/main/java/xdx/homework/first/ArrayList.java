package xdx.homework.first;


public class ArrayList<E> implements List<E> {

    private Object[] elements;

    // 列表长度,默认10个元素
    private int size = 10;

    // 最后一个元素的位置
    private int position = -1;

    public ArrayList() {
        elements = new Object[size];
    }

    public ArrayList(final int capacity) {
        if (capacity <= 0)
            throw new RuntimeException("列表长度不可小于等于0!");
        elements = new Object[capacity];
    }

    /**
     * 添加元素
     *
     * @param e 要添加的元素
     * @return
     */
    @Override
    public boolean add(E e) {

        if (++position > size - 1) {
            grow();
        } else {
            elements[position] = e;
        }
        return true;
    }

    /**
     * 删除指定索引的元素
     *
     * @param index 索引下标
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {

        if (index < 0 || index > position || this.isEmpty())
            throw new IndexOutOfBoundsException("要删除的索引不正确!");

        E returnElement = (E) elements[index];
        elements[index] = null;
        System.arraycopy(elements, index + 1, elements, index, position + 1 - index);
        position--;
        return returnElement;
    }

    /**
     * 删除指定的元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean remove(E e) {

        if (!this.contains(e)) return false;
        int removeIndex = 0;
        for(int i = 0; i < this.position; i++) {
            if(elements[i].equals(e)) {
                removeIndex = i;
                break;
            }
        }
        remove(removeIndex);
        return true;
    }

    /**
     * 返回列表长度
     *
     * @return
     */
    @Override
    public int size() {
        return position + 1;
    }

    /**
     * 判断列表是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return position == -1;
    }

    /**
     * 获取指定索引的元素
     *
     * @param index
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index > position) return null;
        return (E)elements[index];
    }

    /**
     * 重置某个索引的元素
     *
     * @param index
     * @param o
     * @return
     */
    @Override
    public boolean set(int index, Object o) {
        if (index > position) return false;
        elements[index] = null;
        elements[index] = o;
        return true;
    }

    /**
     * 判断是否包含某个元素
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {

        if(this.isEmpty()) return false;
        for(int i = 0; i <= position; i++) {
            if (elements[i].equals(o))
                return true;
        }
        return false;
    }

    /**
     * 清空列表
     */
    @Override
    public void clear() {
        for(int i = 0; i <= this.size(); i++) {
            elements[i] = null;
        }
        position = -1;
    }

    /**
     * 取得迭代器
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * 数组增长
     */
    private void grow() {
        Object[] newElements = new Object[size << 2];
        System.arraycopy(elements, 0, elements, 0, this.size);
        elements = null;
        elements = newElements;
    }

    private class Itr implements Iterator<E> {

        private int itrIndex = 0;

        @Override
        public boolean hasNext() {
            return get(itrIndex) != null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            return (E) elements[itrIndex++];
        }

        @Override
        public void remove() {
            ArrayList.this.remove(itrIndex);
        }
    }

    @Override
    public String toString() {

        if(this.isEmpty()) {
            return "[]";
        }
        StringBuilder strList = new StringBuilder("[");
        for(int i = 0; i < this.size(); i++) {
            strList.append(elements[i].toString()).append(",");
        }
        strList.deleteCharAt(strList.length() - 1);
        strList.append("]");
        return strList.toString();
    }

}
