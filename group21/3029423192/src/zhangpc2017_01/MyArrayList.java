import java.util.Scanner;

public class MyArrayList implements MyList {
 
	private int num = 0;//用来记录集合中的元素个数
	private int size = 0;
	Scanner sc = new Scanner(System.in);
	private int arrayLength = sc.nextInt();
	
	private Object[] elementDate = new Object[arrayLength];
	
	//直接在后面增加元素
	public void add(Object o){
		if(size <= elementDate.length){
			elementDate[size] = o;
		}else{
			elementDate = new Object[arrayLength+5];
			elementDate[size] =o;
		}
		size ++;
	}
	
	
	//在指定位置添加元素，能自增加
	public void add(int index,Object o){
		try{
				//当插入位置为空时
				if(elementDate[index-1] == null){
					elementDate[index-1] = o;
				}
				
				//插入位置不为空但最后一个位置为空
				if(elementDate[index] != null && elementDate[arrayLength-1] == null){
					
					//index+1后面的元素后移
					for(int x = arrayLength-1; x>= index; x--){
							elementDate[x-1] = elementDate[x];
						}	
						elementDate[index-1] = o;
					}
				
				//插入位置和最后一个位置均不为空
				if(elementDate[index] != null && elementDate[arrayLength-1] !=null){
						//溢出自增5
						elementDate = new Object[arrayLength+5];
						for(int x = elementDate.length-1; x>=index; x--){
							elementDate[x-1] = elementDate[x];
						}
						elementDate[index-1] = o;
					}
				num++;
			}catch(Exception e){
					System.out.println("捕获异常信息为"+e.getMessage());
			}
	}
	
	//获取第index元素
	public Object get(int index){
		Object flag;
		try{
			flag = elementDate[index];
		}catch(Exception e){
			flag = null;
			System.out.println("捕获异常信息为"+e.getMessage());
		}
		return flag;
	}
	
	//删除第index元素,返回第index个元素
	public Object remove(int index){
		Object flag;
		try{
			if(index == arrayLength-1){
				flag = elementDate[index];
				elementDate[index] = null;
			}
			else if(index >=0 && index < elementDate.length-1){
				flag = elementDate[index];
				for(int x = index; x < elementDate.length-1; x++){
					elementDate[index] = elementDate[index+1];
				}
			}else{
				flag = null;
			}
			num--;
		}catch(Exception e){
			System.out.println("捕获异常信息为"+e.getMessage());
			flag = null;
		}
			return flag;
	}
	
	//返回列表中的元素个数
	public int size(){
		return num;
	}
	
	public MyIterator myiterator(){
		return null;
	}
}
