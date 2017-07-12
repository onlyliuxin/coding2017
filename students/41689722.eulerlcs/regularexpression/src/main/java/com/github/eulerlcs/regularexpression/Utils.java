package com.github.eulerlcs.regularexpression;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

	public static String readAllFromResouce(String resourceName) {
		byte[] fileContentBytes;
		try {
			Path path = Paths.get(ClassLoader.getSystemResource(resourceName).toURI());
			fileContentBytes = Files.readAllBytes(path);
			String fileContentStr = new String(fileContentBytes, StandardCharsets.UTF_8);

			return fileContentStr;
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
}
