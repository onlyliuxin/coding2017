package com.coderising.ood.srp;

import java.io.File;
import java.util.Map;

import com.coderising.ood.srp.api.FileLoader;

public abstract class BaseFileLoader implements FileLoader {
	public abstract  Map readFile(File file);
}
