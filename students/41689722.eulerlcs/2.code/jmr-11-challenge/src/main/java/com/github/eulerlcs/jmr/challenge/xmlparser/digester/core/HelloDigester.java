package com.github.eulerlcs.jmr.challenge.xmlparser.digester.core;

import org.apache.commons.digester.Digester;

import com.github.eulerlcs.jmr.challenge.xmlparser.digester.entity.Hello;

public class HelloDigester {
	public static Digester newInstance() {
		Digester d = new Digester();

		d.addObjectCreate("files", Hello.class);
		d.addSetProperties("files");
		d.addRuleSet(new HelloFileRulerSet("files/"));

		return d;
	}
}
