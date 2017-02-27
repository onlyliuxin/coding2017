package SinglyLinkedList2;
/**
 * Created by Honoka on 2/16/2017.
 */
public class LinkedList1 {
    private class Node
    {
        String value;
        Node next;

        Node(String val, Node n)
        {
            value = val;
            next = n;
        }
        Node(String val)
        {
            //Call the other(daddy(or sister(whatever))) constructor.
            this(val, null);
        }
    }

    private Node first; // head
    private Node last;  //the last element in list

    public LinkedList1()
    {
        first = null;
        last = null;
    }

    /**This method checks to see
     * if the list is empty
     * @return true if list is empty
     */
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * size method returns the length of the list
     * @return The number of the elements in the list
     */
    public int size()
    {
        int counter = 0;
        Node p = first;
        while (p != null)
        {
            counter ++;
            p = p.next;
        }
        return counter;
    }

    /**
     * add method add an element to the end of the list
     * @param element the value to add
     */
    public void add(String element)
    {
        if (isEmpty())
        {
            //Obviously, add the element to the first position in the list
            first = new Node(element);
            last = first;
        }
        else
        {
            //add to the end of existing list
            last.next = new Node(element);
            last = last.next;
        }
    }

    /**
     * add method, or you might call it insert method since it can
     * add element to a specific position
     * @param index The position at which to add the element
     * @param element you should know what is this
     */
    public void add (int index, String element)
    {
        if (index < 0 || index > size())
        {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        //index is at least 0
        if(index == 0)
        {
            //new element add to the head
            first = new Node(element, first);
            if (last == null)
            {
                last = first;
            }
            return;
        }
        //set a reference predecessor to point to the node that
        //will be the predecessor of the new node
        Node predecessor = first;
        for (int k = 1; k <= index - 1; k++)
        {
            predecessor = predecessor.next;
        }
        //Splice in a node containing the new element
        predecessor.next = new Node(element, predecessor.next);

        //if there is a new last element
        if(predecessor.next.next == null)
            last = predecessor.next;
    }

    /**
     * toString method, like print method, hopefully it will display the contents of the list
     * @return say something I'm giving up on you(
     */
    public String toString()
    {
        StringBuffer strBuilder = new StringBuffer();
        //Use p to walk down the list
        Node p = first;
        while (p != null)
        {
            strBuilder.append(p.value + "\n");
            p = p.next;
        }
        return strBuilder.toString();
    }

    /**
     * remove method removes the element with the position you want
     * @param index the position of the element that you want to remove
     * @return the removed element
     */
    public String remove (int index)
    {
        /* Index out of bounds */
        if (index < 0 || index >= size())
        {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }
        String element = null;
        if(index == 0)
        {
            //Removal of first item in the list
            element = first.value;
            first = first.next;
            if (first == null)
            {
                last = null;
            }
        }
        else
        {
            /* find the predecessor of the element to be removed */
            Node predecessor = first;

            /* Move predecessor forward index - 1 times */
            for (int k = 1; k <= index - 1; k++)
            {
                predecessor = predecessor.next;
                /* Store the value to return */
                element = predecessor.next.value;
                /* Route link around the node to be removed */
                predecessor.next = predecessor.next.next;
                /* Check if predecessor is now last */
                if(predecessor.next == null)
                {
                    last = predecessor;
                }
            }
        }
        return element;
    }

    /**
     * The remove method removes an element
     * @param element the element to remove
     * @return true if the remove succeeded
     */
    public boolean remove(String element)
    {
        if (isEmpty())
        {
            return false;
        }

        if (element.equals(first.value))
        {
            //Removal of first element in the list
            first = first.next;
            if(first == null)
            {
                last = null;
            }
            return true;
        }

        /* Find the predecessor of the element to remove */
        Node predecessor = first;
        while (predecessor.next != null &&
                !predecessor.next.value.equals(element))
        {
            predecessor = predecessor.next;
        }
        /* predecessor.next == null OR predecessor.next.value is element */
        if(predecessor.next == null)
        {
            return false;
        }
        /* predecessor.next.value is element */
        predecessor.next = predecessor.next.next;

        /* check if predecessor is now last */
        if (predecessor.next == null)
        {
            last = predecessor;
        }
        return true;
    }

    public static void main (String [] args)
    {
        LinkedList1 testList = new LinkedList1();
        testList.add("Apple");
        testList.add("Banana");
        testList.add(0,"Blueberry");
        testList.add(2,"Cherry");
        testList.add(4,"Peach");
        System.out.println("The list has : ");
        System.out.println(testList);
        testList.remove("Cherry");
        testList.remove(2);
        System.out.println("The list has : ");
        System.out.println(testList);
    }
}
