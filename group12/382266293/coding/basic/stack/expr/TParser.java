package stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class TParser {

	Queue<Integer> intQ;
	Queue<String> signQ;
	static final List<String> signs = new ArrayList<String>();
	{
		signs.add("+");
		signs.add("-");
		signs.add("*");
		signs.add("/");
	}

	public TParser() {
		intQ = new LinkedBlockingDeque<Integer>();
		signQ = new LinkedBlockingDeque<String>();
	}

	public void parse(String expr) {

		String[] tokens = expr.split("");
		String number = "";

		for (int i = 0; i < tokens.length; i++) {

			String c = tokens[i];

			if (isSign(c)) {
				
				signQ.add(c);
			
				int num = Integer.parseInt(number);
				intQ.add(num);
				number = "";
	
			} else {
				
				number += tokens[i];
				
			}

		}

		int num = Integer.parseInt(number);
		intQ.add(num);

		int intSize = intQ.size();
		if (intSize < 2 || intSize - signQ.size() > 1) {
			throw new RuntimeException("Invalid input IntQ: " + intQ + " signQ " + signQ);
		}

		intQ.add(0);

	}

	private boolean isSign(String c) {
		if (signs.contains(c)) {
			return true;
		}
		return false;
	}

	public int nextInt() {
		return intQ.poll();
	}

	public String nextSign() {
		return signQ.poll();
	}

	public boolean hasNextInt() {
		return !intQ.isEmpty();
	}

	public boolean hasNextSign() {
		return !signQ.isEmpty();
	}

}
