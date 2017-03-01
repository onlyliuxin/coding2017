package com.eulerlcs.jmr.xmlparser.digester.core;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

import com.eulerlcs.jmr.xmlparser.digester.entity.HelloFile;

final class FileRulerSet extends RuleSetBase {
	protected String prefix = null;

	public FileRulerSet() {
		this("");
	}

	public FileRulerSet(String prefix) {
		super();
		this.namespaceURI = null;
		this.prefix = prefix;
	}

	@Override
	public void addRuleInstances(Digester digester) {
		digester.addObjectCreate(prefix + "file", HelloFile.class);
		digester.addSetProperties(prefix + "file", "dir", "path");

		digester.addSetNext(prefix + "file", "addFile");

		digester.addCallMethod(prefix + "file", "setName", 1);
		digester.addCallParam(prefix + "file", 0);

	}

}
