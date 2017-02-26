package week1;
public class Deque<E> {
     private LinkedList<E> data = new LinkedList<E>();
     private int size = 0;
     
     
     public Deque(){
         
     }
     public Deque(int arg0){
         data = new LinkedList<E>(arg0);
     }
     public boolean push(E arg0){
         data.add(arg0);
         size += 1;
         return true;
     }
     public E pop(){
         size -= 1;
         E res = data.get(size);
         data.remove(size);
         return res;
     }
     public E peek(){
         return data.get(size-1);
     }
     public int size(){
         return this.size;
     }
     public boolean isEmpty(){
         return this.size==0;
     }
     
}
