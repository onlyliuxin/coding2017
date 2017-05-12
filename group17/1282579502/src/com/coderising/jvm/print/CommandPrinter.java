package com.coderising.jvm.print;

import com.coderising.jvm.cmd.BiPushCmd;
import com.coderising.jvm.cmd.GetFieldCmd;
import com.coderising.jvm.cmd.GetStaticFieldCmd;
import com.coderising.jvm.cmd.InvokeSpecialCmd;
import com.coderising.jvm.cmd.InvokeVirtualCmd;
import com.coderising.jvm.cmd.LdcCmd;
import com.coderising.jvm.cmd.NewObjectCmd;
import com.coderising.jvm.cmd.NoOperandCmd;
import com.coderising.jvm.cmd.OneOperandCmd;
import com.coderising.jvm.cmd.PutFieldCmd;
import com.coderising.jvm.cmd.TwoOperandCmd;

public interface CommandPrinter {

	public void printBiPushCmd(BiPushCmd cmd);
	public void printGetFieldCmd(GetFieldCmd cmd);
	public void printGetStaticFieldCmd(GetStaticFieldCmd cmd);
	public void printInvokeSpecialCmd(InvokeSpecialCmd cmd);
	public void printInvokeVirtualCmd(InvokeVirtualCmd cmd);
	public void printLdcCmd(LdcCmd cmd);
	public void printNewObjectCmd(NewObjectCmd cmd);
	public void printPutFieldCmd(PutFieldCmd cmd);
	public void printNoOperandCmd(NoOperandCmd cmd);
	public void printOneOperandCmd(OneOperandCmd cmd);
	public void printTwoOperandCmd(TwoOperandCmd cmd);
	
}
