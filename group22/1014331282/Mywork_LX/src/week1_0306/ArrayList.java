package week1_0306;



public class ArrayList implements List
{

	private int size;
	
	private Object[] elementData ;
	
    public ArrayList()
	{
    	elementData = new Object[3];
    	size=-1;
	}
	
	public void add(Object o)
	{
		if(size >= elementData.length-1)
		{
			Object[] replaceData=new Object[elementData.length+5];
			System.arraycopy(elementData, 0, replaceData, 0, elementData.length);
			elementData = replaceData;
		}
		elementData[++size] = o;
	}
	
	public void add(int index, Object o)
	{
		Object[] replaceData=new Object[11];
		for(int i=0;i<index;i++)
		{
			replaceData[i]=elementData[i];
		}
		replaceData[index]=o;
		for(int i=index+1;i<elementData.length+1;i++)
		{
			replaceData[i]=elementData[i-1];
		}
		elementData=replaceData;
		size++;
	}
	
	public Object get(int index)
	{
		if (index<=size && index>=0 && size>=0)
			return elementData[index];
		else return null;
	}
	
	
	public Object remove(int index)
	{
		size--;
		Object o=elementData[index];
		if (index<elementData.length && index>0)
		{
			for(int i=index;i<elementData.length-1;i++)
			{
				elementData[i]=elementData[i+1];
			}
			elementData[elementData.length-1]=null;
			return o;
		}
		else return null;
	}
	
	public int size()
	{
		if(size>=0)
			return size+1;
		else
			return 0;
	}
	
	public Iterator iterator()
	{
		return new ArrayListIterator(this);
	}

	private class ArrayListIterator implements Iterator
	{
		ArrayList l=null;
		int pos = 0;
		private ArrayListIterator(ArrayList l)
		{
			this.l=l;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			pos++;
			if(pos > size)
				return false;
			else return true;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return elementData[pos];
			
		}
		
		public Object remove()
		{
			return this.l.remove(pos);
		}
		
	}
	
}
