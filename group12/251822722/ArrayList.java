import java.util.Arrays;

public class ArrayList implements List {

    private int size = 0;

    private int index =0;

    private Object[] elementData = new Object[100];

    public void add(Object o) {
        elementData[size] = o;
        size = size+1;

    }

    public void add(int index, Object o) {

        Object[] elementDataNew =null;
        if(size<elementData.length+1){
             elementDataNew = new Object[elementData.length];
        }else {
             elementDataNew = new Object[elementData.length+1];
        }

        for(int i =0;i<index;i++){
            elementDataNew[i]=elementData[i];
        }

        elementDataNew[index]=o;

        for(int j= index+1;j<elementData.length;j++){
            elementDataNew[j]=elementData[j];
        }
        elementData = elementDataNew;
        size=size+1;

        return;

    }

    public Object get(int index) {
        return elementData[index];
    }

    public Object remove(int index) {
        Object[]  elementDataNew = new Object[elementData.length+1];

        for(int i =0;i<index;i++){
            elementDataNew[i]=elementData[i];
        }

        for(int j= index+1;j<elementData.length;j++){
            elementDataNew[j-1]=elementData[j];
        }

        elementData = elementDataNew;
        size=size-1;
         return this;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "size=" + size +
                ", index=" + index +
                ", elementData=" + Arrays.toString(elementData) +
                '}';
    }

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2,2);
        arrayList.remove(0);

        arrayList.toString();
    }
}
