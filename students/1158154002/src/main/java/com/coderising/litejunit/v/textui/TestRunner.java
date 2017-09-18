package com.coderising.litejunit.v.textui;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.Iterator;

import com.coderising.litejunit.v.AssertionFailedError;
import com.coderising.litejunit.v.Test;
import com.coderising.litejunit.v.TestFailure;
import com.coderising.litejunit.v.TestResult;
import com.coderising.litejunit.v.runner.BaseTestRunner;

public class TestRunner extends BaseTestRunner {
	PrintStream writer=System.out;
	int column=0;
	
	public static void main(String[] args) {
		TestRunner testRunner = new TestRunner();
		try {
			TestResult result=	testRunner.start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private TestResult start(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("参数错误！");
		}
		String testCase = args[0];
		Test test = getTest(testCase);
		return this.doRun(test);		
	}
	
	private TestResult doRun(Test suite){
		TestResult result=new TestResult();
		result.addListener(this);
		long startTime= System.currentTimeMillis();
		suite.run(result);
		long endTime= System.currentTimeMillis();
		long runTime= endTime-startTime;
		writer().println();
		writer().println("Time: "+elapsedTimeAsString(runTime));
		print(result);

		writer().println();
		return result;
	}

	private void print(TestResult result) {
	    printErrors(result);
	    printFailures(result);
	    printHeader(result);
		
	}
	
	/**
	 * Prints the errors to the standard output
	 */
	public void printErrors(TestResult result) {
	    if (result.errorCount() != 0) {
	        if (result.errorCount() == 1)
		        writer().println("There was "+result.errorCount()+" error:");
	        else
		        writer().println("There were "+result.errorCount()+" errors:");

			int i= 1;
			for (Iterator<TestFailure> e= result.errors(); e.hasNext(); i++) {
			    TestFailure failure= e.next();
				writer().println(i+") "+failure.failedTest());
				writer().print(getFilteredTrace(failure.thrownException()));
		    }
		}
	}
	private static String getFilteredTrace(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer= stringWriter.getBuffer();
		String trace= buffer.toString();
		return trace;
	}

	/**
	 * Prints failures to the standard output
	 */
	public void printFailures(TestResult result) {
		if (result.failureCount() != 0) {
			if (result.failureCount() == 1)
				writer().println("There was " + result.failureCount() + " failure:");
			else
				writer().println("There were " + result.failureCount() + " failures:");
			int i = 1;
			for (Iterator<TestFailure> e= result.failures(); e.hasNext(); i++) {
				TestFailure failure= (TestFailure) e.next();
				writer().print(i + ") " + failure.failedTest());
				Throwable t= failure.thrownException();
				writer().print(getFilteredTrace(failure.thrownException()));
			}
		}
	}
	/**
	 * Prints the header of the report
	 */
	public void printHeader(TestResult result) {
		if (result.wasSuccessful()) {
			writer().println();
			writer().print("OK");
			writer().println (" (" + result.runCount() + " tests)");

		} else {
			writer().println();
			writer().println("FAILURES!!!");
			writer().println("Tests run: "+result.runCount()+ 
				         ",  Failures: "+result.failureCount()+
				         ",  Errors: "+result.errorCount());
		}
	}
	

	private String elapsedTimeAsString(long runTime) {
		return NumberFormat.getInstance().format((double)runTime/1000);
	}

	@Override
	public void addError(Test test, Throwable t) {
		writer().print("E");
	}

	@Override
	public void addFailure(Test test, AssertionFailedError t) {
		writer().print("F");		
	}

	@Override
	public void endTest(Test test) {
		
	}

	@Override
	public void startTest(Test test) {
		writer().print(".");
		if (column++ >= 40) {
			writer().println();
			column= 0;
		}
	}
	
	private PrintStream writer(){
		return writer;
	}

}
