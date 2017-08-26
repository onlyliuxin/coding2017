package org.litejunit.v3.runner;

import java.io.PrintStream;
import java.text.NumberFormat;

import org.litejunit.v3.notification.Failure;

public class ResultPrinter {
	
	private final PrintStream fWriter;
	
	public ResultPrinter(){
		fWriter = System.out;
	}
	
	public void print(Result result) {
		printHeader(result.getRunTime());
		printFailures(result);
		printFooter(result);
	}
	protected void printHeader(long runTime) {
		getWriter().println();
		getWriter().println("Time: " + elapsedTimeAsString(runTime));
	}

	protected void printFailures(Result result) {
		if (result.getFailureCount() == 0)
			return;
		if (result.getFailureCount() == 1)
			getWriter().println("There was " + result.getFailureCount() + " failure:");
		else
			getWriter().println("There were " + result.getFailureCount() + " failures:");
		int i= 1;
		for (Failure each : result.getFailures())
			printFailure(each, i++);
	}

	protected void printFailure(Failure failure, int count) {
		printFailureHeader(failure, count);
		printFailureTrace(failure);
	}

	protected void printFailureHeader(Failure failure, int count) {
		getWriter().println(count + ") " + failure.getTestHeader());
	}

	protected void printFailureTrace(Failure failure) {
		getWriter().print(failure.getTrace());
	}

	protected void printFooter(Result result) {
		if (result.wasSuccessful()) {
			getWriter().println();
			getWriter().print("OK");
			getWriter().println(" (" + result.getRunCount() + " test" + (result.getRunCount() == 1 ? "" : "s") + ")");

		} else {
			getWriter().println();
			getWriter().println("FAILURES!!!");
			getWriter().println("Tests run: " + result.getRunCount() + ",  Failures: " + result.getFailureCount());
		}
		getWriter().println();
	}
	protected String elapsedTimeAsString(long runTime) {
		return NumberFormat.getInstance().format((double) runTime / 1000);
	}
	private PrintStream getWriter() {
		return fWriter;
	}


}
