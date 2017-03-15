package com.coderising.download.api;

import java.io.IOException;
import java.net.URL;

public interface Connection {
	/**
	 * ������ʼ�ͽ���λ�ã� ��ȡ���ݣ� ����ֵ���ֽ�����
	 * @param startPos ��ʼλ�ã� ��0��ʼ
	 * @param endPos ����λ��
	 * @return
	 */
	public byte[] read(long startPos,long endPos) throws IOException;
	/**
	 * �õ��������ݵĳ���
	 * @return
	 */
	public long getContentLength();
	
	/**
	 * �ر�����
	 */
	public void close();
}
