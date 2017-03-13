package datastructure;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
        if(this.size<100) {
            this.elementData[this.size] = o;
        }else{
            Object[] tmp = new Object[this.size+1];
            System.arraycopy(elementData,0,tmp,0,this.size);
            tmp[this.size] = o;
            this.elementData = tmp;
        }
        this.size++;
	}
	public void add(int index, Object o){
        if(this.size<100) {
            System.arraycopy(this.elementData,index,this.elementData,index+1,this.size-index);
            this.elementData[index] = o;
        }else{
            Object[] tmp = new Object[this.size+1];
            System.arraycopy(this.elementData,0,tmp,0,this.size);
            System.arraycopy(tmp,index,tmp,index+1,this.size-index);
            tmp[index] = o;
            this.elementData = tmp;
        }
        this.size++;
	}
	
	public Object get(int index){
        if(index >= this.size || index < 0){
            return null;
        }else {
            return this.elementData[index];
        }
	}
	
	public Object remove(int index){
        if(index >= this.size){
            System.out.println("out of index");
            return null;
        }else {
            Object o = this.elementData[index];
            System.arraycopy(this.elementData,index+1,this.elementData,index,this.size-index-1);
            this.elementData[this.size-1]=null;
            this.size--;
            return o;
        }
	}
	
	public int size(){
        return this.size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
