package mini_jvm.loader;

import mini_jvm.clz.ClassFile;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public ClassFile loadClass(String className){
		byte[] byteCodes = readBinaryCode(className);
		ClassFileParser classFileParser = new ClassFileParser();
		ClassFile clzFile = classFileParser.parse(byteCodes);
		return clzFile;
	}


	/**
	 * 读取class文件的二进制代码
	 * @param className
	 * @return
     */
	public byte[] readBinaryCodeV1(String className) {
		String clazzPath = getClassPath(className);
		BufferedInputStream bins = null;
		ByteArrayOutputStream  bouts = new ByteArrayOutputStream();
		try {
			bins = new BufferedInputStream(new FileInputStream(new File(clazzPath)));
			byte[] buffer = new byte[1024];
			int length = -1;
			while((length = bins.read(buffer)) != -1){
				bouts.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] codes = bouts.toByteArray();
		//关闭流
		try {
			if(bins != null){
				//调用外层流的close方法就关闭其装饰的内层流
				bins.close();
			}
			if(bouts != null){
				bouts.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codes;
	}

	/**
	 * 使用IOUtils.toByteArray()读取流文件
	 */
	public byte[] readBinaryCode(String className){
		String clzPath = getClassPath(className);
		File f = new File(clzPath);
		try{
			return IOUtils.toByteArray(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 从扫描根目录，获取指定类的绝对路径
	 * @param className
	 * @return
	 */
	private String getClassPath(String className){
		String clazzPath = null;
		//遍历clzPaths中所有路径
		for (String path : this.clzPaths){
			File file = new File(path);
			clazzPath = getClassPath(className,file);
			if(clazzPath!=null) break;
		}
		return clazzPath;
	}

	private String getClassPath(String className, File file){
		String clazzPath = null;
		if(file.exists()){
			//如果是目录，则遍历所有目录下的文件
			if(file.isDirectory()){
				File[] fs = file.listFiles();
				for (File f : fs){
					clazzPath = getClassPath(className,f);
				}
			}else {
				//检查是否是该类对应的class文件
				if(isClazzFile(file.getName(),className)){
					clazzPath = file.getAbsolutePath();
				}
			}
		}
		return clazzPath;
	}

	private boolean isClazzFile(String filename , String className){
		String fileClazzName = null;
		String [] names = filename.split("\\.");
		if(names.length > 0){
			fileClazzName = names[0];
		}
		return className.endsWith(fileClazzName);
	}
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (; i < clzPaths.size() - 1; i++) {
			sb.append(clzPaths.get(i));
			sb.append(";");
		}
		sb.append(clzPaths.get(i));
		return sb.toString();
	}

	public static void main(String[] args) {
		String FULL_QUALIFIED_CLASS_NAME = "mini_jvm/test/EmployeeV1";
		String path1 = "D:\\worksapce\\gitRepo\\java_coding2017\\coding2017\\group23\\769232552\\coding\\target\\test-classes\\mini_jvm";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "mini_jvm.test.EmployeeV1";
	}
}
