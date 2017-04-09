package coderising.jvm.loader;

import coderising.jvm.util.Util;

public class ByteCodeIterator {
	private byte[] codes;
	private int pos =0;
	public ByteCodeIterator(byte[] codes){
		this.codes = codes;
	}
	public String nextU4ToHexString() {		
		byte[] bys = new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]};		
		return Util.byteToHexString(bys);
	}
	
	public int nextU1ToInt(){
		byte[] bys = new byte[]{codes[pos++]};
		return Util.byteToInt(bys);
	}
	public int nextU2ToInt(){
		byte[] bys = new byte[]{codes[pos++],codes[pos++]};
		return Util.byteToInt(bys);
	}
	public String nextString(int len){
		byte[] bys = new byte[len];
		for (int i = 0; i < bys.length; i++) {
			bys[i]=codes[pos++];
		}
		String result = new String(bys);
		return result;
	}
}
