package firstday;

import firstday.LinkListt;
//import firstday.LinkListt.Node;

public class LinkListt {
  private static class Node
  {
	  private Object date=null;
	  private Node next=null;
	  public Node(Object o)
	  {
		  this.date=o;
	  }
  }
   public Node head=null;
   public void add(Object o)
   {
	   Node nde=new Node(o);
	   if(head == null)
	   {
		   head=nde;
	   }
	   else
	   {
		   Node order=head;
		   while(true)
		   {
			   if(order.next==null)
			   {
				   order.next=nde;
				   break;
			   }
			   order=order.next;
		   }
	   }
   }
   public void add(int index,Object o)
   {
	   int cnt=1;
	   Node n=head;
	   while(true)
	   {
		   if(cnt==index-1)
		   {
			   Node no=new Node(o);
			   no.next=n.next;
			   n.next=no;
			   break;
		   }
		   n=n.next;
		   cnt++;
	   }
   }
   public Object get(int index)
   {
	   int cnt=1;
	   Node n=head;
	   while(true)
	   {
		   if(cnt==index)
		   {
			   return n.date;
		   }
		   n=n.next;
		   cnt++;
	   }
   }
   public Object remove(int index)
   {
	   int cnt=1;
	   Node n=head;
	   Node front=null;
	   Object re=null;
	   while(true)
	   {
		   if(cnt==index-1)
		   {
			   front=n;
		   }
		   if(cnt==index)
		   {
			   front.next=n.next;
			   n.next=null;
			   re=n.date;
			   n=null;
			   return re;
		   }
		   cnt++;
		   n=n.next;
	   }
   }
   public int size()
   {
	   int cnt=1;
	   Node n=head;
	   while(true)
	   {
		   if(n.next==null)
		   {
			   return cnt;
		   }
		   cnt++;
		   n=n.next;
	   }
   }
   public void addFirst(Object o)
   {
	   Node n=new Node(o);
	   n.next=head;
	   head=n;
   }
   public Object removeFirst()
   {
	   Object re;
	   Node n=head;
	   head=n.next;
	   n.next=null;
	   re=n.date;
	   n=null;
	   return re;
   }
   public Object removeLast()
   {
	   Node n=head;
	   Node front=null;
	   Object re=null;
	   while(true)
	   {
		   if(n.next==null)
		   {
			   re=n.date;
			   front.next=null;
			   n=null;
			   return re;
		   }
		   front=n;
		   n=n.next;
	   }
   }
   public static void main(String[] args)
   {
	   LinkListt l=new LinkListt();
	   for(int i=0;i<5;i++)
	   {
		   l.add(i);
	   }
	   l.add(3,8);
	   for(int i=1;i<=6;i++)
	   {
		   System.out.println(l.get(i));
	   }
	   //System.out.println(l.size());
	   l.addFirst(99);
	   for(int i=1;i<=7;i++)
	   {
		   System.out.println(l.get(i));
	   }
	   l.removeFirst();
	   for(int i=1;i<=6;i++)
	   {
		   System.out.println(l.get(i));
	   }
	   System.out.println(l.size());
   }
}

