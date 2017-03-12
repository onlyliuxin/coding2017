package 基本数据结构;

public class MyLinkedList<E> implements MyList<E>
{
    private Node<E> head, tail;
    private int size;

    public MyLinkedList() {}

    public MyLinkedList(E[] objects)
    {
        for (int i = 0; i < objects.length; i++)
        {
            add(objects[i]);
//            size++;
        }
    }

    public E getFirst()
    {
        if (size == 0)
            return null;
        else
            return head.element;
    }

    public E getLast()
    {
        if (size == 0)
            return null;
        else
            return tail.element;
    }

    public void addFirst(E e)
    {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;

        if (tail == null)
            tail = head;
    }

    public void addLast(E e)
    {
        Node<E> newNode = new Node<>(e);

        if (tail == null)
            head = tail = newNode;
        else
        {
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    public void add(E e)
    {
        add(size, e);
    }

    @Override
    public void add(int index, E e)
    {
        if (index == 0)
        {
            addFirst(e);
        } else if (index >= size)
        {
            addLast(e);
        } else
        {
            Node<E> current = head;
            for (int i = 1; i < index; i++)
            {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }
    }

    @Override
    public E get(int index)
    {
        if (index < 0 || index > size - 1)
            return null;

        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;

        return current.element;
    }

    @Override
    public E remove(int index)
    {
        if (index < 0 || index >= size)
            return null;
        else if (index == 0)
        {
            E e = removeFirst();
            return e;
        } else if (index == size - 1)
            return removeLast();
        else
        {
            Node<E> previous = head;

            for (int i = 1; i < index; i++)
            {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    public E removeFirst()
    {
        if (size == 0)
            return null;
        else
        {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null)
                tail = null;
            return temp.element;
        }
    }

    public E removeLast()
    {
        if (size == 0)
            return null;
        else if (size == 1)
        {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else
        {
            Node<E> current = head;

            for (int i = 0; i < size - 2; i++)
                current = current.next;

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {
        size = 0;
        head = tail = null;
    }

    @Override
    public int indexOf(E e)
    {
        Node<E> current = head;
        for (int i = 0; i < size; i++)
        {
            if (current.element.equals(e))
                return i;
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty()
    {
        if (size != 0)
            return false;
        else
            return true;
    }

    @Override
    public E set(int index, E e)
    {
        if (index < 0 || index > size - 1)
            return null;

        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;

        E temp = current.element;
        current.element = e;

        return temp;
    }

    @Override
    public boolean contains(E e)
    {
        Node<E> current = head;
        for (int i = 0; i < size; i++)
        {
            if (current.element.equals(e))
                return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("[");

        if (size == 0)
            return null;
        else
        {
            Node<E> current = head;
            for (int i = 0; i < size; i++)
            {
                try
                {
                    result.append(current.element);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                current = current.next;
                if (current != null)
                {
                    result.append(", ");
                } else
                {
                    result.append("]");
                }
            }
            return result.toString();
        }
    }

    public static class Node<E>
    {
        E element;
        Node<E> next;

        public Node(E e)
        {
            this.element = e;
        }
    }
}