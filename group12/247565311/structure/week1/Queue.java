package structure.week1;
public class Queue<E> {
     private LinkedList<E> data = new LinkedList<E>();
     public void enQueue(E arg0){
          data.add(data.size(),arg0);
     }
     public E deQueue(){
         E res = data.get(0);
         data.remove(0);
         return res;
     }
     public int size(){
         return data.size();
     }
     public boolean isEmpty(){
         return data.isEmpty();
     }
}
