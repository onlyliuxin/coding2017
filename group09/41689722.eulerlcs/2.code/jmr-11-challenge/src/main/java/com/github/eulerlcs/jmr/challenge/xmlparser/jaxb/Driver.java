package com.github.eulerlcs.jmr.challenge.xmlparser.jaxb;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
	private final static Logger log = LoggerFactory.getLogger(Driver.class);

	public static void main(String[] args) {
		File xml = new File("data//xmlparser", "hello.xml");
		Hello hello = JaxbParser.loadAppConfig(xml, Hello.class);
		log.debug("hello.value=[{}]", hello.getValue());

		xml = new File("data//xmlparser", "app-config.xml");
		AppConfig appConfig = JaxbParser.loadAppConfig(xml, AppConfig.class);
		log.debug("process-args.InputPath=[{}] ", appConfig.getInputPath());
	}
}
