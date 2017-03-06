public class Queue {

    private LinkedList llist = new LinkedList();

    public void enQueue(Object o){
        llist.add(o);
    }

    public Object deQueue(){
        if (llist.size() == 0) {
            throw new UnsupportedOperationException();
        }
        return llist.remove(0);
    }

    public boolean isEmpty(){
        return llist.size() == 0;
    }

    public int size(){
        return llist.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Queue: [ ");
        Iterator iter = llist.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            builder.append(" ");
        }
        builder.append("]");
        return builder.toString();
    }
}
