package com.github.eulerlcs.jmr.challenge.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class HelloProcessor2 implements Processor {
	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);

		if ("".equals(body)) {
			throw new Exception("empty value.");
		}

		body = "Hello " + body;
		exchange.getIn().setBody(body);
	}
}
