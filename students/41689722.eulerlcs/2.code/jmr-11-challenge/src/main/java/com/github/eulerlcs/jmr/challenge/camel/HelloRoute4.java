package com.github.eulerlcs.jmr.challenge.camel;

import org.apache.camel.builder.RouteBuilder;

public class HelloRoute4 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("stream:in?promptMessage=Enter : ").process(new HelloProcessor1())
				.bean(HelloConv.class, "addHello(${body})").to("stream:out");
	}
}
