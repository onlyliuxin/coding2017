package com.coderising.dp.ResponsibilityChain;

public class StdoutLogger implements Logger {
	private int level = Logger.DEBUG;
	private Logger next;
	
	public StdoutLogger(int level) {
		this.level = level;
	}

	@Override
	public void message(String context, int level) {
		if (level >= this.level) {
			System.out.println(this.getClass().getName()+ " " + context);
		}
		if (this.next != null) {
			this.next.message(context, level);
		}
	}

	@Override
	public void setNext(Logger next) {
		this.next = next;
	}

}
