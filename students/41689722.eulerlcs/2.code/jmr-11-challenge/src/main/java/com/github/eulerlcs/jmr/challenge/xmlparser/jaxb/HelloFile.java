package com.github.eulerlcs.jmr.challenge.xmlparser.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import lombok.Getter;

@Getter
public class HelloFile {
	@XmlAttribute(name = "dir")
	private String path;
	@XmlValue
	private String name;
}