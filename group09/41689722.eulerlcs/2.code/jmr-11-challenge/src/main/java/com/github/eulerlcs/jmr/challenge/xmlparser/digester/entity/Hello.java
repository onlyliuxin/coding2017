package com.github.eulerlcs.jmr.challenge.xmlparser.digester.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hello {
	private String project;
	private String value;
	private List<HelloFile> files = new ArrayList<>();

	public void addFile(HelloFile file) {
		files.add(file);
	}
}
