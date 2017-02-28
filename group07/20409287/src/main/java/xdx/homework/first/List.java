package xdx.homework.first;

/**
 * @param <E>
 * @Description: 定义一组操作有序列表的接口
 * @author: xdx
 * @date: 2017年2月21日 下午8:53:52
 */
public interface List<E> {

    /**
     * 添加元素
     *
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * 删除指定索引的元素
     *
     * @param int 索引下标
     * @return
     */
    E remove(int index);

    /**
     * 删除指定的元素
     *
     * @param e
     * @return
     */
    boolean remove(E e);

    /**
     * 返回列表长度
     *
     * @return
     */
    int size();

    /**
     * 判断列表是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 获取指定索引的元素
     *
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 重置某个索引的元素
     *
     * @param index
     * @param e
     * @return
     */
    boolean set(int index, E e);

    /**
     * 判断是否包含某个元素
     *
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * 清空列表
     */
    void clear();

    /**
     * 取得迭代器
     *
     * @return
     */
    Iterator<E> iterator();

}
