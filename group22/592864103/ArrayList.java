import java.util.Arrays;
//import java.util.Objects;

public class ArrayList implements List {

	//private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		if (o == null) {
			System.out.println("传入对象不能为空！");
			return;
		} else {
			if (size() == elementData.length) {
				Object[] elementData2 = Arrays.copyOf(elementData, ((int) (elementData.length * 1.2)));
				elementData2[size()] = o;
				elementData = elementData2;
			} else {
				elementData[size()] = o;
			}
		}
	}

	public void add(int index, Object o) {
		if (o == null) {
			System.out.println("传入对象不能为空！");
			return;
		}
		if ((index > size())) {
			System.out.println("超出数组长度！");
			return;
		} else {
			if (size() == elementData.length) {
				Object[] elementData2 = Arrays.copyOf(elementData, ((int) (elementData.length * 1.2)));
				rightShift(elementData2, index);
				elementData2[index] = o;
				elementData = elementData2;
				return;
			} else {
				rightShift(elementData, index);
				elementData[size()] = o;
				return;
			}
		}
	}

	public Object get(int index) {
		if ((index > size() - 1)) {
			System.out.println("超出数组长度！");
			return null;
		} else {
			return elementData[index];
		}
	}

	public Object remove(int index) {
		if (index > size() - 1) {
			System.out.println("超出数组长度！");
			return null;
		}else{
			Object o = elementData[index];
			elementData[index]=null;
			leftShift(elementData,index);
			return o;
		}
	}

	public int size() {
		int n;
		for (n = 0; n < elementData.length; n++) {
			if (elementData[n] == null) {
				break;
			}
		}
		return n;
	}


	public Iterator iterator() {
		return new ArrayListIterator(this);
	}

	public class ArrayListIterator implements Iterator{
		ArrayList list = new ArrayList();
		int pos = 0;
		ArrayListIterator(ArrayList list){
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			if (pos<list.size()){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public Object next() {
			pos++;
			return list.get(pos);
		}
	}






	public void rightShift(Object[] o, int index) {
		for (int k = size(); k > index; k--) {
			o[k] = o[k - 1];
		}
	}

	public void leftShift(Object[] o, int index) {
		for (int k = index; k < size(); k++) {
			o[k] = o[k + 1];
		}
	}
}
