package com.github.eulerlcs.jmr.challenge.xmlparser.digester.core;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

import com.github.eulerlcs.jmr.challenge.xmlparser.digester.entity.HelloFile;

final class HelloFileRulerSet extends RuleSetBase {
	protected String prefix = null;

	public HelloFileRulerSet() {
		this("");
	}

	public HelloFileRulerSet(String prefix) {
		super();
		this.namespaceURI = null;
		this.prefix = prefix;
	}

	@Override
	public void addRuleInstances(Digester digester) {
		digester.addObjectCreate(prefix + "file", HelloFile.class);
		digester.addSetProperties(prefix + "file", "dir", "path");
		digester.addBeanPropertySetter(prefix + "file", "name");
		digester.addSetNext(prefix + "file", "addFile");
	}
}
