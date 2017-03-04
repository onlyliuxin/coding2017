public class ArrayList implements List{

    private Object[] elements;

    private static final int INITIAL_SIZE = 16;

    public static final int MAX_LIST_SIZE = 48;

    private int size = 0;

    private int capacity = 0;

    public ArrayList() {
        elements = new Object[INITIAL_SIZE];
        capacity = INITIAL_SIZE;
    }

    public void add(int index, Object obj) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureSpace();
        if (index == size) {
            add(obj);
        } else {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = obj;
            size++;
        }
    }

    public void add(Object obj) {
        ensureSpace();
        elements[size++] = obj;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("List: [ ");
        for (int i = 0; i < size; ++ i) {
            builder.append(elements[i]).append(" ");
        }
        builder.append("]");
        return builder.toString();
    }

    private void ensureSpace() {
        if (size == capacity) {
            if (size == MAX_LIST_SIZE) {
                throw new IndexOutOfBoundsException();
            }
            int newCapacity = capacity*2 > MAX_LIST_SIZE ? MAX_LIST_SIZE : capacity*2;
            grow(newCapacity);
        }
    }

    private void grow(int newLength) {
        Object[] newElements = new Object[newLength];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
        capacity = newLength;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        return elements[index];
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        Object toRemove = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index -1);
        --size;
        return toRemove;
    }

    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator{

        private int pos = 0;

        public boolean hasNext() {
            return pos < size();
        }

        public Object next() {
            return elements[pos++];
        }
    }
}
