package com.github.eulerlcs.jmr.challenge.camel;

import org.apache.camel.builder.RouteBuilder;

public class HelloRoute1 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:test-hello").to("log:test-log");
	}
}
