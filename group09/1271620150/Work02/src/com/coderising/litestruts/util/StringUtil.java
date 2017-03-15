package com.coderising.litestruts.util;

public class StringUtil {

	/**
	 * 判断是否为空字符串
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果不为空返回true
	 */
	public static boolean isNotBlank(String str) {
		return (str != null && !"".equals(str)) ? true : false;
	}

	/**
	 * 判断是否为空字符串
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果为空返回true
	 */
	public static boolean isBlank(String str) {
		return !isNotBlank(str);
	}

	/**
	 * 判断是否为空字符串(包括空格)
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果不为空返回true
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && !"".equals(str.trim())) ? true : false;
	}

	/**
	 * 判断是否为空字符串(包括空格)
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果为空返回true
	 */
	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	/**
	 * 字符串比较
	 * 
	 * @param src
	 * @param des
	 * @return
	 */
	public static boolean equals(String src, String des) {
		if (src == null)
			return (des == null ? true : false);
		if (des == null)
			return (src == null ? true : false);
		return src.equals(des);
	}

	/**
	 * 将String数组变成","号间隔的字符串
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果为空返回true
	 */
	public static String StringArrayToString(String[] str) {
		StringBuilder sb = new StringBuilder();
		if (str != null && str.length > 0) {
			for (String s : str) {
				if (s != null) {
					sb.append(s + ",");
				}
			}
			if (sb.length() == 0)
				return "";
			return sb.substring(0, sb.length() - 1).toString();
		}
		return str[0];
	}

	/**
	 * 判断URL后缀是否为.action,如果是的话，提取actionName
	 * 
	 * @param servletPath
	 *            request.getServletPath()
	 * @return actionName
	 */
	public static String parseServletPath(String servletPath) {
		if (null != servletPath && !"".equals(servletPath)) {
			if (servletPath.contains(".action")) {
				return servletPath.substring(servletPath.lastIndexOf("/") + 1,
						servletPath.indexOf(".action"));
			}
		}
		return "";
	}
}
