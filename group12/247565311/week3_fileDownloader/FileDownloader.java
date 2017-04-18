package week3_fileDownloader;

import java.util.concurrent.CyclicBarrier;
import java.io.IOException;
import java.io.RandomAccessFile;

import week3_fileDownloader.api.Connection;
import week3_fileDownloader.api.ConnectionException;
import week3_fileDownloader.api.ConnectionManager;
import week3_fileDownloader.api.DownloadListener;
import week3_fileDownloader.impl.ConnectionManagerImpl;

public class FileDownloader {
	private int MaxThreadNum = 4;
	private String url = null,path=null;
	DownloadListener listener = null;
	private ConnectionManager cm = new ConnectionManagerImpl();
	
	public FileDownloader(String weburl,String localpath) {
		this.url = weburl;
		this.path = localpath;
	}
	
	public void execute() throws InterruptedException{
		// 鍦ㄨ繖閲屽疄鐜颁綘鐨勪唬鐮侊紝 娉ㄦ剰锛�闇�鐢ㄥ绾跨▼瀹炵幇涓嬭浇
		// 杩欎釜绫讳緷璧栦簬鍏朵粬鍑犱釜鎺ュ彛, 浣犻渶瑕佸啓杩欏嚑涓帴鍙ｇ殑瀹炵幇浠ｇ爜
		// (1) ConnectionManager , 鍙互鎵撳紑涓�釜杩炴帴锛岄�杩嘋onnection鍙互璇诲彇鍏朵腑鐨勪竴娈碉紙鐢╯tartPos, endPos鏉ユ寚瀹氾級
		// (2) DownloadListener, 鐢变簬鏄绾跨▼涓嬭浇锛�璋冪敤杩欎釜绫荤殑瀹㈡埛绔笉鐭ラ亾浠�箞鏃跺�缁撴潫锛屾墍浠ヤ綘闇�瀹炵幇褰撴墍鏈�
		//     绾跨▼閮芥墽琛屽畬浠ュ悗锛�璋冪敤listener鐨刵otifiedFinished鏂规硶锛�杩欐牱瀹㈡埛绔氨鑳芥敹鍒伴�鐭ャ�
		// 鍏蜂綋鐨勫疄鐜版�璺細
		// 1. 闇�璋冪敤ConnectionManager鐨刼pen鏂规硶鎵撳紑杩炴帴锛�鐒跺悗閫氳繃Connection.getContentLength鏂规硶鑾峰緱鏂囦欢鐨勯暱搴�
		// 2. 鑷冲皯鍚姩3涓嚎绋嬩笅杞斤紝  娉ㄦ剰姣忎釜绾跨▼闇�鍏堣皟鐢–onnectionManager鐨刼pen鏂规硶
		// 鐒跺悗璋冪敤read鏂规硶锛�read鏂规硶涓湁璇诲彇鏂囦欢鐨勫紑濮嬩綅缃拰缁撴潫浣嶇疆鐨勫弬鏁帮紝 杩斿洖鍊兼槸byte[]鏁扮粍
		// 3. 鎶奲yte鏁扮粍鍐欏叆鍒版枃浠朵腑
		// 4. 鎵�湁鐨勭嚎绋嬮兘涓嬭浇瀹屾垚浠ュ悗锛�闇�璋冪敤listener鐨刵otifiedFinished鏂规硶
		
		// 涓嬮潰鐨勪唬鐮佹槸绀轰緥浠ｇ爜锛�涔熷氨鏄鍙湁涓�釜绾跨▼锛�浣犻渶瑕佹敼閫犳垚澶氱嚎绋嬬殑銆�
		Connection conn = null;
		CyclicBarrier barr= new CyclicBarrier(MaxThreadNum,new Runnable(){
			public void run(){
				listener.notifyFinished();
			}
		});
		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();	
			// 鍦ㄨ繖閲屽垱寤哄嚭涓�釜鍚屾牱澶у皬鐨勭┖鏂囦欢锛�璺緞鏄痯ath
			RandomAccessFile tarfile = new RandomAccessFile(path,"rw");
			tarfile.setLength(length);
			tarfile.close();
			Thread[] threads = new Thread[4];
			threads[0] = new DownloadThread(barr,cm.open(this.url),0,length/4,path);
			threads[1] = new DownloadThread(barr,cm.open(this.url),length/4,length/2,path);
		    threads[2] = new DownloadThread(barr,cm.open(this.url),length/2,3*length/4,path);
		    threads[3] = new DownloadThread(barr,cm.open(this.url),3*length/4,length,path);
			for(int i=0;i<4;i++)
			    threads[i].start();
		} catch (ConnectionException | IOException e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.close();
			}
		}
	}
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}
	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	public DownloadListener getListener(){
		return this.listener;
	}
	public double getDownPercent(){
		return 0.0;
	}
}
