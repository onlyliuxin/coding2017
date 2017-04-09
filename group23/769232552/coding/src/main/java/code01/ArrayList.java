package code01;

/**
 * Created by yaoyuan on 2017/3/6.
 */
public class ArrayList implements List {

    private int max_size = 0;//总长度
    private int current_size = 0; //当前长度
    private float extendPercent = 2; //扩展系数

    private Object[] elementData;

    /**
     * 默认构造函数，初始化数组长度为100
     */
    public ArrayList(){
        this.elementData = new Object[100];
        this.max_size = 100;
    }
    /**
     * 构造函数
     * @param size，初始化数组长度
     */
    public ArrayList(int size){
        this.elementData = new Object[size];
        this.max_size = size;
    }

    /**
     * 顺序添加元素，超出原始界限时，数组自动扩展
     */
    public void add(Object o) {
        //如果越界了，需要复制原有的数组到扩充后的数组中
        if(this.current_size + 1 > this.max_size) {
            this.max_size = (int) Math.floor(this.max_size * this.extendPercent);
            this.elementData = copyToNew(this.elementData,this.max_size);
        }
        this.elementData[this.current_size] = o;
        this.current_size ++;

    }

    /**
     * 指定位置添加元素
     * 一种是在中间，一种是当前插入的位置尾部(如果超过尾部则默认添加到尾部)
     */
    public void add(int index, Object o){
        assert(index>=0);
        //如果越界了，需要复制原有的数组到扩充后的数组中
        if(this.current_size + 1 > this.max_size) {
            //如果越界了，需要复制原有的数组到扩充后的数组中
            this.max_size = (int) Math.floor(this.max_size * this.extendPercent);
            this.elementData = copyToNew(this.elementData,this.max_size);
        }
        //数组中间插入
        if(index < this.current_size){
            //需要把当前位置的元素往后移动
            for (int i = this.current_size - 1; i >= index; i--) {
                this.elementData[i+1] = this.elementData[i];
            }
            this.elementData[index] = o;
        }else {
            //后面加入
            this.elementData[this.current_size] = o;
        }
        this.current_size ++;
    }

    public Object get(int index){
        if(index >= 0 && index <= this.current_size-1){
            return this.elementData[index];
        }else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    /**
     * 删除指定位置的元素
     * @param index
     * @return
     */
    public Object remove(int index){
        Object result = null;
        if(index >= 0 && index <= current_size-1){
            result = elementData[index];
            //删除操作
            if(index == current_size - 1){
                elementData[index] = null;
            }else {
                //需要把当前位置后面的元素往前移动
                for (int i = index; i < this.current_size-1 ; i++) {
                    this.elementData[i] = this.elementData[i+1];
                }
                this.elementData[this.current_size-1] = null;
            }
            this.current_size --;
        }else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return result;
    }

    public int size(){
        return this.current_size;
    }

    public Iterator iterator(){
        return new Iterator() {
            int next_pos = 0;
            int pos = -1;
            public boolean hasNext() {
                if(max_size <= 0){
                    return false;
                }
                return next_pos < ArrayList.this.size();
            }

            public Object next() {
                Object next = ArrayList.this.get(next_pos);
                pos = next_pos ++;
                return next;
            }
            public void remove(){
                ArrayList.this.remove(pos);
            }
        };
    }

    private Object[] copyToNew(Object[] oldArray, int extendSize){
        Object[] newArray = new Object[extendSize];
        for (int i = 0; i < size(); i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

}