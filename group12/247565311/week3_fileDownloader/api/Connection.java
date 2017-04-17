package week3_fileDownloader.api;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface Connection{
	/**
	 * 缁欏畾寮�鍜岀粨鏉熶綅缃紝 璇诲彇鏁版嵁锛�杩斿洖鍊兼槸瀛楄妭鏁扮粍
	 * @param startPos 寮�浣嶇疆锛�浠�寮�
	 * @param endPos 缁撴潫浣嶇疆
	 * @return
	 */
	public byte[] read(int startPos,int endPos) throws IOException;
	/**
	 * 寰楀埌鏁版嵁鍐呭鐨勯暱搴�	 * @return
	 */
	public int getContentLength();
	
	/**
	 * 鍏抽棴杩炴帴
	 */
	public void close();
}
