package cc.gzhlaker;

public class Queue extends ArrayList {
	ArrayList top;
	int size = 0;
	public Queue(){
		top = new ArrayList();
	}
	
	public Queue(int length){
		top = new ArrayList(length);
	}
	
	public void pull(int data){
		if(top.isFill(size, top.now.length)){
			top.increaseLength(this.now);
		}
		top.add(data);
		
	}
	public void remove(){
		top.removeFirst();
	}

}
