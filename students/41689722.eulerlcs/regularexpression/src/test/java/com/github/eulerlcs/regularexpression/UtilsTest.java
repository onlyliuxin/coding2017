package com.github.eulerlcs.regularexpression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

// http://www.cnblogs.com/playing/archive/2011/03/15/1984943.html

public class UtilsTest {

	/**
	 * 多行模式-1
	 */
	@Test
	public void test01_01() {
		String t1 = Utils.readAllFromResouce("01.txt");

		// 默认 单行模式
		Pattern p1 = Pattern.compile("^def$");
		Matcher m1 = p1.matcher(t1);

		if (m1.find()) {
			System.out.println("found!");
		} else {
			System.out.println("not found!");
		}
	}

	/**
	 * <pre>
	 * 多行模式-2 
	 *  (?m)		Pattern.MULTILINE
	 * </pre>
	 */
	@Test
	public void test01_02() {
		String t1 = Utils.readAllFromResouce("01.txt");

		// 多行模式 写法一
		// Pattern p1 = Pattern.compile("(?m)^def$");
		// 多行模式 写法二
		Pattern p1 = Pattern.compile("^def$", Pattern.MULTILINE);

		Matcher m1 = p1.matcher(t1);

		if (m1.find()) {
			System.out.println("found!");
		} else {
			System.out.println("not found!");
		}
	}

	/**
	 * flag设定和(?X)的等价关系
	 * 
	 * <pre>
	 *  (?m)		Pattern.MULTILINE
	 *  (?i)		Pattern.CASE_INSENSITIVE
	 *  (?u)		Pattern.UNICODE_CASE
	 *  (?s)		Pattern.DOTALL
	 *  (?d)		Pattern.UNIX_LINES
	 *  (?x)		Pattern.COMMENTS
	 * </pre>
	 */

	/**
	 * <pre>
	 * ascii大小写
	 *  (?i)		Pattern.CASE_INSENSITIVE
	 * </pre>
	 */
	@Test
	public void test02_01() {
		String t1 = "abc AbC aCd ａｂｃ ＡＢｃ 2343";
		String r1 = "abc";

		// 默认 区分大小写
		// Pattern p1 = Pattern.compile(r1);

		// 忽略ascii大小，写法一
		Pattern p1 = Pattern.compile("(?i)abc");

		// 忽略ascii大小，写法而
		// Pattern p1 = Pattern.compile(r1, Pattern.CASE_INSENSITIVE );

		Matcher m1 = p1.matcher(t1);

		while (m1.find()) {
			System.out.println(m1.group());
		}
	}

	/**
	 * <pre>
	 * unicode大小写
	 *  (?u)		Pattern.UNICODE_CASE
	 * </pre>
	 */
	@Test
	public void test03_01() {
		String t1 = "abc AbC aCd ａｂｃ ＡＢｃ 2343";
		String r1 = "ａｂｃ";// 日文输入法下，全角abc,也就是宽字体

		// 默认 区分大小写只适用于ascii
		// Pattern p1 = Pattern.compile((?i)ａｂｃ);

		// 忽略ascii大小，写法一
		Pattern p1 = Pattern.compile("(?iu)ａｂｃ");

		// 忽略ascii大小，写法而
		// Pattern p1 = Pattern.compile(r1, Pattern.UNICODE_CASE);

		Matcher m1 = p1.matcher(t1);

		while (m1.find()) {
			System.out.println(m1.group());
		}
	}

	/** 通过设定标志位忽略大小写 */
	@Test
	public void test03_02() {
		String t1 = "abc AbC aCd\nABCD 2343";
		String r1 = "(?i)(?m)abc";
		Pattern p1 = Pattern.compile(r1);
		Matcher m1 = p1.matcher(t1);

		while (m1.find()) {
			System.out.println(m1.group());
		}
	}

	@Test
	public void test04_01_dotall() {
		Pattern p = null;
		Matcher m = null;

		String text1 = "width height";
		String text2 = "width\nheight";
		// Pattern p = Pattern.compile("(?s)width.height");
		p = Pattern.compile("width.height", Pattern.DOTALL);

		m = p.matcher(text1);
		boolean result1 = m.find();
		if (result1) {
			System.out.println("text1 found");
		} else {
			System.out.println("text1 not found");
		}

		m = p.matcher(text2);
		boolean result2 = m.find();
		if (result2) {
			System.out.println("text2 found");
		} else {
			System.out.println("text2 not found");
		}
	}

	/**
	 * group
	 * 
	 * <pre>
	 * group(0):正则表达式的匹配值 
	 * group(1):第一个子串
	 * </pre>
	 */
	@Test
	public void test05_01() {
		Pattern p = Pattern.compile("([a-z]+)-(\\d+)");
		Matcher m = p.matcher("type x-235, type y-3, type zw-465");

		while (m.find()) {
			for (int i = 0; i < m.groupCount() + 1; i++) {
				System.out.println("group(" + i + ")=" + m.group(i));
			}
			System.out.println("---------------------");
		}
	}

	/**
	 * 字符串分割的例子
	 */
	@Test
	public void test05_02() {
		String abc = "a///b/c";

		// 分割后的数组中包含空字符
		String[] array1 = abc.split("/");
		for (String str : array1) {
			System.out.println(str);
		}

		System.out.println("---------------------");

		// 分割后的数组中取出了空字符
		String[] array2 = abc.split("/+");
		for (String str : array2) {
			System.out.println(str);
		}
	}

	/**
	 * 替换
	 */
	@Test
	public void test06_01() {
		String str = "Orange is 100yuan, Banana is 180 yuan.";
		String regex = "\\d+\\s*yuan";
		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(str);
		System.out.println(m.find());
		String result = m.replaceFirst("_$0_");

		System.out.println(result);
	}

	/**
	 * 替换
	 */
	@Test
	public void test06_02() {
		String str = "Orange is 100yuan, Banana is 180 yuan.";
		String regex = "(\\d)\\s*(yuan)";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);

		String result = m.replaceAll("$2_$1");

		System.out.println(result);
	}

	/**
	 * 命名分组，替换
	 */
	@Test
	public void test06_03() {
		String pathfFilename = "aa/notepad.exe";

		String regex = "^.+/(?<filename>.+)$";
		String replacement = "${filename}";

		String filename = pathfFilename.replaceFirst(regex, replacement);
		System.out.println(filename);
	}

	/**
	 * 从文本中读取多行数据后，建议先把回车符删掉
	 */
	@Test
	public void test07_01() {
		String t1 = Utils.readAllFromResouce("07.txt");
		System.out.println("--orignal text start--");
		System.out.print(t1);
		System.out.println("--orignal text  end --");

		// 统一换行符
		String ret1 = t1.replaceAll("(\r\n)|\r", "\n");
		System.out.println("--统一换行符 start--");
		System.out.print(ret1);
		System.out.println("--统一换行符   end --");

		// 行单位前后trim
		String ret2 = ret1.replaceAll("(?m)^\\s*(.*?)\\s*$", "$1");
		System.out.println("--行单位前后trim start--");
		System.out.println(ret2);
		System.out.println("--行单位前后trim   end --");

		assertFalse(ret2.equals(t1));
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
