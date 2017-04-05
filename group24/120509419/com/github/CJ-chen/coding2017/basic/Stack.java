/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass;

/**
 *
 * @author CJ
 */
public class Stack {

    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        return elementData.remove(elementData.size()-1);
    }

    public Object peek() {
        return elementData.get(elementData.size()-1);
    }

    public boolean isEmpty() {
        return elementData.size()==0;
    }

    public int size() {
        return elementData.size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayList: [");
        for (int i = 0; i < elementData.size(); i++) {
            sb.append(elementData.get(i)).append(", ");
        }
//        System.err.println(size);
        sb.delete(sb.length()-2,sb.length()).append("]");
        return sb.toString();
    }
    public static void main(String[] args) {
        Stack newS = new Stack();
        for(int i=0;i<10;i++){
            newS.push(i);
        }
        System.err.println("Test push()");
        System.err.println(newS);
        newS.pop();
        System.err.println("Test pop()");
        System.err.println(newS);
        System.err.println("Test peek()");
        System.err.println(newS.peek());
        System.err.println(newS);
        
    }
    
}
