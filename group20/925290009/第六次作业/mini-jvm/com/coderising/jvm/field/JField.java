package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.Utf8Info;
import com.coderising.jvm.loader.ByteCodeIterator;

public class JField {

	private int access_flags;
	private int name_index;
	private int descriptor_index;
	private ConstantPool pool;

	public JField(int access_flags, int name_index, int descriptor_index,
			ConstantPool pool) {
		this.access_flags = access_flags;
		this.name_index = name_index;
		this.descriptor_index = descriptor_index;
		this.pool = pool;
		
	}
	
	public static JField parse(ConstantPool pool, ByteCodeIterator iterator){
		
		int accessflag = iterator.next2BytesToInt();
		int nameindex = iterator.next2BytesToInt();
		int descripindex = iterator.next2BytesToInt();
		
		int attribute_count = iterator.next2BytesToInt();
		if (attribute_count > 0) {
			throw new RuntimeException("Field attribute_info is not added.");
		}
		JField field = new JField(accessflag, nameindex, descripindex, pool);
		return field;
	}
	
	public String toString(){
		
		String name = ((Utf8Info)this.pool.getConstantInfo(name_index)).getValue();
		String descriptor = ((Utf8Info) this.pool.getConstantInfo(descriptor_index)).getValue();
		
		return name + ":" + descriptor;
	}
}
