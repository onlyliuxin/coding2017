package com.github.eulerlcs.jmr.challenge.xmlparser.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name = "app-config")
public class AppConfig {
	@XmlElement(name = "input-path")
	private String inputPath;
	@XmlElement(name = "input-look-subfolder")
	private boolean inputLookSubfolder;
	@XmlElement(name = "input-encode")
	private String inputEncode;
	@XmlElement(name = "output-path")
	private String outputPath;
	@XmlElement(name = "output-by-package-tree")
	private boolean outputByPackageTree;
	@XmlElement(name = "output-prefix")
	private String outputPrefix;
	@XmlElement(name = "output-subfix")
	private String outputSubfix;
	@XmlElement(name = "output-encode")
	private String outputEncode;
	@XmlElement(name = "output-package-name")
	private String outputPackageName;
}
