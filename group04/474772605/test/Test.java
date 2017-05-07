

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.InputStreamReader;
	import java.io.Reader;




	public  class Test{
		 static int n1 =0 ;
		 static int n2 =0 ;

		public static void main(String[] args) throws Exception{
			 
			Test.readFileByChars("D://Text.txt");
			
			System.out.println("字母个数为："+n1+"  字母个数为"+n2);
			
	}

		 public static void readFileByChars(String fileName) {
		        File file = new File(fileName);
		        Reader reader = null;
		        try {
		            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
		            // 一次读一个字符
		            reader = new InputStreamReader(new FileInputStream(file));
		            int tempchar;
		            while ((tempchar = reader.read()) != -1) {
		                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
		                // 但如果这两个字符分开显示时，会换两次行。
		                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
		                if (((char) tempchar) != '\r') {
		                    System.out.print((char) tempchar);
		                      n1++;
		                    if((char)tempchar >='A'&&(char)tempchar<='Z'){
		                    	n2++;
		                    }
		                    
		                }
		            }
		            reader.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
	}

