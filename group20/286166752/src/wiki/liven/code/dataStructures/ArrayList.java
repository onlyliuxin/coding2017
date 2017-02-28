package wiki.liven.code.dataStructures;

/**
 * Created by leven on 2017/2/21.
 */
public class ArrayList implements List{

    /**
     * 列表中元素的个数
     */
    private int size = 0;
    private int maxSize = 100;
    /**
     * 初始数组
     */
    private Object[] elementData = new Object[maxSize];

    /**
     * 在指定的位置i插入元素O
     * 插入元素,判断当前列表中元素的个数,
     * 当size==100,则需要扩张数组
     * 当size<100,则使用初始数组完成插入操作
     * 插入操作:
     *  从最后一个元素开始,往后移动一位,直到到index为止.
     * @param index
     * @param o
     */
    @Override
    public void add(int index, Object o) {
        if (size>=maxSize){
            Object[] targt = new Object[++maxSize];
            System.arraycopy(elementData,0,targt,0,maxSize);
            for (int j = targt.length;j>=index;j--){
                targt[j-1] = targt[j-2];
            }
            targt[index] = o;
            size++;
        }else if(size<maxSize){
            for (int j = elementData.length;j>=index;j--){
                elementData[j-1] = elementData[j-2];
            }
            elementData[index] = o;
            size++;
        }
    }

    /**
     * 追加元素
     * @param o
     */
    @Override
    public void add(Object o) {
        if (size>=maxSize){
            Object[] targt = new Object[++maxSize];
            System.arraycopy(elementData,0,targt,0,maxSize);
            targt[maxSize-1] = o;
            size++;
        }else if(size<maxSize){
            elementData[size-1] = o;
            size++;
        }
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public Object remove(int index) {
        Object temp = elementData[index];
        for (int i = index;i>size-1;i++){
            elementData[i] = elementData[i+1];
        }
        return temp;
    }

    @Override
    public int size() {
        return size;
    }




}
