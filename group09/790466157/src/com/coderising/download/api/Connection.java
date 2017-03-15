package com.coderising.download.api;

import java.io.IOException;

public interface Connection {
	/**
	 * ������ʼ�ͽ���λ�ã� ��ȡ���ݣ� ����ֵ���ֽ�����
	 * @param startPos ��ʼλ�ã� ��0��ʼ
	 * @param endPos ����λ��
	 * @return
	 */
	public byte[] read(int startPos,int endPos) throws IOException;
	/**
	 * �õ��������ݵĳ���
	 * @return
	 */
	public int getContentLength();
	
	/**
	 * �ر�����
	 */
	public void close();
	public Connection open(Object url);
}


