package com.github.eulerlcs.jmr.litestruts.degister;

import org.apache.commons.digester.Digester;

public class StrutsDigester {
	public static Digester newInstance() {
		Digester d = new Digester();
		d.addObjectCreate("struts", StrutsConfig.class);
		d.addSetProperties("struts");
		d.addRuleSet(new StrutsActionRulerSet("struts/"));
		return d;
	}
}
