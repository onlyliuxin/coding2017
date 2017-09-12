/**
 * 
 */
package org.litejunit.v3.internal.requests;


import org.litejunit.v3.runner.Request;
import org.litejunit.v3.runner.Runner;
import org.litejunit.v3.runner.manipulation.Filter;
import org.litejunit.v3.runner.manipulation.NoTestsRemainException;

public final class FilterRequest extends Request {
	private final Request fRequest;
	private final Filter fFilter;

	public FilterRequest(Request classRequest, Filter filter) {
		fRequest= classRequest;
		fFilter= filter;
	}

	@Override
	public Runner getRunner() {
		try {
			Runner runner= fRequest.getRunner();
			fFilter.apply(runner);
			return runner;
		} catch (NoTestsRemainException e) {
			return Request.errorReport(Filter.class, new Exception(String
					.format("No tests found matching %s from %s", fFilter
							.describe(), fRequest.toString()))).getRunner();
		}
	}
}