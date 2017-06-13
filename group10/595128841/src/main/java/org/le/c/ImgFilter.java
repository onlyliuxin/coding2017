/**
 * 
 */
package org.le.c;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 图片过滤器
 * @author yue
 * @time 2017年3月12日
 */
public class ImgFilter implements FilenameFilter {

	/**
	 *
	 */
	@Override
	public boolean accept(File dir, String name) {
		String s1 = name.toLowerCase();
		return s1.endsWith(".jpg") || s1.endsWith(".png");
	}

}
