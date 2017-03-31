package firstday;
import firstday.ArrayListt;
public class Stack {
     private ArrayListt a=new ArrayListt();
     public Object peek()
     {
    	 return a.get(a.size()-1);
     }
     public Object pop()
     {
    	 return a.remove(a.size()-1);
     }
     public void push(Object o)
     {
    	 a.add(o);
     }
     public boolean isEmpty()
     {
    	 if(a.size()==0)
    	 {
    		 return true;
    	 }
    	 else
    	 {
    		 return false;
    	 }
     }
     public int size()
     {
    	 return a.size();
     }
     public static void main(String[] args)
     {
    	 Stack s=new Stack();
    	 for(int i=0;i<4;i++)
    	 {
    		 s.push(i);
    	 }
    	 s.pop();
    	 System.out.println(s.peek());
    	 System.out.println(s.size());
     }
}
