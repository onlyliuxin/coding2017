package com.C_thrid_ThreadDownload;


public class MultiThreadDown {

	public static void main(String[] args) throws Exception{
		// ��ʼ��DownUtil����
//		final DownUtil downUtil = new DownUtil("http://www.crazyit.org/"
//				+ "attachments/month_1403/1403202355ff6cc9a4fbf6f14a.png"
//				, "ios.png", 3);//3���̶߳���
		final DownUtil downUtil = new DownUtil("http://pic17.nipic.com/20111102/3707281_235344313129_2.jpg"
				, "ThreadDownload.png", 3);//3���̶߳���
		//��ʼ����
		downUtil.download();
		new Thread(()->{
			while(downUtil.getCompleteRate()<1){
				//ÿ��0.1���ѯһ���������ɽ���
				//GUI�����пɸ��������ƽ�����
				System.out.println("����ɣ�"
						+downUtil.getCompleteRate());
				try{
					Thread.sleep(1000);
				}catch(Exception ex){
				}
			}
		}).start();
	}
}
