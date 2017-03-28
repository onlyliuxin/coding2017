package list;

/**
 * @author jiaxun
 */
public class ArrayList implements List {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(Object object) {
        ensureCapacity();
        elementData[size] = object;
        size++;
    }

    public void add(int index, Object object) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = object;
        size++;
    }

    public Object remove(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        Object object = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return object;
    }

    public int size() {
        return size;
    }

    public Object get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elementData[index];
    }

    public void set(int index, Object object) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = object;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator iterator() {
        return new ArrayListIterator(this);
    }

    private void ensureCapacity() {
        if (size + 1 > elementData.length) {
            ensureCapacity(elementData.length + 1);
        }
    }

    private void ensureCapacity(int length) {
        Object[] newElementData = new Object[length];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private class ArrayListIterator implements Iterator {

        private ArrayList arrayList;
        private int currentPosition = 0;

        private ArrayListIterator(ArrayList arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public Object next() {
            return elementData[currentPosition++];
        }

        @Override
        public Object remove() {
            return arrayList.remove(--currentPosition);
        }
    }

}
