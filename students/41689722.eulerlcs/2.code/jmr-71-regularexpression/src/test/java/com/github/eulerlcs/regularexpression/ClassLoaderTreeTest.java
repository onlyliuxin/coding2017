package com.github.eulerlcs.regularexpression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ClassLoaderTreeTest {

	@Test
	public void test01_01() {
		String text = Utils.readAllFromResouce("01_01.txt");
		System.out.println("--orignal text start--");
		System.out.print(text);
		System.out.println("--orignal text  end --");

		// 统一换行符
		String result = text.replaceAll("(\r\n)|\r", "\n");
		System.out.println("--统一换行符 start--");
		System.out.print(result);
		System.out.println("--统一换行符   end --");

		// 行单位前后trim
		result = result.replaceAll("(?m)^\\s*(.*?)\\s*$", "$1");
		System.out.println("--行单位前后trim start--");
		System.out.print(result);
		System.out.println("--行单位前后trim   end --");

		assertFalse(result.equals(text));
	}

	@Test
	public void test01_02() {

		// ^ $ \A \E (?m)
		String text = "123\r\nabc def";
		String regex = "\\Aabc";
		// Pattern p = Pattern.compile(regex);
		Pattern p = Pattern.compile(regex, Pattern.MULTILINE);

		Matcher m = p.matcher(text);

		boolean result = m.find();
		if (result) {
			System.out.println("found");
		} else {
			System.out.println("not found");
		}
		assertTrue(result);
	}

	@Test
	public void test01_03_dotall() {
		Pattern p = null;
		Matcher m = null;

		String text1 = "width height";
		String text2 = "width\nheight";
		// Pattern p = Pattern.compile("(?s)width.height");
		p = Pattern.compile("width.height", Pattern.DOTALL);

		m = p.matcher(text1);
		boolean result1 = m.find();

		m = p.matcher(text2);
		boolean result2 = m.find();
		if (result2) {
			System.out.println("found");
		} else {
			System.out.println("not found");
		}

		assertTrue(result1);
		assertTrue(result2);
	}

	@Test
	public void test01_04_Zz() {
		Pattern p = null;
		Matcher m = null;
		boolean result1 = false;
		boolean result2 = false;
		boolean result3 = false;

		String text1 = "abc def";
		String text2 = "def abc";
		String text3 = "def abc\n";

		p = Pattern.compile("abc\\z");

		m = p.matcher(text1);
		result1 = m.find();

		m = p.matcher(text2);
		result2 = m.find();

		m = p.matcher(text3);
		result3 = m.find();

		p = Pattern.compile("abc\\Z");

		m = p.matcher(text1);
		result1 = m.find();

		m = p.matcher(text2);
		result2 = m.find();

		m = p.matcher(text3);
		result3 = m.find();

		assertFalse(result1);
		assertTrue(result2);
		assertTrue(result3);
	}

}
