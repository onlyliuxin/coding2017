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

    public int size() {
		return size;
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

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < 10; ++i) {
			list.add(i);
			list.add(10 - i);
		}
		System.out.println("------------------size");
		System.out.println("size: " + list.size());

		System.out.println("------------------for(int i)");
		for (int i = 0; i < list.size(); ++i) {
			System.out.print(list.get(i) + " ");
		}

		System.out.println("\n-----------------iterator");
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}

		System.out.println("\n-----------------add at index 0    100~104");
		for (int i = 100; i < 105; ++i) {
			list.add(0, i);
		}
		list.print();
		System.out.println("-----------------add at last    200~204");
		for (int i = 200; i < 205; ++i) {
			list.add(list.size(), i);
		}
		list.print();

		System.out.println("-----------------removeFirst x4");
		for (int i = 0; i < 4; ++i) {
			list.remove(0);
		}
		list.print();

		System.out.println("\n-----------------removeLast x4");
		for (int i = 0; i < 4; ++i) {
			list.remove(list.size() - 1);
		}
		list.print();
	}

	public void print() {
		Iterator iterator = iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println("\nsize: " + size());
	}
}
