package com.github.eulerlcs.jmr.challenge.camel;

import org.apache.camel.builder.RouteBuilder;

public class HelloRoute5 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("stream:in?promptMessage=Enter : ").routeId("HelloRoute20170705").process(new HelloProcessor2())
				.to("stream:out");
	}
}
