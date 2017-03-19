package com.github.dengliotng.coding2017.basic;

public class ArrayList implements List {

	private int size = 0;
    private static final int maxLength = 100;

    private Object[] elementData = new Object[maxLength];

	public void add(Object o){
        if (size > maxLength) {
            return;
        }
        elementData[size++] = o;
	}

	public void add(int index, Object o){
        if (size > maxLength) {
            return;
        }
        if (elementData[index] == null) {
            elementData[index] = o;
            ++size;
            return;
        } else {
            int emptyIndex = index + 1;
            boolean hasEmpty = false;
            for (int i = index; i < maxLength; i++) {
                if (elementData[i] == null) {
                    hasEmpty = true;
                    emptyIndex = i;
                }
            }
            if (!hasEmpty) {
                return;
            }
            //shift
            for (int i = emptyIndex; i > index; --i) {
                elementData[i] = elementData[i-1];
            }
            elementData[index] = o;
            ++size;
        }
	}

	public Object get(int index){
        if (index > maxLength || index < 0) {
            return null;
        }
        return elementData[index];
	}

	public Object remove(int index){
        if (index > maxLength || index < 0) {
            return null;
        }
        Object o = elementData[index];
        for (int i = index; i < size - 1; ++i) {
            elementData[i] = elementData[i+1];
        }
        --size;
        return  o;
	}

	public int size(){
        return size;
	}

	public Iterator iterator() {
        return new Iterator() {
            private int index=0;
            @Override
            public boolean hasNext() {
                if (index < size) {
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    return elementData[index++];
                }
                return null;
            }
        };
	}



}
