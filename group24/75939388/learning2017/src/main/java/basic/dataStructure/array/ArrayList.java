package basic.dataStructure.array;

import basic.dataStructure.List;

/**
 * Created by macvi on 2017/4/2.
 */
public class ArrayList implements List {
    private int size = 10;
    //每次扩容的长度，默认为10
    private int extendSize = 10;

    private Object[] data = new Object[size];

    public ArrayList(Object o) {
        this.add(o);
    }

    public ArrayList(){}

    public void add(Object o) {
        if (this.size() == this.size) {
            this.size += extendSize;
            Object[] newData = new Object[this.size];
            System.arraycopy(this.data, 0, newData, 0, this.data.length);
            this.data = newData;
        }

        for (int i = 0; i < this.data.length; i++) {
            if (data[i] == null) {
                data[i] = o;
                break;
            } else continue;
        }
    }

    public void add(int index, Object o) {
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if(this.size() == this.size){
            this.size += extendSize;
        }

        Object[] newData = new Object[this.size];

        System.arraycopy(this.data, 0, newData, 0, index);
        newData[index] = o;
        System.arraycopy(this.data, index, newData, index + 1, this.size() - index);

        this.data = newData;
    }

    public Object get(int index) {
        if(index > this.size() || index < 0){
            throw new IndexOutOfBoundsException();
        }
        for(int i = 0; i < this.size(); i ++){
            if(index == i){
                return this.data[i];
            }
        }

        return null;
    }

    public Object remove(int index) {
        if(index > this.size() || index < 0){
            throw new IndexOutOfBoundsException();
        }

        Object[] newData = new Object[this.size];
        Object removed = this.get(index);

        System.arraycopy(this.data, 0, newData, 0, index);
        System.arraycopy(this.data, index + 1, newData, index, this.size() - index);
        this.data = newData;
        return removed;
    }

    public int size() {
        int size = 0;
        for(Object obj : this.data){
            if(obj != null){
                size += 1;
            }
        }

        return size;
    }

    public boolean contains(Object obj){
        for(int i = 0; i < this.size(); i++){
            if(obj == this.get(i)){
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(Object obj : data){
            if(obj != null){
                sb.append(obj.toString()).append(",");
            }else {
//                sb.append("null,");
                continue;
            }
        }

        return sb.toString();
    }
}
