package miniJVM.method;

import miniJVM.attr.CodeAttr;
import miniJVM.clz.ClassFile;
import miniJVM.cmd.ByteCodeCommand;
import miniJVM.constant.ConstantPool;
import miniJVM.constant.UTF8Info;

import java.util.ArrayList;
import java.util.List;

public class Method {
	
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	private CodeAttr codeAttr;
	
	private ClassFile clzFile;
	
	
	public ClassFile getClzFile() {
		return clzFile;
	}

	public int getNameIndex() {
		return nameIndex;
	}
	public int getDescriptorIndex() {
		return descriptorIndex;
	}
	
	public CodeAttr getCodeAttr() {
		return codeAttr;
	}

	public void setCodeAttr(CodeAttr code) {
		this.codeAttr = code;
	}

	public Method(ClassFile clzFile,int accessFlag, int nameIndex, int descriptorIndex) {
		this.clzFile = clzFile;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	public String toString() {
		
		ConstantPool pool = this.clzFile.getConstantPool();
		StringBuilder buffer = new StringBuilder();
		
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		
		buffer.append(name).append(":").append(desc).append("\n");
		
		buffer.append(this.codeAttr.toString(pool));
		
		return buffer.toString();
	}
	
	public ByteCodeCommand[] getCmds() {
		ByteCodeCommand[] cmds = this.getCodeAttr().getCmds();

		return cmds;
	}

	private String getParammeterAndReturnType(){
		return ((UTF8Info) this.clzFile.getConstantPool().getConstantInfo(this.descriptorIndex)).getValue();
	}

	public List<String> getParammeterList(){
		// e.g. (Ljava/util/List;Ljava/lang/String;II)V
		String parammeterTypeString = this.getParammeterAndReturnType();

		int startPos = parammeterTypeString.indexOf("(");
		int endPos = parammeterTypeString.indexOf(")");

		String paramTypes = parammeterTypeString.substring(startPos + 1, endPos);

		List<String> paramTypeList = new ArrayList<String>();

		while(!paramTypes.equals("")){
			int pos = 0;

			if(paramTypes.charAt(pos) == 'L'){
				int end = paramTypes.indexOf(";");
				if(end == -1){
					throw new RuntimeException("an object start with L not end with ; found");
				}

				paramTypeList.add(paramTypes.substring(pos, end));
				pos = end;
			}else if(paramTypes.charAt(pos) == 'I'){
				paramTypeList.add("I");
			}else if(paramTypes.charAt(pos) == 'F'){
				paramTypeList.add("F");
			}else{
				throw new RuntimeException(paramTypes + " is not supported");
			}
			pos++;
			paramTypes = paramTypes.substring(pos);
		}

		return paramTypeList;
	}
}
