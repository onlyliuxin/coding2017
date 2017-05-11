package com.coding.iostreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class readfile {

	public static void main(String[] args) {
		String filename = "/test.txt";
		fileread(filename);
	}
	
	public static void fileread(String filename){	
	
		File file = new File(filename);		
		Reader reader = null;
		int  n1=0;
		int  n2=2;
		try {
			reader = new InputStreamReader(new FileInputStream(file));
			int num ;
			while((num = reader.read())!= -1){
				if((char)num != '\r'){
					if((char)num >='A'&&(char)num <='z'){
						n1++;
					}
					if((char)num >='A'&&(char)num <='Z'){
						n2++;
					}
				}
			}			
			reader.close();	
			System.out.println(n1+""+n2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
		
		public  float method(){
			return 13.21f;
		}
	
	

		
	}
	
	
	
	
	
	
	
	
	
	

