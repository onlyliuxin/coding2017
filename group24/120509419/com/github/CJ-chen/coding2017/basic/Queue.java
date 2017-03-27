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
public class Queue {
    
    private final LinkedList innerList;
    
    public Queue(){
        innerList = new LinkedList();
    }
    
    public void enQueue(Object o) {
        innerList.addLast(o);
    }

    public Object deQueue() {
        return innerList.removeFirst();
    }

    public boolean isEmpty() {
        return innerList.size()==0;
    }

    public int size() {
        return innerList.size();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayList: [");
        for (int i = 0; i < innerList.size(); i++) {
            sb.append(innerList.get(i)).append(", ");
        }
//        System.err.println(size);
        sb.delete(sb.length()-2,sb.length()).append("]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Queue newQ = new Queue();
        for(int i=0;i<10;i++){
            newQ.enQueue(i);
        }
        System.err.println(newQ);
        newQ.deQueue();
        System.err.println(newQ);
    }
    
    
}
