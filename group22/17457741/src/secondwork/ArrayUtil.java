package secondwork;


import java.util.Arrays;

import org.junit.Test;

public class ArrayUtil {
       public void reverseArray (int [] origin){
    	   int le =origin.length;
    	   int []changedArray=Arrays.copyOf(origin, le);
    	   for (int i=0;i<le;i++)
    		   origin[le-i]=changedArray[i];
       }
       
       public int []removeZero(int []oldArray){
    	   int le=oldArray.length;
    	   
    	   for(int i=0;i<le;i++)
    		   if(oldArray[i]==0)
    			   le--;
    		
    	   int []newArray=new int[le];
    	   int count=0;
    	   for(int i=0;i<oldArray.length;i++){
    		   if(oldArray[i]!=0){
    			   newArray[count]=oldArray[i];
    			   count++;
    		   }
    	   }
    	   return newArray;
       }
       
       public int []merge (int [] array1,int []array2){
    	   int le=array1.length+array2.length;
    	   int [] mergedArray=new int[le];
    	   for(int i=0;i<array1.length;i++)
    		   mergedArray[i]=array1[i];
    	   
    	  int count =array1.length;
    	  for(int i=0;i<array2.length;i++)
    		  mergedArray[count++]=array2[i];
    	  
    	   return mergedArray;
       }
       
       public int []grow(int []oldArray,int size){
    	   int le=oldArray.length+size;
    	   int [] growedArray=new int[le];
    	   for(int i=0;i<oldArray.length;i++){
    		   growedArray[i]=oldArray[i];
    	   }
    	   return growedArray;
       }
       
       public int []fibonacci(int max){
    	   int []fibonacci;
    	   if(max==1)
    		   fibonacci=new int[0];
    	   else{
    	  int n1=1,n2=1,sn=0,count=2;
    	  do{
    		  sn=n1+n2;
    		  n1=n2;
    		  n2=sn;
    		  count++;
    	  }while(sn<=max);
    	  fibonacci=new int [count];
    	  fibonacci[0]=1;
    	  fibonacci[1]=1;
    	  for (int i=2;i<count;i++){
    		  int m1=1,m2=1,sm=0;
    		  sm=m1+m2; m1=m2;m2=sm;
    		  fibonacci[i]=sm;
    	  }
    	 }
    	   return fibonacci;
    	   
       }
       
       public int [] getPrimes(int max){
    	   int a=2,count=0,b=2;
    	  for(int i=1;i<max+1;i++){
    		  if(isPrime(a++)){
    			 count++;
    		  }  
    	  }
    	  int []primes=new int [count];
    	  for(int i=0;i<count;i++){
    		  if(isPrime(b++)){
    			 primes[i]=b;
    		  }  
    	  }
		return primes;
       }
       
     

	private boolean isPrime(int a) {
		// TODO Auto-generated method stub
		boolean isPrime=true;
		for(int i=0;i<Math.sqrt(a);i++){
			if(a%i==0)
				isPrime=false;
		}
		return isPrime;
	}

	public int []getPerfectNumbers(int max){
		int a=1,count =0,b=1;
		for(int i=1;i<max+1;i++){
			if(isPerfectNumber(a++)){
				count++;
			}
		}
		int []getPerfectNumber=new int[count];
		for(int i=1;i<count;i++){
			if(isPerfectNumber(b++)){
				getPerfectNumber[i]=b;
			}
		}
    	   return getPerfectNumber;
       }
       
       private boolean isPerfectNumber(int a) {
		// TODO Auto-generated method stub
    	   boolean perfectNumber=false;
    	   int count=0,sum=0;
    	   int [] sumOfFactor=new int[100];
    	   for(int i=0;i<Math.sqrt(a);i++){
    		   if(a%i==0)
    			   sumOfFactor[count++]=i;  
    	   }
    	  for(int i=0;i<100;i++){
    		 sum+=sumOfFactor[i];
    	  }
    	  if(sum==a)
    		  perfectNumber=true; 
		return perfectNumber;
	}
 
   
	public static String join(int []array,String sperator){
    	 String output="";
    	 for(int i=0;i<array.length;i++){
    		 output+=array[i];
    		 output+=sperator;
    	 }
    	 output=output.substring(0, array.length*2-1);
		return output;
       }
	
	

	
   
}
