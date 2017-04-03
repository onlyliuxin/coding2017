package list;

/**
 * @author jiaxun
 */
public class Stack {

    private ArrayList elementData = new ArrayList();

    public void push(Object object) {
        elementData.add(object);
    }

    public Object pop() {
        return elementData.remove(elementData.size() - 1);
    }

    public Object peek() {
        return elementData.get(elementData.size() - 1);
    }

    public boolean isEmpty() {
        return elementData.size() == 0;
    }

    public int size() {
        return elementData.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (size() > 0) {
            for (int i = 0, len = size(); i < len; i++) {
                stringBuilder.append("[data is ").append(elementData.get(i)).append("]");
            }
        }
        return stringBuilder.toString();
    }
}
