package com.github.eulerlcs.jmr.challenge.xmlparser.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.github.eulerlcs.jmr.challenge.xmlparser.digester.entity.HelloFile;

import lombok.Getter;

@Getter
@XmlRootElement(name = "files")
public class Hello {
	@XmlAttribute
	private String project;
	@XmlAttribute
	private String value;
	@XmlElement(name = "file")
	private List<HelloFile> files;
}
