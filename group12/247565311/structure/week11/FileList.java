package structure.week11;

import java.io.File;

public class FileList {
	public void list(File f) {		
        File[] tempList = f.listFiles();
        for(int i=0;i<tempList.length;i++){
        	if(tempList[i].isDirectory())list(tempList[i]);
        	else System.out.println(tempList[i].toString());
        }
	}	
}
