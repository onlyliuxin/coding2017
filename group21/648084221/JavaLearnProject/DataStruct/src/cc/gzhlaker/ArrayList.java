package cc.gzhlaker;

public class ArrayList implements List{
	//------------------------------
	//-
	//------------------------------
	public int size;
	MyData[] now;
	//------------------------------
	//-
	//------------------------------
	public ArrayList(){
		size = 0;
		now = new MyData[100];
	}
	public ArrayList(int length){
		size = 0;
		now = new MyData[length];
	}
	//------------------------------
	//-
	//------------------------------
	public boolean isFill(int size,int length){
		if(size>length){
			return true;
		}else if(size<=length){
			return false;
		}
		return false;
	}
	//------------------------------
	//-
	//------------------------------
	public MyData[] increaseLength(MyData[] now){
		MyData[] newarray = new MyData[now.length+10];
		System.arraycopy(now, 0, newarray, 0, now.length);
		return newarray;
	}
	
	@Override
	public void add(int data) {
		if(isFill(size+1,now.length)){
			now = increaseLength(now);
		}
		now[size].number = data;
		size ++;
	}
	
	@Override
	public void add(int data, int index) {
		if(isFill(size+1,now.length)){
			now = increaseLength(now);
		}
		for(int i=size+1;i>index;i--){
			now[i].number = now[i-1].number; 
		}
		now[index].number = data;
		size++;
		
		
	}
	
	@Override
	public void addFirst(int data) {
		if(isFill(size+1,now.length)){
			now = increaseLength(now);
		}
		for(int i=size+1;i>0;i--){
			now[i].number = now[i-1].number;
		}
		now[0].number = data;
		size++;
	}
	
	@Override
	public void remove(int index) {
		now[size].number = 0;
		size--;
	}
	
	@Override
	public void removeFirst() {
		now[0].number = 0;
		for(int i=0;i<=size;i++){
			now[i].number = now[i+1].number;
		}
		size--;
	}
	
	@Override
	public void removeEnd(){
		
	}
	
	@Override
	public void setData(int data,int index) {
		if(index>now.length){
			return;
		}
		now[index].number = data;
		
	}
	
	@Override
	public int getData(int index) {
		if(index>now.length){
			return -1;
		}
		int i = now[index].number;
		return i;
	}


	

}
