package week567_miniJVM.loader;

public class ByteCodeIterator {
    private byte[] bytes = null;
    int index = 0;
    public ByteCodeIterator(byte[] _byte){
        bytes = _byte;
    }
    public void skip(int i){
        index += i;
    }
    public int nextU1ToInt(){
        byte a = bytes[index];
        System.out.print(a);
        index += 1;
        return (int)(a&0xff);
    }
	public int nextU2ToInt() {
		return nextU1ToInt()*256+nextU1ToInt();
	}

	public int nextU4ToInt() {
		return nextU1ToInt()*256*256*256+nextU1ToInt()*256*256+nextU1ToInt()*256+nextU1ToInt();
	}

	public String nextUxToHexString(int len) {
        String res = "";
        while(len>0){
            len -= 1;
            int a = nextU1ToInt();
            res += (char)(a&0xff);
        }
		return res;
	}
    public boolean hasNext(){
        return index<bytes.length;
    }
}
