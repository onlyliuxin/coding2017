/**
 * copy from http://blog.csdn.net/ibm_hoojo/article/details/6838222
 */
package com.hoo.util;

/**
 * <b>function:</b> 下载测试
 * 
 * @author hoojo
 * @createDate 2011-9-23 下午05:49:46
 * @file TestDownloadMain.java
 * @package com.hoo.download
 * @project MultiThreadDownLoad
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class DownloadUtilsTest {
	public static void main(String[] args) {
		/*
		 * DownloadInfo bean = new DownloadInfo(
		 * "http://i7.meishichina.com/Health/UploadFiles/201109/2011092116224363.jpg"
		 * ); System.out.println(bean); BatchDownloadFile down = new
		 * BatchDownloadFile(bean); new Thread(down).start();
		 */

		// DownloadUtils.download("http://i7.meishichina.com/Health/UploadFiles/201109/2011092116224363.jpg");
		DownloadUtils.download("https://github.com/dracome/coding2017/archive/master.zip", 5);

		try {
			Thread.sleep(30 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 3;
		System.out.println(i);
	}
}