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
        index += 1;
        return (int)(a&0xff);
    }
	public int nextU2ToInt() {
		return nextU1ToInt()*256+nextU1ToInt();
	}

	public int nextU4ToInt() {
		return nextU1ToInt()*256*256*256+nextU1ToInt()*256*256+nextU1ToInt()*256+nextU1ToInt();
	}
    public String nextUxToString(int len){
    	String res="";
    	while(len>0){
    		len -= 1;
    		res += (char)(bytes[index]);
    		index += 1;
    	}
    	return res;
    }
	public String nextUxToHexString(int len) {
        String res = "";
        while(len>0){
        	len -= 1;
        	int val = (int)(bytes[index]&0xff);
            String hex = Integer.toHexString(val);
            res+=hex.length()<2?"0":"";
            res+=hex;
            index += 1;
        }
		return res;
	}
    public boolean hasNext(){
        return index<bytes.length;
    }
}
