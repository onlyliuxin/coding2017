package mini_jvm.cmd;


import mini_jvm.clz.ClassFile;
import mini_jvm.engine.ExecutionResult;
import mini_jvm.engine.Heap;
import mini_jvm.engine.JavaObject;
import mini_jvm.engine.StackFrame;

public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString() {
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}

	/**
	 * //将 byte 带符号扩展为一个 int 类型的值 value，然后将 value 压入到操作数栈中。
	 * @param frame 函数帧
	 * @param result 函数帧执行结果
	 */
	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		int value = this.getOperand();
		JavaObject jo = Heap.getInstance().newInt(value);
		frame.getOprandStack().push(jo);
	}

}
