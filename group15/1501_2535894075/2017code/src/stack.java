package firstweek;

import java.util.Arrays;

public class stack {
    //Define capacity constant:CAPACITY
    private static final int CAPACITY = 1024;
    //Define capacity
    private static int capacity;
    //Define the top position of stack 
    //top = -1 meaning that the stack empty
    private static int top = -1;
    //Basic Object class array
    Object[] array;
    //Initialize the capacity of stack
    public stack() {
        this.capacity = CAPACITY;
        array = new Object[capacity];
    }

    //Get the size of stack
    public int getSize(){
        if(isEmpty()){
            return 0;
        }else{
            return top + 1;
        }
    }

    //Get whether stack is empty
    public boolean isEmpty(){
        return (top < 0);
    }

    //Get the top element of stack
    public Object top() {

        if(isEmpty()){
            System.out.println("Stack is empty");
        }
        return array[top];

    }

    //Push element to stack
    public void push(Object element) throws Exception{
           if(getSize()== CAPACITY){
               throw new Exception("Stack is full");
           }
           array[++ top] = element;
    }

    //Pop element from stack
    public Object pop() {
        if(isEmpty()){
            System.out.println("Stack is empty");
        }
        return array[top --];
    }


    public String getAllElements() {
        String[] arr = new String[top + 1];
        if(!isEmpty()){
            for(int i = 0;i < getSize();i ++){
                arr[i] = (String)array[i];
            }
        }
        return Arrays.toString(arr);
    }
}
