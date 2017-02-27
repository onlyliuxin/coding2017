package coding.basic;

/**
 * @Author shane
 * @Time 2017/2/26 16:55
 * @Email stevenchenguang@gmail.com
 * @Desc Own Stack
 */
public class Stack {

    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        return elementData.remove(elementData.size() - 1);
    }

    public Object peek() {
        return elementData.get(elementData.size() - 1);
    }

    public boolean isEmpty() {
        return elementData.isEmpty();
    }

    public int size() {
        return elementData.size();
    }

    @Override
    public String toString() {
        return elementData.toString();
    }
}
