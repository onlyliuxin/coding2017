package weeks11.jvm;

import java.util.ArrayList;
import java.util.List;

public class Overflow {

	
	/**模拟java堆异常**/
	public void outOfMemoryWithHeap(){
		List<Integer> list=new ArrayList<Integer>();
		while(true){
			list.add(1);
		}
	}
	
	/**模拟虚拟机栈溢出**/
	public void stackOverflowError(){
		stackOverflowError();
	}
	
	/**模拟永久代溢出**/
	public void outOfMemoryWithPermGen(){
	
		//使用list 保持着常量池的引用，避免Full Gc回收常量池的行为
		List<String> list=new ArrayList<>();
		int i=0;
		
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
	
	
}
