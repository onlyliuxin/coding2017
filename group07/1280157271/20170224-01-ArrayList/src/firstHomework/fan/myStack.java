package firstHomework.fan;

 

public class myStack {
	private myArrayList array = new myArrayList();//myArrayList�������и���̬����

	public void push(Object o){	//��ջ
		 //���Ȳ���ʱmyArrayList���Լ�����
		array.add(o);//�¶�������������
	}
	
	public Object pop(){//��ջ��������β�ϵ�Ԫ�أ� ��Ҫɾ����
		Object pop = array.get(array.size()-1);//�±�Ҫ��sizeС1
		array.remove(array.size()-1);
		return pop;
	}
	
	public Object peek(){//ֻ�ǵ���ջ����ֵ����ɾ��
		return array.get(array.size()-1);
	}
	public boolean isEmpty(){
		return array.size()==0;
	}
	public int size(){
		return array.size();
	}

	
}

