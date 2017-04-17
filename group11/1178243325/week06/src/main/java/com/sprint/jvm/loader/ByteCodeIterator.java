package com.sprint.jvm.loader;

import com.sprint.jvm.util.Util;
import java.util.Arrays;
public class ByteCodeIterator {
	byte[] codes;
	int pos = 0;
	ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}

	public String nextU4ToHexString() {
		return Util.byteToHexString(new byte[] {codes[pos++], codes[pos++], codes[pos++], codes[pos++]});
	}


}
