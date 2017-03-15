package lessones03;
import lessones01.Node;
import lessones01.LinkedList;
import others.Range;
//ֻҪ����prior�ͺ͵�������һ���˰ɡ�����
//��ȻҪ���ǵ����б��������д��벻�ù��ǵ�����˫��(������LinkedList����,����Node)������
public class LinkedListNoPrior<T> extends LinkedList<T>{
	protected void _reverse(){
		if(size() == 1){return;}
		T temp = _remove(size()-1).value;
		_reverse();
		_add(temp,0);
	}
	protected void _removeFirstHalf(){
		(first = _getNode((int)(size()>>1))).setLeft(null);//����ڵ�Ҳ������setLeft����
		size-=size/2;
	}
	protected void _remove(int arg_i,int arg_length){
		Range<Integer> thisRange = new Range<Integer>(0,size());
		Range<Integer> targetRange = thisRange.and(new Range<Integer>(arg_i,arg_i+arg_length));
		if(size == arg_length){
			(lastest = first).value = null;
			first.setRight(null);
		}else if(targetRange.min() == 0){
			first = _getNode(targetRange.max());
			first.setLeft(null);
		}else if(targetRange.max() == size){
			lastest = _getNode(targetRange.min()-1);
			lastest.setRight(null);
		}else{
			_getNode(targetRange.min()-1).setRight(_getNode(targetRange.max()));
		}
		size -= arg_length;
		//for(int i = targetRange.min();i<targetRange.max();i++){remove(i);}
	}
	@SuppressWarnings("unchecked")
	protected T[] _getElements(LinkedList<Integer> arg_list){
		//��Ŀ���ߵ����Ѿ��ź����LinkedList
		//û�´�Ļ�Ӧ�þ�������취�Ż��ˡ�����
		//Ȼ����������Ѿ���LinkedListWithFloat�������(Ц)
		//Ȼ���������ﲻ�ܼ̳�LinkedListWithFloat����
		//ps:arg_list������LinkedListWithFloat,���Բ�����ʲô�Ż�
		int list_size = arg_list.size();
		T[] arr = (T[])(new Object[size()]);
		int list_i = 0;
		int i = arg_list.get(0/*list_i*/);
		Node<T> node = _getNode(i);
		while((node!=null)&&list_i<list_size){
			if(i++ == arg_list.get(list_i)){arr[list_i++] = node.value;}
			node = node.right;
		}
		return arr;
	}
	protected void _subtract_sorted(LinkedList<T> arg_list){
		for(int i=0;i<arg_list.size();i++){
			T t = arg_list.get(i);
			Integer n;
			while((n = index(t))!=null){remove(n);}
		}
	}
	protected void _removeDuplicateValues_sorted(){
		T temp = get(0);
		T t;
		int i = 1;
		while(i<size()){
			if(temp.equals(t = get(i))){
				remove(i);
			}else{
				i++;
				temp = t;
			}
		}
	}
}