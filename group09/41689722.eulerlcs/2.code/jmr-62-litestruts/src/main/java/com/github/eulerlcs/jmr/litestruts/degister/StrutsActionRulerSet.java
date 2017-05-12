package com.github.eulerlcs.jmr.litestruts.degister;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

final class StrutsActionRulerSet extends RuleSetBase {
	protected String prefix = null;

	public StrutsActionRulerSet() {
		this("");
	}

	public StrutsActionRulerSet(String prefix) {
		super();
		this.namespaceURI = null;
		this.prefix = prefix;
	}

	@Override
	public void addRuleInstances(Digester digester) {
		digester.addObjectCreate(prefix + "action", StrutsAction.class);
		digester.addSetProperties(prefix + "action");
		digester.addSetProperties(prefix + "action", "class", "clazz");
		digester.addSetNext(prefix + "action", "addAction");
		digester.addObjectCreate(prefix + "action/result", StrutsActionResult.class);
		digester.addSetProperties(prefix + "action/result");
		digester.addBeanPropertySetter(prefix + "action/result", "url");
		digester.addSetNext(prefix + "action/result", "addResult");
	}
}