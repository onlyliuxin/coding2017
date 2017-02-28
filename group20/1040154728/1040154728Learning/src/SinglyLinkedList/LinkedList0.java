package SinglyLinkedList;

/**
 * Created by Honoka on 2/16/2017.
 */
public class LinkedList0 {
    //Node class represents a list node
    private class Node
    {
        String value;
        Node next;
        /**
         * Constructor
         * @param val The element to store in this node.
         * @param n The reference to the next node.
         */
        Node (String val, Node n)
        {
            value = val;
            next = n;
        }

        /**
         * Constructor
         * @param val The element to store in this node.
         */
        Node(String val)
        {
            value = val;
            next = null;
        }
    }
    //Reference to the first node in the list
    private Node first = null;
    /**
     * Constructor
     * Builds a linked list
     */
    public LinkedList0()
    {
        //test
        first = new Node("Apple");
        first.next = new Node("Peach");
        first.next.next = new Node("Kiwi");
        first = new Node("Blueberry",first);

        //Using an array to add elements into list
        String[] fruits = {"Banana", "Cherry"};
        for (String f : fruits)
        {
            first = new Node(f, first);
        }
    }
    /**
     * print method
     * traverses the list and prints all elements
     */
    public void print()
    {
        Node reference = first;
        while(reference != null)
        {
            System.out.println(reference.value + " ");
            reference = reference.next;
        }
    }

    //Main test method
    public static void main(String [] args)
    {
        LinkedList0 list = new LinkedList0();
        System.out.println("The elements inside this list are ");
        list.print();
    }
}
