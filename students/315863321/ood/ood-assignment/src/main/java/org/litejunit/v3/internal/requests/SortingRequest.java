package org.litejunit.v3.internal.requests;


import org.litejunit.v3.runner.Description;
import org.litejunit.v3.runner.Request;
import org.litejunit.v3.runner.Runner;
import org.litejunit.v3.runner.manipulation.Sorter;

import java.util.Comparator;

public class SortingRequest extends Request {
	private final Request fRequest;
	private final Comparator<Description> fComparator;

	public SortingRequest(Request request, Comparator<Description> comparator) {
		fRequest= request;
		fComparator= comparator;
	}

	@Override
	public Runner getRunner() {
		Runner runner= fRequest.getRunner();
		new Sorter(fComparator).apply(runner);
		return runner;
	}
}
