package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.constant.ConstantInfo;
import minijvm.constant.ConstantPool;
import minijvm.constant.StringInfo;
import minijvm.engine.ExecutionResult;
import minijvm.engine.Heap;
import minijvm.engine.JavaObject;
import minijvm.engine.StackFrame;

public class LdcCmd extends OneOperandCmd {

	public LdcCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}
	
	@Override
	public String toString() {
		
		ConstantInfo info = getConstantInfo(this.getOperand());
		
		String value = "TBD";
		if(info instanceof StringInfo){
			StringInfo strInfo = (StringInfo)info;
			value = strInfo.toString();
		}
		
		return this.getOffset()+":"+this.getOpCode()+" " + this.getReadableCodeText() + " "+  value;
		
	}

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        ConstantPool pool = this.getConstantPool();
        ConstantInfo info = pool.getConstantInfo(this.getOperand());
        
        if (info instanceof StringInfo) {
            StringInfo strInfo = (StringInfo) info;
            String value = strInfo.toString();
            JavaObject jo = Heap.getInstance().newString(value);
            frame.getOprandStack().push(jo);
        } else {
            throw new RuntimeException("暂时只支持字符串常量ldc");
        }
    }
	
}
