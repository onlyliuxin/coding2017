package com.github.eulerlcs.jmr.challenge.camel;

import org.apache.camel.builder.RouteBuilder;

public class HelloRoute6 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class).handled(true).setBody().constant("なにかエラーが発生").to("stream:out");

		from("stream:in?promptMessage=Enter : ").process(new HelloProcessor2()).process(new HelloProcessor2())
				.to("stream:out");
	}
}
