package queue;

/**
 * @author jiaxun
 */
public class Queue<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void enQueue(E data) {
        Node<E> prevLast = last;
        Node<E> node = new Node<>(data, null);
        last = node;
        if (isEmpty()) {
            first = last;
        } else {
            prevLast.next = node;
        }
        size++;
    }

    public E deQueue() {
        if (isEmpty()) return null;
        Node<E> result = first;
        first = first.next;
        size--;
        if (isEmpty()) last = null;
        return result.item;
    }

}
