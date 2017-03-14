package week1_0306;

import static org.junit.Assert.*;

public class Test_ArrayList {

	@org.junit.Test
	public void test() {
		ArrayList list=new ArrayList();
		ScoreRecord s1=new ScoreRecord(8,"Tom",98,99,88);
		ScoreRecord s2=new ScoreRecord(2,"Jack",90,99,80);
		ScoreRecord s3=new ScoreRecord(5,"Merry",93,90,90);
		ScoreRecord s4=new ScoreRecord(9,"Mike",88,95,80);
		ScoreRecord s5=new ScoreRecord(3,"Jerry",92,80,78);
		ScoreRecord s6=new ScoreRecord(7,"Jason",91,87,83);
		
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		
		Test_ArrayList t = new Test_ArrayList();
		t.printList(list);
		
		System.out.println("ȡ�±���3��Ԫ�أ�");
		ScoreRecord g = (ScoreRecord)list.get(3);
		System.out.println(g.getId()+"<<<"+g.getName());
		
		System.out.println("ɾ���±���2��Ԫ�أ�");
		ScoreRecord r = (ScoreRecord)list.remove(2);
		System.out.println(r.getId()+"<<<"+r.getName());
		t.printList(list);
	
		System.out.println("���±���3��λ�ü�Ԫ��s6��");
		list.add(3, s6);
		t.printList(list);
		
		System.out.println("һ����"+list.size()+"��Ԫ��");

				
		
	}
	
	public void printList(ArrayList list)
	{
		System.out.println("���ڵ�Ԫ���У�");
		
	
		for(int i=0;i<list.size();i++)
		{
			System.out.println(((ScoreRecord) list.get(i)).getId()+"<<<"+((ScoreRecord)list.get(i)).getName());
		}
	}

}
