package com.github.eulerlcs.jmr.challenge.camel;

import org.apache.camel.builder.RouteBuilder;

public class HelloRoute2 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("stream:in?promptMessage=Enter : ").to("stream:out");
	}
}
