package structure.week5;
import java.util.HashSet;

import structure.week1.ArrayList;
public class LRUPageFrame {
	int size;
	Node head = new Node(0);
	HashSet<Integer> lib = new HashSet<Integer>();
	class Node{
		int val;
		Node next;
		public Node(int _val){
			val = _val;
			next = null;
		}
	}
    public LRUPageFrame(int _size){
    	if(_size>0) {
    		this.size = _size;
    	}
    }
    public int[] getAll(){
    	int length = lib.size(),index = 0;
    	int []res = new int[length];
    	Node p = head.next;
    	while(p!=null){
    		res[index] = p.val;
    		index += 1;
    		p = p.next;
    	}
    	return res;
    }
    public void add(int e){
    	int index = 0;
    	if(lib.contains(e)){
    		Node p = head;
            while(p.next!= null){
                if(p.next.val == e){
                    Node newn = p.next;
                    p.next = newn.next;
                    newn.next = head.next;
                    head.next = newn;
                    break;
                }
                p = p.next;
            }
    	}else{
    		if(lib.size() == size){
    			lib.add(e);
    			Node newn = new Node(e);
    			newn.next = head.next;
    			head.next = newn;
                Node p = head;
                while(p.next.next != null)
                    p = p.next;
                Node deln = p.next;
                lib.remove(deln.val);
                p.next = null;
    		}else{
                Node newn = new Node(e);
                newn.next = head.next;
                head.next = newn;
                lib.add(e);
            }
    	}
    }
}
