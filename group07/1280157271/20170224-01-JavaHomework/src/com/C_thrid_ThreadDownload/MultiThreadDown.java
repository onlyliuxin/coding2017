package com.C_thrid_ThreadDownload;


public class MultiThreadDown {

	public static void main(String[] args) throws Exception{
		// 初始化DownUtil对象
//		final DownUtil downUtil = new DownUtil("http://www.crazyit.org/"
//				+ "attachments/month_1403/1403202355ff6cc9a4fbf6f14a.png"
//				, "ios.png", 3);//3个线程对象
		final DownUtil downUtil = new DownUtil("http://pic17.nipic.com/20111102/3707281_235344313129_2.jpg"
				, "ThreadDownload.png", 3);//3个线程对象
		//开始下载
		downUtil.download();
		new Thread(()->{
			while(downUtil.getCompleteRate()<1){
				//每隔0.1秒查询一次任务的完成进度
				//GUI程序中可根据来绘制进度条
				System.out.println("已完成："
						+downUtil.getCompleteRate());
				try{
					Thread.sleep(1000);
				}catch(Exception ex){
				}
			}
		}).start();
	}
}
