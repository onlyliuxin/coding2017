package com.coderising.ood.ocp;

public class FormatterFactory {
    public Formatter createFormatter(int type) {
	Formatter formatter = null;
	if (type == 1) {
	    formatter = new RawFormatter();
	}
	if (type == 2) {
	    formatter = new HtmlFormatter();
	}
	return formatter;
    }
}
