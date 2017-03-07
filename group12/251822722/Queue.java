public class Queue {


    private Object[] elementData = new Object[100];

    private int size =0;

    public void enQueue(Object o) {

        elementData[size]=o;
        size=size+1;
    }

    public Object deQueue() {

        Object a = elementData[0];

        Object[] newElementData = new Object[100];

        for (int i = 1; i < elementData.length; i++)
        {
            newElementData[i-1]=elementData[i];
        }
        elementData=newElementData;
        return a;
    }

    public boolean isEmpty() {
        if(size==0){
            return true;
        }
        return false;
    }

    public int size() {
        return elementData.length;
    }
}
