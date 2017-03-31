package others;
public class Range<T extends Comparable<T>>{
	private T min;
	private T max;
	public Range(T arg1,T arg2){
		min = arg1;
		max = arg2;
		check();
	}
	public T min(){
		return min;
	}
	public T max(){
		return max;
	}
	public void setMax(T arg_value){
		max = arg_value;
		check();
	}
	public void setMin(T arg_value){
		min = arg_value;
		check();
	}
	public boolean include(T arg_value){
		return min.compareTo(arg_value)<=0&&max.compareTo(arg_value)>0;
	}
	public boolean include(Range<T> arg_another){
		return min.compareTo(arg_another.min())<=0&&max.compareTo(arg_another.max())>=0;
	}
	public Range<T> and(Range<T> another){
		T m_min = min.compareTo(another.min())<0 ? another.min() : min;
		T m_max = max.compareTo(another.max())>0 ? another.max() : max;
		if(m_max.compareTo(m_min)<0){m_min = m_max = null;}
		return new Range<T>(m_min,m_max);
	}
	private void check(){
		if(min.compareTo(max)>0){
			T temp = max;
			max = min;
			min = temp;
		}
	}
}