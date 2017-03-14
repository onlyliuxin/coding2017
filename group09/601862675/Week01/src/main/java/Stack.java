public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o){
        elementData.add(o);
    }

    public Object pop(){
        if (elementData.size() == 0) {
            throw new UnsupportedOperationException();
        }
        return elementData.remove(elementData.size()-1);
    }

    public Object peek(){
        return elementData.get(elementData.size()-1);
    }

    public boolean isEmpty(){
        return elementData.size() == 0;
    }

    public int size(){
        return elementData.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Stack: [ ");
        Iterator iter = elementData.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            builder.append(" ");
        }
        builder.append("]");
        return builder.toString();
    }
}