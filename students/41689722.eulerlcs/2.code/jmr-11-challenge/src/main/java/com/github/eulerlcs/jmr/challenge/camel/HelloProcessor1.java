package com.github.eulerlcs.jmr.challenge.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class HelloProcessor1 implements Processor {
	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		body = "Hello " + body;
		exchange.getIn().setBody(body);
	}
}
