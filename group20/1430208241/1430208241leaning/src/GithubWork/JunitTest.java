package GithubWork;

import org.junit.Test;

public class JunitTest {
     @Test
     public void ArrayList(){
       ArrayList a=new ArrayList();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.get(2);
       
        System.out.println(a);
        
     
     }
     @Test
     public void Queue(){
    	 Queue q=new Queue(4);
    	 q.enQueue(1);
    	 q.enQueue(2);
    	 q.enQueue(3);
    	 q.enQueue(4);
    	 
    	 while(!q.isEmpty()){
    		 int i=(int) q.deQueue();
    		 System.out.println(i);
    	 }
    	 System.out.println(q.size());
     }
     @Test
     public void LinkedList(){
    	 LinkedList ls=new LinkedList();
    	 ls.add(1);
    	 ls.add(7);
    	 ls.add(3);
    	 ls.add(4, 5);
    	 ls.get(2);
    	 ls.addFirst(0);
    	 
    	 ls.remove(3);
    	System.out.println(ls);
    	 
     }
}
