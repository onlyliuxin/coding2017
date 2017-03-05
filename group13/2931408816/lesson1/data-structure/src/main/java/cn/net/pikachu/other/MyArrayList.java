package cn.net.pikachu.other;

import java.util.AbstractList;
import java.util.Objects;

/**
 * Created by pikachu on 17-2-19.
 */
public class MyArrayList<E> extends AbstractList<E>{
    // 当前能容纳的最大元素个数
    private int maxSize = 8;
    // 当前数组已经有了的元素个数
    private int curSize = 0;
    // 存放元素的数组
    private Object[] arr= new Object[curSize];
    /**
     * {@inheritDoc}
     *
     * @param index
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index<0 || index > curSize) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return (E) arr[index];
    }

    public int size() {
        return curSize;
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     * <p>
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     * <p>
     * <p>This implementation calls {@code add(size(), e)}.
     * <p>
     * <p>Note that this implementation throws an
     * {@code UnsupportedOperationException} unless
     * {@link #add(int, Object) add(int, E)} is overridden.
     *
     * @param e element to be appended to this list
     * @return {@code true}
     * @throws UnsupportedOperationException if the {@code add} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    @Override
    public boolean add(E e) {
        if (curSize>=maxSize){
            int t = maxSize;
            int limit = 1024;
            if(maxSize<limit){
                t*=2;
            }else {
                t+=limit;
            }
            Object[] tempArr = new Object[t];
            System.arraycopy(arr,0,tempArr,0,arr.length);
            arr = tempArr;
            maxSize=t;
        }
        arr[curSize++]=e;
        return true;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the <tt>set</tt> operation
     *         is not supported by this list
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and
     *         this list does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index>curSize){
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        E t = (E) arr[index];
        arr[index]=element;
        return t;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>This implementation always throws an
     * {@code UnsupportedOperationException}.
     *
     * @param index
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index>curSize){
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        E t = (E) arr[index];
        if (index<curSize-1){
            System.arraycopy(arr,index+1,arr,index,curSize-index);
        }
        curSize--;
        return t;
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     * <p>
     * <p>This implementation calls {@code removeRange(0, size())}.
     * <p>
     * <p>Note that this implementation throws an
     * {@code UnsupportedOperationException} unless {@code remove(int
     * index)} or {@code removeRange(int fromIndex, int toIndex)} is
     * overridden.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation
     *                                       is not supported by this list
     */
    @Override
    public void clear() {
        // 父类使用了迭代器
//        super.clear();
        curSize=0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>This implementation first gets a list iterator (with
     * {@code listIterator()}).  Then, it iterates over the list until the
     * specified element is found or the end of the list is reached.
     *
     * @param o
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public int indexOf(Object o) {
        // 父类使用了迭代器
//        return super.indexOf(o);
        for (int i = 0; i < curSize; i++) {
            if (Objects.equals(o,arr[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>This implementation returns <tt>size() == 0</tt>.
     */
    @Override
    public boolean isEmpty() {
//        return super.isEmpty();
        return curSize==0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>This implementation first gets a list iterator that points to the end
     * of the list (with {@code listIterator(size())}).  Then, it iterates
     * backwards over the list until the specified element is found, or the
     * beginning of the list is reached.
     *
     * @param o
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object o) {
        // 父类使用了迭代器
//        return super.lastIndexOf(o);
        for (int i = curSize-1; i >=0; i--) {
            if (Objects.equals(o,arr[i])){
                return i;
            }
        }
        return -1;
    }
}
