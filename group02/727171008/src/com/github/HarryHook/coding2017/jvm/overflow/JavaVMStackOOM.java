package com.github.HarryHook.coding2017.jvm.overflow;

public class JavaVMStackOOM {
    private void dontStop() {
	while(true) {
	    
	}
    }
    
    public void stackLeakByThread() {
	while(true) {
	    Thread thread = new Thread(new Runnable() {
		@Override
		public void run() {
		    dontStop();
		}
	    });
	    thread.start();
	}
    }
    public static void main(String[] args) {
	
	JavaVMStackOOM oom = new JavaVMStackOOM();
	oom.stackLeakByThread();
    }

}
