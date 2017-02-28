package cn.mark;

public interface MyList {
    /**
     * 向集合中增加元素
     * @param o 
     */
    public boolean add(Object o);
    /**
     * 向集合指定的位置中增加元素
     * @param index 下标
     * @param o 元素
     */
    public boolean add(int index, Object o);
    /**
     * 从集合指定位置取出元素
     * @param index 下标
     * @return
     */
    public Object get(int index);
    /**
     * 从集合中删除指定位置的元素
     * @param index 下标
     * @return
     */
    public Object remove(int index);
    /**
     * 当前集合的元素个数
     * @return
     */
    public int size();
}