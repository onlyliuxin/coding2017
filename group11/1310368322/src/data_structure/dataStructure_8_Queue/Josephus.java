package dataStructure_8_Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Queue��ʵ��Josephus����
 * ��������ϵ����⵱�У� N�����ݾ�������һ��ͬ�������ַ�ʽ��������������  N����Χ��һȦ��λ�ü�Ϊ0��N-1���� ���Ҵӵ�һ���˱����� ����M���˻ᱻɱ���� ֱ�����һ����������
 * �÷�������һ��List�� �����˱�ɱ���˵Ĵ���
 * @author liuxin
 *
 */

public class Josephus {
	
	
	public static List<Integer> execute(int n, int m){	
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> listNew = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			list.add(i);
		}
		
		int size = list.size();
		System.out.println(list.size());
		int index = 0;
		while(size != 0){
			index += m-1;
			index = index%size;
			listNew.add(list.remove(index));
			size--;
		}
		
		return listNew;
	}
}
