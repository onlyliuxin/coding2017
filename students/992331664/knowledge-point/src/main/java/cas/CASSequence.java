package cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASSequence{

	private AtomicInteger count = new AtomicInteger(0);

	public  int next(){
		while(true){
			int current = count.get();
			int next = current +1;
			if(count.compareAndSet(current, next)){
				return next;
			}
		}		
	}
}