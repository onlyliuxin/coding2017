package week3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import week3.api.Connection;

public class DownloadThread extends Thread{
	Connection conn;
	int startPos;
	int endPos;
	String path = "";
	public DownloadThread( Connection conn, int startPos, int endPos,String filepath){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.path = filepath;
	}
	public void run(){	
		// 获取下载的字节数组，写入文件，注意这是一个多线程程序。
		// 这个函数只会写一部分的文件
		
		// 从connect那里获取字节数组，如果没有字节数组了，就表示这部分下载完成
		// 向filepath写文件
		if(conn == null) return;
		ByteBuffer buffer = ByteBuffer.allocate(endPos-startPos);
		Path filepath = Paths.get(path);

		if(filepath == null) return;
		int curEndPos = startPos;
	//	while(curEndPos<endPos){
			startPos = curEndPos;
			curEndPos += 4096;
			//if (curEndPos > endPos) 
				curEndPos = endPos;
			try {
				byte[] data = conn.read(startPos, curEndPos);
				FileChannel channel = FileChannel.open(filepath,StandardOpenOption.WRITE);
				// System.out.println("startPos"+startPos + ", length:"+data.length);
				buffer.put(data);
                channel.write(buffer);
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("写文件错误");
			}
	//	}
		conn.close();
	}
}
