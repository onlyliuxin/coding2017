package tree;

import java.io.File;

public class FileList {

	public void list(File file)
	{
		//获取该目录下所有的文件或者文件夹的File数组
		File[] fileArray=file.listFiles();
		// 遍历该File数组，得到每一个File对象
		for(File f:fileArray)
		{
			// 判断该File对象是否是文件夹
			if(f.isDirectory())
			{
				System.out.println("文件夹名为："+f.getName());
				list(f);
			}else{
				System.out.println("文件名为："+f.getName());
			}
		}
	}
}
