package week1_0306;
/*
提示录入几个学生信息
录入学号姓名三门成绩
打印学生中语文数学英语成绩最高的那个人
统计每门课的平均成绩及总成绩的平均值
封装处理
*/
import java.util.Scanner;
import java.util.Comparator;

public class ScoreRecord implements Comparator
{
	
	private int id;
	private String name;
	private float C_Score;
	private float M_Score;
	private float E_Score;
	
	public ScoreRecord(){};
	
	public ScoreRecord(int id,String name,float C_Score, float M_Score, float E_Score)
	{
		this.id=id;
		this.name=name;
		this.C_Score=C_Score;
		this.E_Score=E_Score;
		this.M_Score=M_Score;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getC_Score() {
		return C_Score;
	}

	public void setC_Score(float c_Score) {
		C_Score = c_Score;
	}

	public float getM_Score() {
		return M_Score;
	}

	public void setM_Score(float m_Score) {
		M_Score = m_Score;
	}

	public float getE_Score() {
		return E_Score;
	}

	public void setE_Score(float e_Score) {
		E_Score = e_Score;
	}
	
	public int compare(Object o1, Object o2)
	{
		ScoreRecord s1 = (ScoreRecord) o1;
		ScoreRecord s2 = (ScoreRecord) o2;
		if(s1.id>s2.id)
			return 1;
		else if(s1.id==s2.id)
			return 0;
		else 
			return -1;
	}
	
	public int compareTo(Object o1)
	{
		ScoreRecord s = (ScoreRecord) o1;
		if(this.id>s.id)
			return 1;
		else if(this.id==s.id)
			return 0;
		else return -1;
	}
	
	public static void readData(ScoreRecord[] arr)
	{
		Scanner a=new Scanner(System.in);
		for(int i=0;i<arr.length;i++)
		{
			ScoreRecord s=new ScoreRecord();
			System.out.println("第"+(i+1)+"个同学的学号：");
			s.id=a.nextInt();
			System.out.println("第"+(i+1)+"个同学的姓名：");
			s.name=a.next();
			System.out.println("第"+(i+1)+"个同学的语文成绩：");
			s.C_Score=a.nextFloat();
			System.out.println("第"+(i+1)+"个同学的数学成绩：");
			s.M_Score=a.nextFloat();
			System.out.println("第"+(i+1)+"个同学的英语成绩：");
			s.E_Score=a.nextFloat();
			
			arr[i]=s;
			
		}
	}
	
	public static void printData(ScoreRecord[] arr)
	{
		
		for(int i=0;i<arr.length;i++)
		{
			System.out.println("学号"+arr[i].id+"  "+"姓名："+arr[i].name+"  "+"语文："+arr[i].C_Score+"数学："+arr[i].M_Score+"英语："+arr[i].E_Score);
		}

	}
	
	public static void printObject(Object o)
	{
		ScoreRecord s=(ScoreRecord)o;
		System.out.println("学号"+s.id+"  "+"姓名："+s.name+"  "+"语文："+s.C_Score+"数学："+s.M_Score+"英语："+s.E_Score);
	}
	
	
	
	public static void printMax(ScoreRecord[] arr)
	{
		ScoreRecord c= new ScoreRecord();
		ScoreRecord m=new ScoreRecord();
		ScoreRecord e = new ScoreRecord();
		for(int i=0;i<arr.length;i++)
		{
			if(c.C_Score<arr[i].C_Score)
				c=arr[i];
			if(m.M_Score<arr[i].M_Score)
				m=arr[i];
			if(e.E_Score<arr[i].E_Score)
				e=arr[i];				
		}
		System.out.print("语文成绩最高的是:");
		System.out.println("学号"+c.id+"  "+"姓名："+c.name+"  "+"语文："+c.C_Score);
		System.out.print("数学成绩最高的是:");
		System.out.println("学号"+m.id+"  "+"姓名："+m.name+"  "+"数学："+m.M_Score);
		System.out.print("英语成绩最高的是:");
		System.out.println("学号"+e.id+"  "+"姓名："+e.name+"  "+"英语："+e.E_Score);
	}
	
	public static void average(ScoreRecord[] arr)
	{
		float avr=0,c_avr=0,m_avr=0,e_avr=0;
		for(int i=0;i<arr.length;i++)
		{
			avr+=arr[i].C_Score+arr[i].E_Score+arr[i].M_Score;
			c_avr+=arr[i].C_Score;
			m_avr+=arr[i].E_Score;
			e_avr+=arr[i].M_Score;
		}
		avr=avr/arr.length;
		c_avr=c_avr/arr.length;
		m_avr=m_avr/arr.length;
		e_avr=e_avr/arr.length;
		System.out.println("语文平均成绩："+c_avr);
		System.out.println("数学平均成绩："+m_avr);
		System.out.println("英语平均成绩："+e_avr);
		System.out.println("总成绩平均成绩："+avr);	
	}


	

}
