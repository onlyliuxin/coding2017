package datastructure.basic;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData;

	public ArrayList() {
        elementData = new Object[100];
    }

	public ArrayList(int initCapacity) {
        elementData = new Object[initCapacity];
    }

	public void add(Object o) {
		autoGrow();
		elementData[size()] = o;
		size++;
	}

    public void add(int index, Object o) {
        autoGrow();
        System.arraycopy(elementData, index, elementData, index + 1, size() - index);
        elementData[index] = o;
        size++;
	}

	public Object get(int index) {
	    checkIndex(index);
		return elementData[index];
	}

	public Object remove(int index) {
	    checkIndex(index);
	    Object removed = elementData[index];
	    System.arraycopy(elementData, index + 1, elementData, index, size() - index - 1);
	    size--;
		return removed;
	}

	@Override
	public int indexOf(Object o) {
	    for (int i = 0; i < size(); ++i) {
			if (elementData[i] == null ? o == null : elementData[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterator iterator() {
		return new Iterator() {
		    int index = -1;
            @Override
            public boolean hasNext() {
                return index + 1 < size();
            }

            @Override
            public Object next() {
                index++;
                return elementData[index];
            }
        };
	}

    private void autoGrow() {
	    if (size >= elementData.length) {
	        Object[] newArray = new Object[nextCapacity()];
	        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
	        elementData = newArray;
        }
    }

    private int nextCapacity() {
        return elementData.length * 2;
    }

    private void checkIndex(int index) {
	    if (index >= size() || index < 0) {
	        throw new IndexOutOfBoundsException(indexOutOfBoundMessage(index));
        }
    }

    private String indexOutOfBoundMessage(int index) {
        return "index: " + index + ", size: " + size();
    }
}
