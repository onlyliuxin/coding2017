package com.eulerlcs.jmr.xmlparser.digester.core;

import org.apache.commons.digester.Digester;

import com.eulerlcs.jmr.xmlparser.digester.entity.Hello;

public class HelloDigester {
	public static Digester newInstance() {
		Digester d = new Digester();

		d.addObjectCreate("files", Hello.class);
		d.addSetProperties("files");
		d.addRuleSet(new FileRulerSet("files/"));

		return d;
	}
}
