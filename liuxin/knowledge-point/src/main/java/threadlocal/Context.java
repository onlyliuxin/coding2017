package threadlocal;
import java.util.HashMap;
import java.util.Map;

public class Context {
	
	private static final ThreadLocal<String> txThreadLocal 
		= new ThreadLocal<String>();
	
	public static void setTransactionID(String txID) {		
		txThreadLocal.set(txID); 
	
	}
	
	public static String getTransactionId() {
		return txThreadLocal.get();
	}	

}

