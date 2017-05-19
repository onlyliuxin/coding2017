package com.github.orajavac.coding2017.jvm;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import com.github.orajavac.coding2017.basic.tree.FileList;

class demo{
	public static List<Object> l = new ArrayList<Object>();
}

public class PermGenSpaceDemo {
	
	public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		URL url = null;
		List classLoaderList = new ArrayList();
		try {
			url = new File("D:/coding2017/group02/562768642/bin/com/github/orajavac/coding2017").toURI().toURL();
			URL[] urls = { url };
			while (true) {
				ClassLoader loader = new URLClassLoader(urls);
				classLoaderList.add(loader);
				loader.loadClass("com.github.orajavac.coding2017.basic.ArrayList");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


