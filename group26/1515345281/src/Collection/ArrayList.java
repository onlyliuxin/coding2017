package Collection;

public class ArrayList implements List {
    private int size = 0;

    private Object[] elementData = new Object[100];

    public void add(Object o) {

        if (o == null)
            throw new RuntimeException("插入的值不能为null");
        if ((size + 1) >= 100) {
            throw new RuntimeException("数组越界异常");
        }
        elementData[size] = o;
        size++;
    }

    public void add(int index, Object o) {

        for (int i = index; i < size; i++) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = o;
    }

    public Object get(int index) {

        if (index < 0 || index >= size)
            throw new RuntimeException("索引异常");
        return elementData[index];
    }

    public Object remove(int index) {

        if (index < 0 || index >= size)
            throw new RuntimeException("索引异常");
        Object temp = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        return temp;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new Itr();
    }

    private class Itr implements Iterator {

        private int cursor = 0;

        @Override
        public boolean hasNext() {

            return cursor != size;
        }

        @Override
        public Object next() {
            int i = cursor;
            cursor = i + 1;
            return elementData[i];
        }

    }

}
